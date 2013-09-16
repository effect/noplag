package org.amse.fedotov.barchart;

import javax.swing.JPanel;

public class PanelUpdater implements IBarChartModelListener {
	
	private final JPanel myPanel;

	public PanelUpdater(final JPanel myPanel) {
		this.myPanel = myPanel;
	}

	public void modelChanged() {
		myPanel.repaint();
	}

}
