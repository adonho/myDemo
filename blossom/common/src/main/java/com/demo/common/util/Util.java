package com.demo.common.util;

import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

public class Util
{
	private static Log LOG = LogFactory.getLog(Util.class);
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	static final int GB_SP_DIFF = 160;

	public static String md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i, offset = 7;
			StringBuffer buf = new StringBuffer(32);
			for (int j = 0; j < b.length; j++) {
				i = b[offset++&15] + 128;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//32位加密
			return buf.toString();
			// 16位的加密
			//return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e);
			return null;
		}

	}

	public static String encrypt(String str)
	{
		String encryptStr = null;
		if (str == null) {
			return null;
		}
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			encryptStr = hash.toString(16);
		}
		catch (Exception e)
		{
			LOG.error("MD5 error.", e);
		}
		return encryptStr;
	}

	public static String filterHtml(String value)
	{
		if (value == null) {
			return null;
		}
		char[] content = new char[value.length()];
		value.getChars(0, value.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i])
			{
				case '<':
					result.append("&lt;");
					break;
				case '>':
					result.append("&gt;");
					break;
				case '&':
					result.append("&amp;");
					break;
				case '"':
					result.append("&quot;");
					break;
				case '\'':
					result.append("&#39;");
					break;
				default:
					result.append(content[i]);
			}
		}
		return result.toString();
	}

	public static Timestamp getCurrentTime()
	{
		return new Timestamp(System.currentTimeMillis());
	}

	public static String[] splitString(String src, String prefix)
	{
		int pos = 0;
		int size = 10;
		int i = 0;
		int p = 0;
		String[] fix = new String[10];
		for (;;)
		{
			p = src.indexOf(prefix, i);
			if (p == -1) {
				break;
			}
			if (pos == size)
			{
				size += 10;
				String[] tmp = new String[size];
				System.arraycopy(fix, 0, tmp, 0, pos);
				fix = tmp;
			}
			fix[pos] = src.substring(i, p);
			pos++;
			i = p + prefix.length();
		}
		String[] tmp = new String[pos + 1];
		tmp[pos] = src.substring(i);
		System.arraycopy(fix, 0, tmp, 0, pos);
		return tmp;
	}

	public static Date parseDate(String date)
	{
		return parseDate(date, "yyyy-MM-dd");
	}

	public static Date parseDate(String date, String format)
	{
		if ((date == null) || (date.equals(""))) {
			return null;
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date d = null;
		try
		{
			d = f.parse(date);
		}
		catch (Exception e)
		{
			LOG.error("parseDate error.", e);
		}
		return d;
	}

	public static String formatDate(Date date)
	{
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String format)
	{
		if (date == null) {
			return "";
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}

	public static Date amountDay(Date date, int amount)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(5, amount);
		return c.getTime();
	}

	public static Date cleanTimeFields(Date d)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return cal.getTime();
	}

	public static String nullToString(String s)
	{
		return s == null ? "" : s;
	}

	public static String getString(Object o, String defaultStr)
	{
		if (null == o) {
			return defaultStr;
		}
		String ret = (String)o;
		return "".equals(ret) ? defaultStr : ret;
	}

	public static String replace(String str, String pattern, String replace)
	{
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = str.indexOf(pattern, s)) >= 0)
		{
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	public static String split2MultiLine(String src, int lineLength)
	{
		int srcLength = src.length();
		if (srcLength <= lineLength) {
			return src;
		}
		StringBuffer sb = new StringBuffer();
		int loopCnt = 0;
		int i = 1;
		for (;;)
		{
			if (i * lineLength > srcLength)
			{
				sb.append(src.substring(loopCnt));
				break;
			}
			sb.append(src.substring(loopCnt, i * lineLength));

			sb.append("??");

			loopCnt = i * lineLength;
			i++;
		}
		return sb.toString();
	}

	public static String truncTableField(String tableField)
	{
		return tableField.substring(tableField.indexOf('.') + 1, tableField.length());
	}

	public static String getGBKString(String src)
	{
		try
		{
			return System.getProperty("user.language").equalsIgnoreCase("zh") ? src : new String(src.getBytes("ISO-8859-1"), "GBK");
		}
		catch (UnsupportedEncodingException e) {}
		return src;
	}

	public static String getISO8859String(String src)
	{
		try
		{
			return System.getProperty("user.language").equalsIgnoreCase("zh") ? src : new String(src.getBytes("GBK"), "ISO-8859-1");
		}
		catch (UnsupportedEncodingException e) {}
		return src;
	}

	public static boolean copyFile(String src, String des)
	{
		boolean ret = false;

		File file = new File(src);
		if (!file.isFile())
		{
			LOG.debug("copyFile error: the src file don't exist");
			return ret;
		}
		des = des.replace(File.separator.charAt(0), '/');
		int dirLen = des.lastIndexOf("/");
		String desDir = des.substring(0, dirLen);
		file = new File(desDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		try
		{
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(des);

			File f = new File(src);
			byte[] buf = new byte[(int)f.length()];
			if (fis.read(buf) != -1) {
				fos.write(buf);
			}
			fos.close();
			fis.close();
			ret = true;
		}
		catch (Exception e)
		{
			LOG.error("copyFile error:" + e, e);
		}
		return ret;
	}

	public static boolean copyDir(String src, String des)
	{
		boolean ret = false;

		File file = new File(src);
		if (!file.isDirectory()) {
			return ret;
		}
		file = new File(des);
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] files = new File(src).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				copyFile(src + "/" + files[i].getName(), des + "/" + files[i].getName());
			}
		}
		ret = true;
		return ret;
	}

	public static boolean moveFile(String src, String des)
	{
		boolean ret = false;
		if (copyFile(src, des)) {
			try
			{
				File file = new File(src);
				file.delete();
				ret = true;
			}
			catch (Exception e)
			{
				LOG.error("moveFile error: delete fail," + e, e);
			}
		}
		return ret;
	}

	public static boolean deleteFile(String src)
	{
		boolean ret = false;
		try
		{
			File file = new File(src);
			file.delete();
			ret = true;
		}
		catch (Exception e)
		{
			LOG.error("deleteFile error: " + e, e);
		}
		return ret;
	}

	public static void copyNotNullProperties(Object des, Object src)
	{
		PropertyDescriptor[] pros = PropertyUtils.getPropertyDescriptors(des);
		for (int i = 0; i < pros.length; i++)
		{
			String prosName = pros[i].getName();
			if (PropertyUtils.isWriteable(des, prosName)) {
				try
				{
					Object val = PropertyUtils.getProperty(src, prosName);
					if (null != val) {
						PropertyUtils.setProperty(des, prosName, val);
					}
				}
				catch (Exception e)
				{
					LOG.error("copyNotNullProperties error:" + e, e);
				}
			}
		}
	}

	static final int[] secPosvalueList = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };
	static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z' };

	public static String getGBKInitial(String srcStr)
	{
		srcStr = srcStr.toLowerCase();
		StringBuffer buffer = new StringBuffer();
		try
		{
			for (int i = 0; i < srcStr.length(); i++)
			{
				char ch = srcStr.charAt(i);
				char[] temp = { ch };
				byte[] uniCode = new String(temp).getBytes("GBK");
				if ((uniCode[0] < 128) && (uniCode[0] > 0)) {
					buffer.append(temp);
				} else {
					buffer.append(convert(uniCode));
				}
			}
		}
		catch (Exception e)
		{
			LOG.error("getGBKInitial " + srcStr + " error:" + e);
		}
		return buffer.toString();
	}

	private static char convert(byte[] bytes)
	{
		char result = '-';
		int secPosvalue = 0;
		for (int i = 0; i < bytes.length; i++)
		{
			int tmp15_14 = i;bytes[tmp15_14] = ((byte)(bytes[tmp15_14] - 160));
		}
		secPosvalue = bytes[0] * 100 + bytes[1];
		for (int i = 0; i < 23; i++) {
			if ((secPosvalue >= secPosvalueList[i]) && (secPosvalue < secPosvalueList[(i + 1)]))
			{
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}

	public static void copyURLFile(String srcUrl, String desPath)
	{
		HttpClient client = null;
		HttpMethod method = null;
		try
		{
			client = new HttpClient();
			method = new GetMethod(srcUrl);

			int statusCode = client.executeMethod(method);
			if (statusCode != -1)
			{
				desPath = desPath.replace(File.separator.charAt(0), '/');
				int dirLen = desPath.lastIndexOf("/");
				String desDir = desPath.substring(0, dirLen);
				File file = new File(desDir);
				if (!file.exists()) {
					file.mkdirs();
				}
				InputStream is = method.getResponseBodyAsStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				FileOutputStream fos = new FileOutputStream(desPath);
				int bytesRead = 0;
				byte[] buffer = new byte['?'];
				while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
					fos.write(buffer, 0, bytesRead);
				}
				fos.close();
				bis.close();
				is.close();
			}
		}
		catch (Exception e)
		{
			LOG.error("copyURLFile error:" + e, e);
		}
		finally
		{
			method.releaseConnection();
			method = null;
			client = null;
		}
	}

	public static void zipFile(String inputFileName, String outputFileName)
	{
		try
		{
			outputFileName = outputFileName.replace(File.separator.charAt(0), '/');

			int dirLen = outputFileName.lastIndexOf("/");
			String desDir = outputFileName.substring(0, dirLen);
			File f = new File(desDir);
			if (!f.exists()) {
				f.mkdirs();
			}
			File file = new File(inputFileName);

			FileInputStream in = new FileInputStream(file);
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFileName));

			out.putNextEntry(new ZipEntry(file.getName()));

			int bytesRead = 0;
			byte[] buffer = new byte['?'];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			in.close();
			out.close();
		}
		catch (Exception e)
		{
			LOG.error("zipFile error:" + e, e);
		}
	}

	public static void unzipFile(String zipFileName, String outputFileName)
	{
		try
		{
			ZipInputStream in = new ZipInputStream(new FileInputStream(zipFileName));

			ZipEntry z = null;
			while ((z = in.getNextEntry()) != null) {
				if (false == z.isDirectory())
				{
					outputFileName = outputFileName.replace(File.separator.charAt(0), '/');

					int dirLen = outputFileName.lastIndexOf("/");
					String desDir = outputFileName.substring(0, dirLen);
					File file = new File(desDir);
					if (!file.exists()) {
						file.mkdirs();
					}
					FileOutputStream out = new FileOutputStream(outputFileName);

					int bytesRead = 0;
					byte[] buffer = new byte['?'];
					while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
						out.write(buffer, 0, bytesRead);
					}
					out.close();
				}
			}
			in.close();
		}
		catch (Exception e)
		{
			LOG.error("unzipFile error:" + e, e);
		}
	}
}
