import java.net.URL;
import java.net.URLClassLoader;
import org.intsmaze.classload.service.CompileService;
import org.intsmaze.classload.service.DynamicService;
/**
 * @author:YangLiu
 * @date:2018年5月18日 下午5:13:44
 * @describe:这个加载class文件，发现经常java.lang.ClassNotFoundException，
 * 用自己自定义的FileSystemClassLoader没有问题。
 */
public class CompileTest {

	public static void main(String[] args) throws Exception {
	
		URL[] urls = new URL[] { new URL("file:/"
				+ CompileService.CLASS_PATH +"/"+CompileService.CLASS_NAME +".class") };
		ClassLoader classLoader = new URLClassLoader(urls);
		Class<?> c = classLoader.loadClass(CompileService.CLASS_NAME);
		DynamicService o = (DynamicService) c.newInstance();
		o.executeService("ClassNotFoundException");
	}
}

