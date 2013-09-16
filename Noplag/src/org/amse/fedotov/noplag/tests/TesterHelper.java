package org.amse.fedotov.noplag.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

public abstract class TesterHelper {

	public static final String PATH = "tests" + File.separatorChar;

	protected BufferedReader getReader(String fullFilename) throws IOException {
		return new BufferedReader(new FileReader(fullFilename));
	}

	/**
	 * Returns testing directory. 
	 * @return testing directory. 
	 */
	protected abstract File getDirectory();

	/**
	 * Returns files in the testing directory with given extension. 
	 * @param extension extension of files. 
	 * @return files in the testing directory with given extension.
	 */
	public File[] getFiles(final String extension) {
		return getDirectory().listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(extension);
			}
		});
	}
	
	protected boolean isFilesEquals(File a, File b) throws IOException {
		BufferedReader readerA = null;
		BufferedReader readerB = null; 
		try {
			readerA = new BufferedReader(new FileReader(a));
			readerB = new BufferedReader(new FileReader(b));
			while (true) {
				String lineA = readerA.readLine();
				String lineB = readerB.readLine();
				if (lineA == null || lineB == null) {
					return lineA == null && lineB == null;
				}
				if (!lineA.equals(lineB)) {
					return false;
				}
			}
		} finally {
			if (readerA != null) {
				readerA.close();
			}
			if (readerB != null) {
				readerB.close();
			}
		}
	}


}
