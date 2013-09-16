package org.amse.fedotov.noplag.comparator.all.impl;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;

public class AlgorithmResult<T extends IResult<? super T>> implements IAlgorithmResult<T>, Comparable<AlgorithmResult<T>> {

	private final T myResult;
	private final IProgram mySimilarProgram;
	
	/* package */ AlgorithmResult(T result, IProgram similarProgram) {
		super();
		myResult = result;
		mySimilarProgram = similarProgram;
	}

	public int compareTo(AlgorithmResult<T> o) {
		return myResult.compareTo(o.myResult);
	}

	public IProgram getSimilarProgram() {
		return mySimilarProgram;
	}
	
	public T getResult() {
		return myResult;
	}

}
