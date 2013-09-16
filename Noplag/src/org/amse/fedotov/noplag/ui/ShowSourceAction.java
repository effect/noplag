package org.amse.fedotov.noplag.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import org.amse.fedotov.noplag.settings.Settings;

/* package */ @SuppressWarnings("serial")
class ShowSourceAction extends AbstractAction {
	
	private final static String ACTION_TEXT = "Show Source";
	
	private final CardLayout myCardLayout;
	private final JPanel myCardsPanel;

	public ShowSourceAction(CardLayout cardLayout, JPanel cardsPanel) {
		super("Source");
		putValue(SMALL_ICON, Settings.getIcon("source"));
		putValue(SHORT_DESCRIPTION, ACTION_TEXT);
		myCardLayout = cardLayout;
		myCardsPanel = cardsPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		myCardLayout.last(myCardsPanel);
	}

}
