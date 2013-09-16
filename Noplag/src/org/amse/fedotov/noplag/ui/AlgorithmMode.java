package org.amse.fedotov.noplag.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmWorkListener;
import org.amse.fedotov.noplag.comparator.all.ICompareAlgorithm;
import org.amse.fedotov.noplag.comparator.all.impl.NaiveCompareAlgorithm;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;
import org.amse.fedotov.noplag.settings.Settings;

@SuppressWarnings("serial")
/* package */ class AlgorithmMode<T extends IResult<? super T>>  extends AbstractAction {
	
	private final ICompareAlgorithm<T> myAlgo = new NaiveCompareAlgorithm<T>();
	private final ContentManager myContentManager;
	private final IAlgorithmPlugin<T> myAlgorithmPlugin;
	private final ProgressDialog myProgressDialog = MainFrame.getProgressDialog();
	private final IAlgorithmWorkListener<T> myWorkListener = new AlgorithmWorkListener<T>(myProgressDialog.getProgress(), this);
	private IProgram myLastProgram;
	private IAlgorithmResults<T> myLastResults;
	
	public AlgorithmMode(IAlgorithmPlugin<T> algorithmPlugin, ContentManager manager) {
		putValue(NAME, algorithmPlugin.getPluginActionName());
		putValue(SHORT_DESCRIPTION, algorithmPlugin.getFullPluginActionName());
		myAlgorithmPlugin = algorithmPlugin;
		myContentManager = manager;
	}

	public void actionPerformed(ActionEvent e) {
		myContentManager.setAlgoMode(this);
		if (myContentManager.getProgram() == null) {
			return;
		}
		if (myLastProgram != myContentManager.getProgram() || myLastResults == null) {
			runAlgorithm();
		} else {
			setAlgorithmResults(myLastResults);
		}
	}
	
	public void runAlgorithm() {
		myProgressDialog.setAlgorithm(myAlgo);
		myProgressDialog.getProgress().setValue(0);
		myProgressDialog.setVisible(true);
		myLastProgram = myContentManager.getProgram();
		Thread thread = new Thread(new Runnable() {
			public void run() {
				myAlgo.run(myLastProgram, Settings.getDatabase(), 
						myAlgorithmPlugin.getProgramComparator(), myWorkListener);
			}
		});
		thread.start();
	}
	
	public void setAlgorithmResults(IAlgorithmResults<T> results) {
		myLastResults = results;
		myProgressDialog.dispose();
		myContentManager.setView(myAlgorithmPlugin.getResultsView(results, myContentManager.getProgram()));
	}

}
