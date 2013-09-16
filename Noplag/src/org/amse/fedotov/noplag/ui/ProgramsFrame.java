package org.amse.fedotov.noplag.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.amse.fedotov.noplag.model.IProgram;

@SuppressWarnings("serial")
public class ProgramsFrame extends JFrame {
	
	private static final String TITLE = "Sources";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int X = 50;
	private static final int Y = 0;
	
	private final SourcePanel myLeftPanel;
	private final SourcePanel myRightPanel;
	
	/* package */ ProgramsFrame() {
		super(TITLE);
		setLocation(X, Y);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		JPanel contentPane = new JPanel(new GridLayout(1, 2, 0, 0));
		setContentPane(contentPane);
		
		myLeftPanel = new SourcePanel();
		myRightPanel = new SourcePanel();
		myLeftPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
		myRightPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
		contentPane.add(myLeftPanel);
		contentPane.add(myRightPanel);
		pack();
	}
	
//	public ProgramsFrame(IProgram original, IProgram similar) {
//		this();
//		setPrograms(original, similar);
//	}

	public void setPrograms(IProgram original, IProgram similar) {
		myLeftPanel.setProgram(original);
		myRightPanel.setProgram(similar);
	}
}
