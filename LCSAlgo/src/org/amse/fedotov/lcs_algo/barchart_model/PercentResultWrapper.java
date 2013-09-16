package org.amse.fedotov.lcs_algo.barchart_model;

import org.amse.fedotov.barchart.IValue;
import org.amse.fedotov.lcs_algo.comparator.PercentResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;

/* package */ class PercentResultWrapper implements IValue {
	
	private final IAlgorithmResult<PercentResult> myResult;

	public PercentResultWrapper(IAlgorithmResult<PercentResult> result) {
		myResult = result;
	}

	public double getValue() {
		return myResult.getResult().getValue();
	}
	
	public IAlgorithmResult<PercentResult> getPercentResult() {
		return myResult;
	}
}
