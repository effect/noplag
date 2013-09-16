package org.amse.fedotov.noplag.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;

import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
public class ShowDiagramAction extends AbstractAction {
	
	private final static String ACTION_TEXT = "Show Diagram";
	
	private final CardLayout myCardLayout;
	private final JPanel myCardsPanel;

	public ShowDiagramAction(CardLayout cardLayout, JPanel cardsPanel) {
		super("Diagram");
		putValue(SMALL_ICON, Settings.getIcon("barchart"));
		putValue(SHORT_DESCRIPTION, ACTION_TEXT);
		myCardLayout = cardLayout;
		myCardsPanel = cardsPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		myCardLayout.first(myCardsPanel);
	}


}
