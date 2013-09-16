package org.amse.fedotov.noplag.comparator.all.impl;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmWorkListener;
import org.amse.fedotov.noplag.comparator.all.ICompareAlgorithm;
import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.program_storage.IProgramStorage;

public class NaiveCompareAlgorithm<T extends IResult<? super T>> implements ICompareAlgorithm<T> {
	
	private boolean isNeedToBreak;
	
	public void run(IProgram program, IProgramStorage programStorage, IProgramComparator<T> comparator, IAlgorithmWorkListener<T> workListener) {
		isNeedToBreak = false;
		AlgorithmResultsBuilder<T> resultsBuilder = new AlgorithmResultsBuilder<T>();
		workListener.setMax(programStorage.getSize());
		synchronized (programStorage) {
			for (IProgram p : programStorage) {
				if (isNeedToBreak) {
					return;
				}
				T curResult = comparator.compare(p, program);
				resultsBuilder.addResult(new AlgorithmResult<T>(curResult, p));
				workListener.increaseDone(1);
			}
		}
		workListener.setResults(resultsBuilder.getAlgorithmResults());
	}

	public void breakAlgorithm() {
		isNeedToBreak = true;
	}
	
}
