package org.amse.fedotov.noplag.settings;

import java.io.File;
import java.nio.charset.Charset;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.amse.fedotov.noplag.data_storage.IDataStorage;
import org.amse.fedotov.noplag.data_storage.impl.DataStorage;
import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.lexer.pascal.PascalTokenFactory;
import org.amse.fedotov.noplag.model.ITokenFactory;
import org.amse.fedotov.noplag.program_storage.IProgramStorage;
import org.amse.fedotov.noplag.program_storage.impl.ProgramStorage;
import org.amse.fedotov.noplag.ui.barchart_models.ProgramResultChartModel;

public class Settings {
	
	/**
	 * In <code>INTERNAL_CHARSET</code> all internal character data is stored.
	 * UTF-8 by default.
	 */
	private static final String INTERNAL_CHARSET = "UTF8";

	private static final String DEFAULT_INPUT_FILE_ENCODING = "Cp1251";
	private static final int DEFAULT_NUMBER_SIMILAR = 5;
	private static final String DEFAULT_STORAGE_PATH = "storage";
	private static final String DEFAULT_INPUT_FILES_PATH = "examples";
	private static final String ICONS_PATH = "icons/small/";
	private static final ITokenFactory myTokenFactory = PascalTokenFactory.getInstance();
	
	/**
	 * Encoding of input file. 
	 */
	private static String myInputFileEncoding;

	/**
	 * Number of similar programs that are displayed after comparing. 
	 */
	private static int myNumberSimilar;
	private static String myNoplagDataPath;
	private static String myInputFilesDirectory = DEFAULT_INPUT_FILES_PATH;
	private static IDataStorage myDataStorage;
	private static IProgramStorage myDatabase;
	private static ProgramResultChartModel myProgramResultChartModel;

	/**
	 * Must be invoked when program starts. 
	 * @param path path for directory with storage.
	 * @return <code>true</code> if and only if new storage path is set successfully.  
	 */
	public static boolean setNoplagDataPath(String path) {
		myNoplagDataPath = path;
		if (path.length() > 0 && path.charAt(path.length() - 1) != File.separatorChar) {
			myNoplagDataPath += File.separatorChar;
		}
		if (FileUtils.createDirectory(new File(myNoplagDataPath))) {
			myDataStorage = new DataStorage();
			myDatabase = new ProgramStorage(myDataStorage, myTokenFactory);
			return true;
		} else {
			return false;
		}
	}

	public static String getInternalCharset() {
		return INTERNAL_CHARSET;
	}
	
	public static IProgramStorage getDatabase() {
		return myDatabase;
	}
	
	public static String getNoplagDataPath() {
		return myNoplagDataPath;
	}
	
	public static ProgramResultChartModel getCurrentChartModel() {
		return myProgramResultChartModel;
	}
	
	public static void setProgramResultChartModel(ProgramResultChartModel programResultChartModel) {
		myProgramResultChartModel = programResultChartModel;
	}
	
	public static Icon getIcon(String name) {
		return new ImageIcon(ClassLoader.getSystemResource(ICONS_PATH + name + ".png"));
	}
	
	public static int getNumberSimilar() {
		return myNumberSimilar;
	}
	
	public static String getInputFileEncoding() {
		return myInputFileEncoding;
	}
	
	public static boolean setInputFileEncoding(String encoding) {
		if (encoding != null && Charset.isSupported(encoding)) {
			myInputFileEncoding = encoding;
			return true;
		} else {
			myInputFileEncoding = DEFAULT_INPUT_FILE_ENCODING;
			return false;
		}
	}
	
	public static boolean setNumberSimilar(String number) {
		try {
			int num = Integer.parseInt(number);
			myNumberSimilar = num;
			return true;
		} catch (RuntimeException e) {
			myNumberSimilar = DEFAULT_NUMBER_SIMILAR;
			return false;
		}
	}
	
	public static void setDefaultUserSettings() {
		myInputFileEncoding = DEFAULT_INPUT_FILE_ENCODING;
		myNumberSimilar = DEFAULT_NUMBER_SIMILAR;
		myNoplagDataPath = DEFAULT_STORAGE_PATH;
		myInputFilesDirectory = DEFAULT_INPUT_FILES_PATH;
		UserSettingsManager.writeUserSettings();
		load();
	}
	
	public static String getInputFilesDirectory() {
		return myInputFilesDirectory;
	}
	
	public static void setInputFilesDirectory(String path) {
		myInputFilesDirectory = path;
	}
	
	public static void load() {
		UserSettingsManager.readUserSettings();
	}
	
	public static void save() {
		UserSettingsManager.writeUserSettings();
	}

	
//	public static void setDefaultNoplagDataPath() {
//		File appDirectory = new File(System.getProperty("user.home")
//				+ File.separatorChar + "Application Data");
//
//		String homePath;
//		if (appDirectory.exists()) {
//			// For Windows
//			homePath =  System.getProperty("user.home") + File.separatorChar
//					+ "Application Data" + File.separatorChar + "Noplag"
//					+ File.separatorChar;
//		} else {
//			// For other OS (not Windows)
//			homePath = System.getProperty("user.home") + File.separatorChar
//					+ ".noplag" + File.separatorChar;
//		}
//		setNoplagDataPath(homePath);
//	}

}
