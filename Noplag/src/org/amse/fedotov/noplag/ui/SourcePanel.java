package org.amse.fedotov.noplag.ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
/* package */ class SourcePanel extends JPanel {
	
	private static final int FONTSIZE = 10;
	
	protected SourcePanel() {
	}
	
	public SourcePanel(IProgram program) {
		decoratePanel(program);
	}
	
	public SourcePanel(ContentManager contentManager) {
		contentManager.setProgramListener(new IProgramListener() {
			public void update(IProgram program) {
				decoratePanel(program);
			}
		});
	}
	
	public void setProgram(IProgram program) {
		decoratePanel(program);
	}
	
	protected void decoratePanel(IProgram program) {
		removeAll();
		setLayout(new GridLayout());
		JTextArea textArea = createTextArea(program);
		
		JScrollPane scrollPane = new JScrollPane(textArea, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane);	
		
		textArea.addMouseListener(new SourcePanelMouseListener(program));
		validate();
	}
	
	private JTextArea createTextArea(IProgram program) {
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, FONTSIZE));
		textArea.setEditable(false);
		try {
			textArea.append(getSource(program));
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		textArea.setCaretPosition(0);
		return textArea;
	}

	private String getSource(IProgram program) throws UnsupportedEncodingException {
		String source = new String(program.getSource(), Settings.getInternalCharset());
		return source;
	}
	
	private class SourcePanelMouseListener extends MouseAdapter {
		
		private final IProgram myProgram;
		
		private SourcePanelMouseListener(IProgram program) {
			myProgram = program;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				JOptionPane.showMessageDialog(SourcePanel.this, 
						getProgramInfo(myProgram), "Program info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		private String getProgramInfo(IProgram program) {
			String info = "";
			info += "Author: " + program.getAuthor().getName() + "\n";
			info += "Filename: " + program.getFilename();
			return info;
		}
		
	}

}
