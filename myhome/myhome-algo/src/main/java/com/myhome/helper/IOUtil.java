/**
 * 
 */
package com.myhome.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class IOUtil
 * <P>
 * IO helper class
 * 
 * @author
 */
public final class IOUtil {

	/** int BUF_SIZE */
	private static final int BUF_SIZE = 1024;

	private static final Logger log = LoggerFactory.getLogger(IOUtil.class);

	/**
	 * Constructor
	 * <P>
	 * private for class containing only static functions
	 */
	private IOUtil() {
	}

	/**
	 * Close in and out streams
	 * <P>
	 * 
	 * @param InputStream
	 *            in
	 * @param OutputStream
	 *            out
	 */
	public static void closeSilently(InputStream in, OutputStream out) {
		boolean asErrors = false;
		IOException error = null;
		try {
			if (in != null) {
				in.close();
			}
		} catch (final IOException e) {
			asErrors = true;
			error = e;
		}
		try {
			if (out != null) {
				out.close();
			}
		} catch (final IOException e) {
			asErrors = true;
			error = e;
		}
		if (asErrors) {
			log.error("", error);
		}
	}

	/**
	 * Copy input stream to output stream
	 * <P>
	 * 
	 * @param InputStream
	 *            in
	 * @param OutputStream
	 *            out
	 * @throws IOException
	 */
	public static void copyStream(InputStream in, OutputStream out)
			throws IOException {
		final byte buf[] = new byte[BUF_SIZE];
		int len = 0;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		out.flush();
	}

	/**
	 * Delete file from disk
	 * <P>
	 * 
	 * @param File
	 *            file
	 */
	public static void deleteFile(File file) {
		if (file != null && !file.delete()) {
			log.error("deleteFile", new IOException("could not delete file "
					+ file.getName()));
		}
	}

	/**
	 * Read a file by fileFullPath an put result in List<String> lines
	 * <P>
	 * 
	 * @param String
	 *            fileFullPath
	 * @return List<String> lines
	 * @throws IOException
	 * @throws RuntimeException
	 */
	public static List<String> readFile(String fileFullPath) throws IOException {
		List<String> lines = null;
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new java.io.FileReader(
					fileFullPath));
			lines = new ArrayList<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return lines;
	}
}
