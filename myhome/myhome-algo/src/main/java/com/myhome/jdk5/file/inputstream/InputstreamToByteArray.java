package com.myhome.jdk5.file.inputstream;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class InputstramToByteArray.
 */
public class InputstreamToByteArray {
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String args[]) throws IOException {
		FileInputStream in = new FileInputStream(new File("c:/voca.txt"));
		renameResource(in);
		splitByte("12112135446484489fghfgh".getBytes(), 64);

	}

	/**
	 * Cut string.
	 * 
	 * @param str the str
	 * @param length the length
	 */
	public static void cutString(String str, int length) {
		int strlen = str.length();

		List<String> array = new ArrayList<String>();
		int beginIndex = 0;
		while (beginIndex < strlen) {
			int endIndex = beginIndex + length;
			if (endIndex < strlen) {
				array.add(str.substring(beginIndex, endIndex));
			} else {
				array.add(str.substring(beginIndex, strlen));
			}
			beginIndex = endIndex;
		}

	}

	/**
	 * Split byte.
	 * 
	 * @param data the data
	 * @param length the length
	 * 
	 * @return the list<byte[]>
	 */
	public static List<byte[]> splitByte(byte[] data, int length) {
		int strlen = data.length;

		List<byte[]> array = new ArrayList<byte[]>();
		int beginIndex = 0;
		while (beginIndex < strlen) {
			int endIndex = beginIndex + length;
			byte[] buffer = new byte[length];
			if (endIndex < strlen) {
				System.arraycopy(data, beginIndex, buffer, 0, length);

			} else {
				System.arraycopy(data, beginIndex, buffer, 0, strlen
						- beginIndex);

			}
			array.add(buffer);
			beginIndex = endIndex;
		}

		return array;

	}

	/**
	 * Convert inputstream to byte array.
	 * 
	 * @param itStrm the it strm
	 * 
	 * @return the byte[]
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] convertInputstreamToByteArray(InputStream itStrm)
			throws IOException {
		byte[] buffer = null;
		int size = itStrm.available();
		buffer = new byte[size];
		itStrm.read(buffer);		
		return buffer;
	}

	/**
	 * Trim zero on left.
	 * 
	 * @param str the str
	 * 
	 * @return the string
	 */
	public static String trimZeroOnLeft(String str) {
		int len = str.length();
		int st = 0;
		char[] val = str.toCharArray();
		while ((st < len) && (val[st] <= '0')) {
			st++;
		}
		return ((st > 0)) ? str.substring(st, len) : str;
	}

	/**
	 * Read resource and calculate file size.
	 * 
	 * @param stream the stream
	 * 
	 * @return the file size in Bytes, KB or MB as String
	 */
	public static String getFileSize(InputStream stream) {

		DecimalFormat myFormatter = new DecimalFormat("###.##");
		double size = 0;
		String unit = "";

		try {

			size = stream.available();
			if (size < 1024) {
				unit = " Bytes";
			} else if (size < 1048576) {
				size = size / 1024;
				unit = " KB";
			} else if (size < 1073741824) {
				size = size / 1024 / 1024;
				unit = " MB";
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return myFormatter.format(size) + unit;
	}

	/**
	 * Rename resource.
	 * 
	 * @param stream the stream
	 * 
	 * @return the string
	 */
	public static String renameResource(InputStream stream) {
		String newname = "c:/xxx.txt";
		File f = new File(newname);

		OutputStream out = null;
		try {

			out = new FileOutputStream(f);

			byte buf[] = new byte[1024];
			int len;
			while ((len = stream.read(buf)) > 0)
				out.write(buf, 0, len);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return f.getAbsolutePath();
	}

	/**
	 * Returns a byte array for a given file resource path.
	 * 
	 * @param resourcePath the resource path
	 * 
	 * @return the byte array from resource path
	 * 
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] getByteArrayFromResourcePath(String resourcePath)
			throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(new File(resourcePath));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int length;
		byte[] part = new byte[10 * 1024];
		while ((length = in.read(part)) != -1) {
			out.write(part, 0, length);
		}
		in.close();
		return out.toByteArray();
	}

	/**
	 * Input stream as string.
	 * 
	 * @param stream the stream
	 * 
	 * @return the string
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String inputStreamAsString(InputStream stream)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		StringBuilder sb = new StringBuilder();
		String line = null;

		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}

		br.close();
		return sb.toString();
	}
}
