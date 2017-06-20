package tw.exmaple.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class IOUtils {

	public static final int EOF = -1;

	private static final int DEFAULT_BUFFER_SIZE = 4 * 1024;

	/**
	 * Close the given {@link Closeable} resource (e.g., {@link java.io.InputStream InputStream},
	 * {@link java.io.OutputStream OutputStream}, {@link java.io.Reader Reader}, or
	 * {@link java.io.Writer Writer}) without the exception handling. Note that this
	 * method should ONLY be used in final cause, this method is not the way to avoid
	 * writing exception handling.
	 *
	 * @param closeable the resource to be closed.
	 */
	public static void closeSilently(final Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Copy the content from the input stream into the output stream. Note that the method does
	 * <em>NOT</em> close the given input stream and output stream, the caller should close the
	 * given streams.
	 *
	 * @param input the content source
	 * @param output the copy target
	 * @return the copied bytes; {@value #EOF} means error occurs
	 */
	public static long copy(final InputStream input, final OutputStream output) {
		return copy(input, output, optimalSizedBuffer());
	}

	/**
	 * Copy the content from the input stream into the output stream. Note that the method does
	 * <em>NOT</em> close the given input stream and output stream, the caller should close the
	 * given streams.
	 *
	 * @param input the content source
	 * @param output the copy target
	 * @param buffer the buffer to be used during copy
	 * @return the copied bytes; {@value #EOF} means error occurs
	 */
	public static long copy(final InputStream input, final OutputStream output, final byte[] buffer) {
		if (input == null || output == null || buffer == null) {
			return EOF;
		}

		int read = 0;
		long copied = 0;
		try {
			while ((read = input.read(buffer)) != EOF) {
				output.write(buffer, 0, read);
				copied += read;
			}
			output.flush();
		}
		catch (IOException e) {
			copied = EOF;
		}
		return copied;
	}

	/**
	 * Create an optimal sized buffer, usually the size is 4 KB for most platforms.
	 *
	 * @return the optimal sized buffer
	 */
	public static byte[] optimalSizedBuffer() {
		return new byte[DEFAULT_BUFFER_SIZE];
	}

	/**
	 * Retrieve the content from the input stream as a string.
	 *
	 * @param input the input stream to retrieve
	 * @return the content from the input stream
	 */
	public static String toString(final InputStream input) {
		return toString(input, Charset.forName("UTF-8"));
	}

	/**
	 * Retrieve the content from the input stream as a string using the specified
	 * charset. Note that the method does <em>NOT</em> close the given input stream,
	 * and the caller should close the input stream.
	 *
	 * @param input the input stream to retrieve
	 * @param charset the chartset used in the conversion
	 * @return the content from the input stream
	 */
	public static String toString(final InputStream input, final Charset charset) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		String result = new String(output.toByteArray(), charset);
		closeSilently(output);
		return result;
	}
}
