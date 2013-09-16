package org.amse.fedotov.noplag.comparator.all.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.settings.Settings;

public class AlgorithmResultsBuilder<T extends IResult<? super T>> {
	
	private final int myNumber;
	private final List<AlgorithmResult<T>> myResults;

	/* package */ AlgorithmResultsBuilder() {
		myNumber = Settings.getNumberSimilar();
		myResults = new ArrayList<AlgorithmResult<T>>(myNumber);
	}
	
	/* package */ void addResult(AlgorithmResult<T> result) {
		if (myResults.size() < myNumber) {
			myResults.add(result);
		} else {
			for (int i = 0; i < myNumber; i++) {
				if (result.compareTo(myResults.get(i)) < 0) {
					myResults.set(i, result);
					break;
				}
			}
		}
	}
	
	/* package */ IAlgorithmResults<T> getAlgorithmResults() {
		Collections.sort(myResults);
		IAlgorithmResults<T> res = new AlgorithmResults<T>(myResults); 
		return res;
	}
	
}
