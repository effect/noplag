package org.amse.fedotov.noplag.file_utilities;

import java.io.*;

import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.settings.Settings;

/**
 * File utilities.
 * 
 * @author Pavel Fedotov
 * 
 */
public class FileUtils {
	
	private static final int BUFFER_SIZE = 2048;
	
	private FileUtils() {
	}
	
	/**
	 * Creates file if it hasn't created yet. It also creates necessary
	 * absenting directories where this file stores.
	 * 
	 * @param file creating file.
	 */
	public static void createNewFile(File file) throws IOException {
		createDirectory(file.getParentFile());
		file.createNewFile();
	}
	
	/**
	 * Creates directory and necessary absenting directories where this directory stores. 
	 * @param directory creating directory. 
	 * @return <code>true</code> if directory is created or was already created before.
	 */
	public static boolean createDirectory(File directory) {
		if (!directory.exists()) {
			return directory.mkdirs();
		} 
		return true;
	}
	
	public static byte[] getSource(Reader reader) throws BadInputException {
		StringBuffer sb = new StringBuffer();
		try {		
			char[] cbuf = new char[BUFFER_SIZE];
			int len;
			while((len = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, len);
			}
			reader.close();
		} catch (IOException e) {
			BadInputException ex = new BadInputException("Can't read source from stored file");
			ex.initCause(e);
			throw ex;
		}
		String s = sb.toString();
		byte[] source;
		try {
			source = s.getBytes(Settings.getInternalCharset());
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		}
		return source;
	}

	
	/**
	 * Deletes all files and subdirectories under dir. Returns <code>true</code>
	 * if all deletions were successful. If a deletion fails, the method stops
	 * attempting to delete and returns <code>false</code>.
	 * 
	 * @param dir directory or file to be deleted. 
	 * @return <code>true</code> if all deletions were successful, 
	 * <code>false</code> otherwise. 
	 * 
	 * @see http://exampledepot.com/egs/java.io/DeleteDir.html
	 * 
	 * @see http://forum.java.sun.com/thread.jspa?threadID=606628&start=0&tstart=0 if
	 * troubles with deleting occured.  
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	public static Reader createReader(File file) throws IOException {
		return new InputStreamReader(new FileInputStream(file), Settings.getInputFileEncoding());
	}

}