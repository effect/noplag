package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
/* package */ class AuthorsComboBox extends JComboBox implements IListener {

	public AuthorsComboBox(final JButton ok) {
		setEditable(true);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("comboBoxEdited")) {
					ok.doClick();
				}
			}
		});
	}
	
	public void update() {
		Object selected = getModel().getSelectedItem();
		IAuthor[] authors = Settings.getDatabase().getAuthors().toArray(new IAuthor[0]);
		Arrays.sort(authors);
		setModel(new DefaultComboBoxModel(authors));
		getModel().setSelectedItem(selected);
	}
	
}