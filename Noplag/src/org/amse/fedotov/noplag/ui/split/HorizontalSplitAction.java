package org.amse.fedotov.noplag.ui.split;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JSplitPane;

import org.amse.fedotov.noplag.settings.Settings;
import org.amse.fedotov.noplag.ui.ContentPanel;

@SuppressWarnings("serial")
public class HorizontalSplitAction extends AbstractAction {
	
	private static final String TITLE = "Horizontal Split";
	
	private final ContentPanel myContentPanel;
	
	public HorizontalSplitAction(ContentPanel contentPanel) {
		myContentPanel = contentPanel;
		putValue(NAME, TITLE);
		putValue(SMALL_ICON, Settings.getIcon("splitHorizontal"));
		putValue(SHORT_DESCRIPTION, TITLE);
	}
	
	public void actionPerformed(ActionEvent e) {
		myContentPanel.split(JSplitPane.HORIZONTAL_SPLIT);
	}

}
