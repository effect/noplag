package org.amse.fedotov.noplag.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;
import org.amse.fedotov.noplag.settings.Settings;
import org.amse.fedotov.noplag.ui.split.CloseAction;
import org.amse.fedotov.noplag.ui.split.HorizontalSplitAction;
import org.amse.fedotov.noplag.ui.split.Layer;
import org.amse.fedotov.noplag.ui.split.SplittablePanel;
import org.amse.fedotov.noplag.ui.split.VerticalSplitAction;

@SuppressWarnings("serial")
public class ContentPanel extends SplittablePanel {

	private static final String DEFAULT_DIRECTORY = Settings.getInputFilesDirectory();
	private static final JFileChooser FILE_CHOOSER = new JFileChooser(DEFAULT_DIRECTORY);
	
	private final CloseAction myCloseAction = new CloseAction(this);
	private final AuthorsDialog myAuthorsDialog;
	
	public ContentPanel(Layer layer) {
		super(layer);
		myAuthorsDialog = MainFrame.getAuthorsDialog();
		CardLayout cardLayout = new CardLayout();
		JPanel cardsPanel = new JPanel(cardLayout);
		setLayout(new BorderLayout());
		JPanel diagramPanel = new JPanel(new BorderLayout());
		ContentManager manager = new ContentManager(diagramPanel);
		JToolBar toolbar = createToolBar(manager, cardLayout, cardsPanel);
		add(toolbar, BorderLayout.NORTH);
		cardsPanel.add(diagramPanel, "diagram");
		cardsPanel.add(new SourcePanel(manager), "source");
		add(cardsPanel, BorderLayout.CENTER);
	}
	
	private JToolBar createToolBar(ContentManager manager, CardLayout cardLayout, JPanel cardsPanel) {
		JToolBar toolbar = new JToolBar();
		OpenAction openAction = new OpenAction(manager, FILE_CHOOSER, myAuthorsDialog);
		toolbar.add(openAction);
		
		JToggleButton[] algoButtons = new JToggleButton[MainFrame.getAlgoPlugins().size()];
		for (int i = 0; i < MainFrame.getAlgoPlugins().size(); i++) {
			IAlgorithmPlugin<?> plugin = MainFrame.getAlgoPlugins().get(i);
			@SuppressWarnings("unchecked")
			AlgorithmMode<?> algoMode = new AlgorithmMode(plugin, manager); 
			algoButtons[i] = new JToggleButton(algoMode);
		}
		addGroupButttons(algoButtons, toolbar, true);
		if (algoButtons.length > 0) {
			algoButtons[0].doClick();
		}
		
		toolbar.add(new AddProgramAction(manager));
		
		JToggleButton diagramButton = new JToggleButton(new ShowDiagramAction(cardLayout, cardsPanel));
		JToggleButton sourceButton = new JToggleButton(new ShowSourceAction(cardLayout, cardsPanel));
		diagramButton.setSelected(true);
		addGroupButttons(new JToggleButton[] {diagramButton, sourceButton}, toolbar, false);
		
		toolbar.add(new HorizontalSplitAction(this));
		toolbar.add(new VerticalSplitAction(this));
		toolbar.add(myCloseAction);
		return toolbar;
	}
	
	private void addGroupButttons(JToggleButton[] buttons, JToolBar toolbar, boolean showButtonText) {
		ButtonGroup group = new ButtonGroup();
		for (JToggleButton button : buttons) {
			if (!showButtonText) {
				button.setText("");
			}
			toolbar.add(button);
			group.add(button);	
		}
	}
	
}
