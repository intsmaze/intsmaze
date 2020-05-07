package cn.intsmaze.io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetHelper {
	private static final String UTF_8 = "UTF-8";
	private static CharsetEncoder encoder = Charset.forName(UTF_8).newEncoder();
	private static CharsetDecoder decoder = Charset.forName(UTF_8).newDecoder();

	public static ByteBuffer encode(CharBuffer in)
			throws CharacterCodingException {
		return encoder.encode(in);
	}

	public static CharBuffer decode(ByteBuffer in)
			throws CharacterCodingException {
		return decoder.decode(in);
	}
}
