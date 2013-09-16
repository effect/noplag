package org.amse.fedotov.noplag.ui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;

/* package */ class ContentManager {
	
	private final JPanel myResultsPanel;
	private JComponent myCurrentView;
	private IProgram myProgram;
	private IProgramListener myProgramListener;
	private AlgorithmMode<? extends IResult<?>> myCurrentAlgoMode;
	private IListener myAuthorsListener;
	
	public ContentManager(JPanel panel) {
		myResultsPanel = panel;
	}
	
	public JPanel getResultsPanel() {
		return myResultsPanel;
	}
	
	public void setProgram(IProgram program) {
		myProgram = program;
		notifyListener();
		myCurrentAlgoMode.runAlgorithm();
	}
	
	public IProgram getProgram() {
		return myProgram;
	}
	
	public void setView(JComponent view) {
		if (getView() != null) {
			getResultsPanel().removeAll();
		}
		myCurrentView = view;
		getResultsPanel().add(getView(), BorderLayout.CENTER);
		getResultsPanel().updateUI();
	}
	
	public JComponent getView() {
		return myCurrentView;
	}
	
	public void setProgramListener(IProgramListener programListener) {
		myProgramListener = programListener;
	}
	
	private void notifyListener() {
		myProgramListener.update(myProgram);
	}
	
	public void setAlgoMode(AlgorithmMode<? extends IResult<?>> algoMode) {
		myCurrentAlgoMode = algoMode;
	}
	
	public void setAuthorsListener(IListener authorsListener) {
		myAuthorsListener = authorsListener;
	}
	
	public void updateAuthors() {
		myAuthorsListener.update();
	}
	
}
