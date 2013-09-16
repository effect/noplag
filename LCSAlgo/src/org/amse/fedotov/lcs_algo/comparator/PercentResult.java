package org.amse.fedotov.lcs_algo.comparator;

import org.amse.fedotov.noplag.comparator.two.IResult;

public class PercentResult implements IResult<PercentResult> {
	
	private final double myPercents;
	
	/* package */ PercentResult(double percents) {
		myPercents = percents;
	}
	
	/**
	 * This result better then res if this.myPercents > res.myPercents.
	 */
	public int compareTo(PercentResult res) {
		return -Double.compare(this.myPercents, res.myPercents);
	}

	public double getValue() {
		return myPercents;
	}

}
