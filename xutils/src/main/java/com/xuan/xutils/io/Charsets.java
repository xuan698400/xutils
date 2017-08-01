package com.xuan.xutils.io;

import java.nio.charset.Charset;

/**
 * 常用编码工具
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-9-4 下午7:32:50 $
 */
public class Charsets {

	/**
	 * 获取字符编码对象
	 * 
	 * @param charset
	 * @return
	 */
	public static Charset toCharset(Charset charset) {
		return charset == null ? Charset.defaultCharset() : charset;
	}

	/**
	 * 获取字符编码对象
	 * 
	 * @param charset
	 * @return
	 */
	public static Charset toCharset(String charset) {
		return charset == null ? Charset.defaultCharset() : Charset
				.forName(charset);
	}

	public static final String UTF8 = "UTF-8";
	public static final String ISO88591 = "ISO-8859-1";
	public static final String USASCII = "US-ASCII";
	public static final String UTF16 = "UTF-16";
	public static final String UTF16BE = "UTF-16BE";
	public static final String UTF16LE = "UTF-16LE";

	public static final Charset UTF_8 = Charset.forName(UTF8);
	public static final Charset ISO_8859_1 = Charset.forName(ISO88591);
	public static final Charset US_ASCII = Charset.forName(USASCII);
	public static final Charset UTF_16 = Charset.forName(UTF16);
	public static final Charset UTF_16BE = Charset.forName(UTF16BE);
	public static final Charset UTF_16LE = Charset.forName(UTF16LE);

}
