package org.amse.fedotov.noplag;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;
import org.amse.fedotov.noplag.settings.Settings;
import org.amse.fedotov.noplag.ui.MainFrame;

public class Main {

	private static final String FILENAME = "info.xml";
	private static final String PLUGIN_CLASS = "main.class";
	private static final String PLUGINS = "plugins/";
	
	private static List<IAlgorithmPlugin<?>> myAlgoPlugins = new ArrayList<IAlgorithmPlugin<?>>(); 

	public static void main(String[] args) {
		loadPlugins();
	    Settings.load();
	    
	    JFrame mainFrame = new MainFrame(myAlgoPlugins);
		mainFrame.setVisible(true);
	}
	
	private static void loadPlugins() {
		File folder = new File(PLUGINS);
		File[] pluginsFiles = folder.listFiles();
		if (pluginsFiles == null) {
			folder = new File("..", PLUGINS);
			pluginsFiles = folder.listFiles();
			if (pluginsFiles == null) {
				folder = new File(PLUGINS);
				showErrorMessage("Plugins wasn't found");
				return;
			}
		}
	    for (int i = 0; i < pluginsFiles.length; i++) {
	        try {
	            URL jarURL = pluginsFiles[i].toURI().toURL();
	            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
	            Properties properties = new Properties();
	            try {
					properties.loadFromXML(classLoader.getResourceAsStream(FILENAME));
				} catch (InvalidPropertiesFormatException e) {
					showErrorMessage("Incorrect plugin " + jarURL.getPath());
				} catch (IOException e) {
					showErrorMessage("I/O exception during plugin loading (" + jarURL.getPath() + ")");
				} catch (RuntimeException e) {
					showErrorMessage("Incorrect plugin " + jarURL.getPath());
				}

	            Class<?> pluginClass =  classLoader.loadClass(properties.getProperty(PLUGIN_CLASS));
	            IAlgorithmPlugin<?> plugin = (IAlgorithmPlugin) pluginClass.newInstance();
	            myAlgoPlugins.add(plugin);
	        } catch (MalformedURLException e) {
	        	showErrorMessage("Incorrect plugins");
	        } catch (ClassNotFoundException e) {
	        	showErrorMessage("Incorrect plugins");
	        } catch (InstantiationException e) {
	        	showErrorMessage("Incorrect plugins");
			} catch (IllegalAccessException e) {
				showErrorMessage("Incorrect plugins");
			}
	    }
	}
	
	private static void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
