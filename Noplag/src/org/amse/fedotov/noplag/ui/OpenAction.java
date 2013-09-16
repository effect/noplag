package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.in_memory_program.InMemoryProgramFactory;
import org.amse.fedotov.noplag.lexer.pascal.PascalLexer;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
public class OpenAction extends AbstractAction {

	private static final String TEXT = "Open...";

	private final ContentManager myManager;
	private final JFileChooser myFileChooser;
	private final AuthorsDialog myAuthorsDialog;

	public OpenAction(ContentManager manager, JFileChooser fileChooser, AuthorsDialog authorsDialog) {
		putValue(SMALL_ICON, Settings.getIcon("open"));
		putValue(SHORT_DESCRIPTION, TEXT);
		myManager = manager;
		myFileChooser = fileChooser;
		myAuthorsDialog = authorsDialog;
		myManager.setAuthorsListener(myAuthorsDialog.getAuthorsListener());
	}

	public void actionPerformed(ActionEvent e) {
		int res = myFileChooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = myFileChooser.getSelectedFile();
			try {
				Settings.setInputFilesDirectory(file.getParentFile().getCanonicalPath());
				Settings.save();
				myAuthorsDialog.setOkPressed(false);
				myAuthorsDialog.setVisible(true);
				if (!myAuthorsDialog.isOkPressed()) {
					return;
				}
				IAuthor author = myAuthorsDialog.getSelectedAuthor();
				myManager.setProgram(InMemoryProgramFactory.getInstance()
						.create(author, file.getCanonicalPath(),
								FileUtils.createReader(file),
								new PascalLexer(FileUtils.createReader(file))));
			} catch (UnsupportedEncodingException ex) {
				JOptionPane.showMessageDialog(null, "Encoding of file not supported", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "I/O exception occured", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}