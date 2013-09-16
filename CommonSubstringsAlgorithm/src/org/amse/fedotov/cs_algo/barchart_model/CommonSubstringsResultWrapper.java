package org.amse.fedotov.cs_algo.barchart_model;

import org.amse.fedotov.barchart.IValue;
import org.amse.fedotov.cs_algo.comparator.CommonSubstringsResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;

public class CommonSubstringsResultWrapper implements IValue {
	
	private final IAlgorithmResult<CommonSubstringsResult> myResult;
	
	public CommonSubstringsResultWrapper(IAlgorithmResult<CommonSubstringsResult> result) {
		myResult = result;
	}

	public double getValue() {
		return myResult.getResult().getValue();
	}
	
	public IAlgorithmResult<CommonSubstringsResult> getAlgorithmResult() {
		return myResult;
	}

}
