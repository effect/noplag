package org.amse.fedotov.noplag.ui;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmWorkListener;
import org.amse.fedotov.noplag.comparator.two.IResult;

public class AlgorithmWorkListener<T extends IResult<? super T>> implements IAlgorithmWorkListener<T> {
	
	private final JProgressBar myProgress;
	private final AlgorithmMode<T> myAlgoMode;

	public AlgorithmWorkListener(JProgressBar progress, AlgorithmMode<T> algoMode) {
		this.myProgress = progress;
		this.myAlgoMode = algoMode;
	}

	public void setMax(final int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myProgress.setMaximum(value);
			}
		});
	}

	public void setDone(final int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myProgress.setValue(value);
			}
		});
	}

	public void setResults(final IAlgorithmResults<T> results) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myAlgoMode.setAlgorithmResults(results);
			}
		});
	}

	public void increaseDone(final int value) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				myProgress.setValue(myProgress.getValue() + value);
			}
		});
	}

}
