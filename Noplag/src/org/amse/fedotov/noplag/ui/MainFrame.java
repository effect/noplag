package org.amse.fedotov.noplag.ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;
import org.amse.fedotov.noplag.ui.split.Layer;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private static final String TITLE = "Noplag";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;
	private static final int DEFAULT_X_LOCATION = 80;
	private static final int DEFAULT_Y_LOCATION = 40;
	private static final ProgramsFrame PROGRAMS_FRAME = new ProgramsFrame();
	private static final ProgressDialog PROGRESS_DIALOG = new ProgressDialog();
	private static final AuthorsDialog AUTHORS_DIALOG = new AuthorsDialog();
	
	private static List<IAlgorithmPlugin<?>> myAlgoPlugins;
	
	public MainFrame(List<IAlgorithmPlugin<?>> algoPlugins) {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocation(DEFAULT_X_LOCATION, DEFAULT_Y_LOCATION);

		myAlgoPlugins = algoPlugins;
		setJMenuBar(createMenu());
		Layer layer = new Layer(null);
		ContentPanel contentPanel = new ContentPanel(layer);
		layer.setContent(contentPanel);
		setContentPane(layer);
	}
	
	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu settingsMenu = new JMenu("Settings");
		settingsMenu.add(new JMenuItem(new SettingsAction(AUTHORS_DIALOG)));
		menuBar.add(settingsMenu);
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.add(new JMenuItem(new AboutAction()));
		menuBar.add(aboutMenu);
		return menuBar;
	}

	public static ProgramsFrame getProgramsFrame() {
		return PROGRAMS_FRAME;
	}
	
	public static ProgressDialog getProgressDialog() {
		return PROGRESS_DIALOG;
	}
	
	public static AuthorsDialog getAuthorsDialog() {
		return AUTHORS_DIALOG;
	}
	
	public static List<IAlgorithmPlugin<?>> getAlgoPlugins() {
		return myAlgoPlugins;
	}
	
}
