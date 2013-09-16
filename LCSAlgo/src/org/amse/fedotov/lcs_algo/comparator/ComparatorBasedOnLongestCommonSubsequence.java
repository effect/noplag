package org.amse.fedotov.lcs_algo.comparator;

import java.util.List;

import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.IToken;

public class ComparatorBasedOnLongestCommonSubsequence implements
		IProgramComparator<PercentResult> {

	public PercentResult compare(IProgram prog1, IProgram prog2) {
		// check if both programs are empty
		if (prog1.getTokens().size() + prog2.getTokens().size() == 0) {
			return new PercentResult(0);
		}
		int lcs = getLengthLCS(prog1.getTokens(), prog2.getTokens());
		double res = 100.0 * (2 * lcs) / (prog1.getTokens().size() + prog2.getTokens().size());
		return new PercentResult(res);
	}
	
	private int getLengthLCS(List<IToken> a, List<IToken> b) {
		int n = a.size();
		int m = b.size();
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a.get(i - 1) == b.get(j - 1)) {
					d[i][j] = d[i - 1][j - 1] + 1;
				} else if (d[i - 1][j] >= d[i][j - 1]) {
					d[i][j] = d[i - 1][j];
				} else {
					d[i][j] = d[i][j - 1];
				}
			}
		}
		return d[n][m];
	}


}
