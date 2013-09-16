package org.amse.fedotov.cs_algo.comparator;

import java.util.Collections;
import java.util.List;

import org.amse.fedotov.noplag.comparator.two.IResult;

public class CommonSubstringsResult implements IResult<CommonSubstringsResult> {
	
	private final List<List<CommonSubstringPart>> myCommonLists;
	private final double mySimilarityValue;
	private final int[] myNumberSimilarBlocks;
	private final double[] myWeights;
	
	/* package */ CommonSubstringsResult(List<List<CommonSubstringPart>> commonLists) {
		myCommonLists = Collections.unmodifiableList(commonLists);
		myNumberSimilarBlocks = calculateNumberSimilarBlocks();
		myWeights = calculateWeights();
		mySimilarityValue = calculateSimilarityValue();
	}
	
	private int[] calculateNumberSimilarBlocks() {
		int maxLength = -1;
		for (List<CommonSubstringPart> list : myCommonLists) {
			for (CommonSubstringPart part : list) {
				int length = part.getLength();
				if (maxLength < length) {
					maxLength = length;
				}
			}
		}
		int[] res = new int[maxLength + 1];
		for (List<CommonSubstringPart> list : myCommonLists) {
			for (CommonSubstringPart part : list) {
				int length = part.getLength();
				res[length]++;
			}
		}
		return res;
	}
	
	private double[] calculateWeights() {
		double[] weights = new double[myNumberSimilarBlocks.length];
		for (int i = 0; i < weights.length; i++) {
			weights[i] = i * i * i;
		}
		return weights;
	}
	
	private double calculateSimilarityValue() {
		double value = 0;
		for (int i = 0; i < myNumberSimilarBlocks.length; i++) {
			value += myWeights[i] * myNumberSimilarBlocks[i];
		}
		return value;
	}

	public int compareTo(CommonSubstringsResult o) {
		return -Double.compare(mySimilarityValue, o.mySimilarityValue);
	}

	public double getValue() {
		return mySimilarityValue;
	}
	
	public int[] getNumberSimilarBlocks() {
		return myNumberSimilarBlocks.clone();
	}
	
}
