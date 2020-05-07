package org.intsmaze.classload.service.javac.one;

import javax.tools.*;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicLoader {
	/**
	 * auto fill in the java-name with code, return null if cannot find the
	 * public class
	 * 
	 * @param javaSrc
	 *            source code string
	 * @return return the Map, the KEY means ClassName, the VALUE means
	 *         bytecode.
	 */
	public static Map<String, byte[]> compile(String javaSrc) {
		Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
		Matcher matcher = pattern.matcher(javaSrc);
		if (matcher.find())
			return compile(matcher.group(1) + ".java", javaSrc);
		return null;
	}

	/**
	 * @param javaName
	 *            the name of your public class,eg: <code>TestClass.java</code>
	 * @param javaSrc
	 *            source code string
	 * @return return the Map, the KEY means ClassName, the VALUE means
	 *         bytecode.
	 */
	public static Map<String, byte[]> compile(String javaName, String javaSrc) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager stdManager = compiler.getStandardFileManager(
				null, null, null);
		try (MemoryJavaFileManager manager = new MemoryJavaFileManager(
				stdManager)) {
			JavaFileObject javaFileObject = manager.makeStringSource(javaName,
					javaSrc);
			JavaCompiler.CompilationTask task = compiler.getTask(null, manager,
					null, null, null, Arrays.asList(javaFileObject));
			if (task.call())
				return manager.getClassBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static class MemoryClassLoader extends URLClassLoader {
		Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

		public MemoryClassLoader(Map<String, byte[]> classBytes) {
			super(new URL[0], MemoryClassLoader.class.getClassLoader());
			this.classBytes.putAll(classBytes);
		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			byte[] buf = classBytes.get(name);
			if (buf == null) {
				return super.findClass(name);
			}
			classBytes.remove(name);
			return defineClass(name, buf, 0, buf.length);
		}
	}
}

final class MemoryJavaFileManager extends ForwardingJavaFileManager {
	/**
	 * Java source file extension.
	 */
	private final static String EXT = ".java";
	private Map<String, byte[]> classBytes;

	public MemoryJavaFileManager(JavaFileManager fileManager) {
		super(fileManager);
		classBytes = new HashMap<String, byte[]>();
	}

	public Map<String, byte[]> getClassBytes() {
		return classBytes;
	}

	public void close() throws IOException {
		classBytes = new HashMap<String, byte[]>();
	}

	public void flush() throws IOException {
	}

	/**
	 * A file object used to represent Java source coming from a string.
	 */
	private static class StringInputBuffer extends SimpleJavaFileObject {
		final String code;

		StringInputBuffer(String name, String code) {
			super(toURI(name), Kind.SOURCE);
			this.code = code;
		}

		public CharBuffer getCharContent(boolean ignoreEncodingErrors) {
			return CharBuffer.wrap(code);
		}

		public Reader openReader() {
			return new StringReader(code);
		}
	}

	/**
	 * A file object that stores Java bytecode into the classBytes map.
	 */
	private class ClassOutputBuffer extends SimpleJavaFileObject {
		private String name;

		ClassOutputBuffer(String name) {
			super(toURI(name), Kind.CLASS);
			this.name = name;
		}

		public OutputStream openOutputStream() {
			return new FilterOutputStream(new ByteArrayOutputStream()) {
				public void close() throws IOException {
					out.close();
					ByteArrayOutputStream bos = (ByteArrayOutputStream) out;
					classBytes.put(name, bos.toByteArray());
				}
			};
		}
	}

	public JavaFileObject getJavaFileForOutput(
			Location location, String className,
			JavaFileObject.Kind kind, FileObject sibling) throws IOException {
		if (kind == JavaFileObject.Kind.CLASS) {
			return new ClassOutputBuffer(className);
		} else {
			return super.getJavaFileForOutput(location, className, kind,
					sibling);
		}
	}

	static JavaFileObject makeStringSource(String name, String code) {
		return new StringInputBuffer(name, code);
	}

	static URI toURI(String name) {
		File file = new File(name);
		if (file.exists()) {
			return file.toURI();
		} else {
			try {
				final StringBuilder newUri = new StringBuilder();
				newUri.append("mfm:///");
				newUri.append(name.replace('.', '/'));
				if (name.endsWith(EXT))
					newUri.replace(newUri.length() - EXT.length(),
							newUri.length(), EXT);
				return URI.create(newUri.toString());
			} catch (Exception exp) {
				return URI.create("mfm:///com/sun/script/java/java_source");
			}
		}
	}
}
