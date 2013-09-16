package org.amse.fedotov.noplag.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.amse.fedotov.noplag.comparator.all.ICompareAlgorithm;

@SuppressWarnings("serial")
/* package */ class ProgressDialog extends JDialog {
	
	private static final String DIALOG_TITLE = "Processing...";
	private static final String DIALOG_TEXT = "Processing...";
	private static final int DIALOG_WIDTH = 300;
	private static final int DIALOG_HEIGHT = 100;
	
	private final JProgressBar myProgress = new JProgressBar();
	private ICompareAlgorithm<?> myAlgorithm;
	private WindowListener myWindowListener;

	public ProgressDialog() {
		setTitle(DIALOG_TITLE);
		setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		getContentPane().setLayout(new BorderLayout());
		add(new JLabel(DIALOG_TEXT), BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(new JButton(new CancelAction()));
		add(myProgress, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public JProgressBar getProgress() {
		return myProgress;
	}
	
	public void setAlgorithm(ICompareAlgorithm<?> algorithm) {
		removeWindowListener(myWindowListener);
		myAlgorithm = algorithm;
		myWindowListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAlgorithm.breakAlgorithm();
			}
		};
		addWindowListener(myWindowListener);
	}
	
	private class CancelAction extends AbstractAction {
		private static final String NAME = "Cancel";
		
		private CancelAction() {
			super(NAME);
		}
		
		public void actionPerformed(ActionEvent e) {
			myAlgorithm.breakAlgorithm();
			ProgressDialog.this.dispose();
		}
	}

}
