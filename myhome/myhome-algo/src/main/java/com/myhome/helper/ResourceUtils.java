package com.myhome.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ResourceUtils.
 */
public class ResourceUtils {

	/** The logger. */
	private static final Logger log = LoggerFactory
			.getLogger(ResourceUtils.class);

	/** Default Mime Type. */
	private static final String MIME_TYPE_RESOURCE_BUNDLE = "com.dem.st.resources.MimeTypes";

	/** The Constant DEFAULT_MIME_TYPE. */
	private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

	/** The Constant DOWNLOAD_MIME_TYPE. */
	private static final String DOWNLOAD_MIME_TYPE = "application/x-download";

	/** The Constant IMAGE_JPG. */
	private static final String IMAGE_JPG = "jpg";

	/**
	 * Find resource.
	 * 
	 * @param path
	 *            the path
	 * 
	 * @return the uRL
	 */
	public static URL findResource(String path) {
		return ResourceUtils.class.getResource(path);
	}

	/**
	 * Find resource as stream.
	 * 
	 * @param path
	 *            the path
	 * 
	 * @return the input stream
	 */
	public static InputStream findResourceAsStream(String path) {
		return ResourceUtils.class.getResourceAsStream(path);
	}

	/**
	 * Checks if file is an image.
	 * 
	 * @param fileName
	 *            the file name
	 * 
	 * @return true, if is image file
	 */
	public static boolean isImageFile(String fileName) {
		String mimeType = getMimeType(fileName);
		return (mimeType != null && mimeType.toLowerCase().startsWith("image/"));
	}

	/**
	 * Gets the mime type of the specified filename or extension.
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the mime type
	 */
	public static String getMimeType(String str) {

		String ext = "";
		String mimeType = DEFAULT_MIME_TYPE;
		if (str != null && str.indexOf(".") > 0) {
			// str is a filename
			ext = str.substring(str.lastIndexOf(".") + 1);

		} else {
			// str is an extension
			ext = str;
		}

		ResourceBundle types = ResourceBundle
				.getBundle(MIME_TYPE_RESOURCE_BUNDLE);
		String type = null;
		try {
			type = types.getString((ext == null) ? ext : ext.toLowerCase());
		} catch (Exception e) {
			log.error("Mime type not found for extension " + ext);
		}
		if (type != null && !type.equals(""))
			mimeType = type;
		else
			mimeType = DOWNLOAD_MIME_TYPE;

		return mimeType;
	}

	/**
	 * Load picture.
	 * 
	 * @param pictureName
	 *            the picture name
	 * @param classLoader
	 *            the class loader
	 * 
	 * @return the byte[]
	 */
	public static byte[] loadPicture(String pictureName, ClassLoader classLoader) {
		InputStream input = classLoader.getResourceAsStream(pictureName);
		byte[] data = null;
		BufferedImage img;
		try {
			img = ImageIO.read(input);
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			ImageIO.write(img, IMAGE_JPG, bas);
			data = bas.toByteArray();
		} catch (IOException e) {
			log.error("An error has occurred while loading picture", e);
		}
		return data;
	}

	/**
	 * Store picture.
	 * 
	 * @param data
	 *            the data
	 * @param pictureFile
	 *            the picture file
	 */
	public static void storePicture(byte[] data, File pictureFile) {
		try {
			OutputStream output = new FileOutputStream(pictureFile);
			output.write(data);
		} catch (IOException e) {
			log.error("An error has occurred while storing picture", e);
		}
	}

	/**
	 * Scale image.
	 * 
	 * @param p_image
	 *            the p_image
	 * @param p_width
	 *            the p_width
	 * @param p_height
	 *            the p_height
	 * 
	 * @return the byte[]
	 * 
	 * @throws Exception
	 *             the exception
	 */
	// public static byte[] scaleImage(InputStream p_image, int p_width,
	// int p_height) throws Exception {
	//
	// InputStream imageStream = new BufferedInputStream(p_image);
	// Image image = (Image) ImageIO.read(imageStream);
	//
	// int thumbWidth = p_width;
	// int thumbHeight = p_height;
	//
	// // Make sure the aspect ratio is maintained, so the image is not skewed
	// double thumbRatio = (double) thumbWidth / (double) thumbHeight;
	// int imageWidth = image.getWidth(null);
	// int imageHeight = image.getHeight(null);
	// double imageRatio = (double) imageWidth / (double) imageHeight;
	// if (thumbRatio < imageRatio) {
	// thumbHeight = (int) (thumbWidth / imageRatio);
	// } else {
	// thumbWidth = (int) (thumbHeight * imageRatio);
	// }
	//
	// // Draw the scaled image
	// BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight,
	// BufferedImage.TYPE_INT_RGB);
	// Graphics2D graphics2D = thumbImage.createGraphics();
	// graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	// RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	// graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
	//
	// // Write the scaled image to the outputstream
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	// JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
	// int quality = 100; // Use between 1 and 100, with 100 being highest
	// // quality
	// quality = Math.max(0, Math.min(quality, 100));
	// param.setQuality((float) quality / 100.0f, false);
	// encoder.setJPEGEncodeParam(param);
	// encoder.encode(thumbImage);
	// ImageIO.write(thumbImage, IMAGE_JPG, out);
	// return out.toByteArray();
	// }

	/**
	 * Check picture size.
	 * 
	 * @param data
	 *            the data
	 * @param min
	 *            the min
	 * @param max
	 *            the max
	 * @param minSize
	 *            the min size
	 * @param maxSize
	 *            the max size
	 * 
	 * @return true, if successful
	 */
	public static boolean checkPictureSize(byte[] data, int min, int max,
			int minSize, int maxSize) {
		BufferedImage image = null;
		int width = 0;
		int height = 0;
		int size = 0;
		try {
			if (data != null && data.length > 0) {
				size = data.length;
				InputStream input = new ByteArrayInputStream(data);
				if (input != null) {
					image = ImageIO.read(input);
					if (image != null) {
						width = image.getWidth();
						height = image.getHeight();
					}
				}
				input.close();
			} else {
				if (log.isWarnEnabled()) {
					log.warn("No picture found");
				}
			}
		} catch (IOException e) {
			log.error("An error has occurred while checking picture", e);
			return false;
		}
		return image != null && width >= min && width <= max && height >= min
				&& height <= max && size >= minSize && size <= maxSize;
	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	/**
	 * Gets the file extension name.
	 * 
	 * @param filename
	 *            the filename
	 * 
	 * @return the file extension name
	 */
	public static String getFileExtensionName(String filename) {
		String extension = "";
		if (filename != null) {
			int index = -1;
			if (!((index = filename.lastIndexOf(".")) == -1)) {
				extension = filename.substring(index + 1, filename.length());
			}
		}
		return extension;
	}

	/**
	 * Removes the file name extension.
	 * 
	 * @param filename
	 *            the filename
	 * 
	 * @return the string
	 */
	public static String removeFileNameExtension(String filename) {

		if (filename != null) {
			int index = -1;
			if (!((index = filename.lastIndexOf(".")) == -1)) {
				filename = filename.substring(0, index);
			}
		}
		return filename;
	}

	/**
	 * Limit file name.
	 * 
	 * @param filename
	 *            the filename
	 * @param limit
	 *            the limit
	 * 
	 * @return the string
	 */
	public static String limitFileName(String filename, int limit) {

		if (filename == null)
			return "";

		if (limit > 0 && filename.length() > limit) {
			String extension = getFileExtensionName(filename);
			String name = removeFileNameExtension(filename);
			int len = limit - extension.length() - 1;
			if (len <= 0) {
				len = 1;
			}
			name = name.substring(0, len);
			filename = name + "." + extension;
		}
		return filename;
	}

}
