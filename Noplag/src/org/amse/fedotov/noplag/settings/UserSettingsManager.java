package org.amse.fedotov.noplag.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JOptionPane;


/* package */ class UserSettingsManager {
	
	private static final String FILENAME = "settings.xml";
	private static final String FILE_ENCODING = "InputFileEncoding";
	private static final String NUMBER_SIMILAR = "NumberSimilar";
	private static final String STORAGE_PATH = "StoragePath";
	private static final String INPUT_DIRECTORY = "InputDirectory";
	
	public static void writeUserSettings() {
		Properties properties = new Properties();
		properties.put(FILE_ENCODING, Settings.getInputFileEncoding());
		properties.put(NUMBER_SIMILAR, Integer.toString(Settings.getNumberSimilar()));
		properties.put(STORAGE_PATH, Settings.getNoplagDataPath());
		properties.put(INPUT_DIRECTORY, Settings.getInputFilesDirectory());
		File info = new File(FILENAME);
		OutputStream os = null;
		try {
			os = new FileOutputStream(info);
		} catch (FileNotFoundException e) {
			showErrorMessage("Can't write to settings file");
		}
		try {
			properties.storeToXML(os, null);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			showErrorMessage("I/O exception");
		}
	}
	
	public static void readUserSettings() {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(FILENAME));
		} catch (FileNotFoundException e) {
			Settings.setDefaultUserSettings();
		}
		try {
			properties.loadFromXML(is);
			Settings.setInputFileEncoding(properties.getProperty(FILE_ENCODING));
			Settings.setNumberSimilar(properties.getProperty(NUMBER_SIMILAR));
			Settings.setNoplagDataPath(properties.getProperty(STORAGE_PATH));
			Settings.setInputFilesDirectory(properties.getProperty(INPUT_DIRECTORY));
		} catch (InvalidPropertiesFormatException e) {
			Settings.setDefaultUserSettings();
		} catch (IOException e) {
			Settings.setDefaultUserSettings();
		} catch (RuntimeException e) {
			Settings.setDefaultUserSettings();
		}
	}
	
	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
