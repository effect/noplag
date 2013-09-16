package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
public class SettingsAction extends AbstractAction {
	
	private static final String TITLE = "Settings...";
	private final SettingsDialog mySettingsDialog = new SettingsDialog();
	private final AuthorsDialog myAuthorsDialog;
	
	public SettingsAction(AuthorsDialog authorsDialog) {
		super(TITLE);
		myAuthorsDialog = authorsDialog;
	}

	public void actionPerformed(ActionEvent e) {
		mySettingsDialog.setVisible(true);
	}
	
	private class SettingsDialog extends JDialog {
		
		private static final String TITLE = "Settings";
		private static final int WIDTH = 300;
		private static final int HEIGHT = 130;
		
		private final JTextField myEncodingText = new JTextField(Settings.getInputFileEncoding(), 10);  
		private final JTextField myNumberText = new JTextField(Integer.toString(Settings.getNumberSimilar()), 2);
		private final JTextField myStoragePath = new JTextField(Settings.getNoplagDataPath(), 10);
		
		private final JButton myOkButton = new JButton(new OkAction());
		private final EnterListener myEnterListener = new EnterListener(myOkButton);
		
		public SettingsDialog() {
			setTitle(TITLE);
			setSize(WIDTH, HEIGHT);
			setResizable(false);
						
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			decorateField("Encoding: ", myEncodingText, panel);
			decorateField("Number similar programs to show: ", myNumberText, panel);
			decorateField("Storage path: ", myStoragePath, panel);
			
			JPanel okPanel = new JPanel();
			okPanel.add(myOkButton);
			panel.add(okPanel);
			setContentPane(panel);
			
			myOkButton.addKeyListener(myEnterListener);
			pack();
			setLocationRelativeTo(null);
		}
		
		private void decorateField(String labelText, JTextField textField, JPanel layer) {
			JPanel panel = new JPanel();
			panel.add(new JLabel(labelText));
			panel.add(textField);
			layer.add(panel);
			textField.addKeyListener(myEnterListener);
		}
		
		private class OkAction extends AbstractAction {
			
			public OkAction() {
				super("OK");
				
			}

			public void actionPerformed(ActionEvent e) {
				boolean correctEncoding = Settings.setInputFileEncoding(myEncodingText.getText());
				boolean correctNumber = Settings.setNumberSimilar(myNumberText.getText());
				boolean correctStorage = Settings.setNoplagDataPath(myStoragePath.getText());
				if (correctEncoding && correctNumber && correctStorage) { 
					SettingsDialog.this.dispose();
					Settings.save();
					myAuthorsDialog.update();
					return;
				} 
				String errorMessage = "";
				if (!correctEncoding) {
					errorMessage += "Unknown encoding\n";
					myEncodingText.setText(Settings.getInputFileEncoding());
				}
				if (!correctNumber) {
					errorMessage += "Incorrect number\n";
					myNumberText.setText(Integer.toString(Settings.getNumberSimilar()));
				}
				if (!correctStorage) {
					errorMessage += "Incorrect storage path\n";
					myStoragePath.setText(Settings.getNoplagDataPath());
				}
				JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private class EnterListener extends KeyAdapter {
			
			private final JButton myButton;
			
			public EnterListener(JButton button) {
				myButton = button;
			}
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					myButton.doClick();					
				}
			}
		}
		
	}

}
