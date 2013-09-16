package org.amse.fedotov.noplag.ui.split;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.amse.fedotov.noplag.settings.Settings;
import org.amse.fedotov.noplag.ui.ContentPanel;

@SuppressWarnings("serial")
public class CloseAction extends AbstractAction {
	
	private static final String TITLE = "Close";
	
	private final ContentPanel myContentPanel;
	
	public CloseAction(ContentPanel contentPanel) {
		myContentPanel = contentPanel;
		putValue(SMALL_ICON, Settings.getIcon("close"));
		putValue(SHORT_DESCRIPTION, TITLE);
	}

	public void actionPerformed(ActionEvent e) {
		myContentPanel.close();
	}

}
