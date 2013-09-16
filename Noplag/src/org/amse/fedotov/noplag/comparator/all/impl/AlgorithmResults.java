package org.amse.fedotov.noplag.comparator.all.impl;

import java.util.Collections;
import java.util.List;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.two.IResult;

public class AlgorithmResults<T extends IResult<? super T>> implements IAlgorithmResults<T> {
	
	private final List<IAlgorithmResult<T>> myResults;
	
	/* package */ AlgorithmResults(List<? extends IAlgorithmResult<T>> results) {
		myResults = Collections.unmodifiableList(results);
	}

	public List<IAlgorithmResult<T>> getResults() {
		return myResults;
	}
	
}
