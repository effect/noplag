package org.amse.fedotov.noplag.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.impl.Author;

@SuppressWarnings("serial")
/* package */ class AuthorsDialog extends JDialog {
	
	private static final int DIALOG_WIDTH = 150;
	private static final int DIALOG_HEIGHT = 100;
	private static final String OK = "OK";
	
	private final JButton myOk = new JButton(OK);
	private final AuthorsComboBox myComboBox = new AuthorsComboBox(myOk);
	private boolean myOkPressed;

	public AuthorsDialog() {
		setTitle("Author");
		setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		JPanel comboPanel = new JPanel();
		comboPanel.add(new JLabel("Enter author: "));
		comboPanel.add(myComboBox);
		add(comboPanel, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(myOk);
		add(buttonPanel, BorderLayout.SOUTH);
		setModal(true);
		pack();
		setLocationRelativeTo(null);
		addButtonListeners(myOk);
		myComboBox.update();
	}
	
	public IAuthor getSelectedAuthor() {
		if (myComboBox.getSelectedItem() instanceof IAuthor) {
			return (IAuthor) myComboBox.getSelectedItem();
		} else {
			String authorName = (String) myComboBox.getSelectedItem();
			return new Author(authorName);
		}
	}
	
	private void addButtonListeners(final JButton ok) {
		ok.addActionListener(new CloseOnClick());
		ok.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ok.doClick();
				}
			}
		});
	}
	
	public void setOkPressed(boolean value) {
		myOkPressed = value;
	}
	
	public boolean isOkPressed() {
		return myOkPressed;
	}
	
	public IListener getAuthorsListener() {
		return myComboBox;
	}
	
	public void update() {
		getAuthorsListener().update();
	}
	
	private class CloseOnClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AuthorsDialog.this.dispose();
			myOkPressed = true;
		}
	}
	
}
