package org.amse.fedotov.lcs_algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PluginInfoWriter {
	
	private static final String FILENAME = "info.xml";
	private static final String PLUGIN_CLASS = "main.class";
	
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(PLUGIN_CLASS, AlgorithmPlugin.class.getCanonicalName());
		File info = new File(FILENAME);
		OutputStream os = null;
		try {
			os = new FileOutputStream(info);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties.storeToXML(os, null);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
