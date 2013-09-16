package org.amse.fedotov.lcs_algo;

import javax.swing.JComponent;

import org.amse.fedotov.barchart.BarChartPanel;
import org.amse.fedotov.lcs_algo.barchart_model.PercentResultChartModel;
import org.amse.fedotov.lcs_algo.comparator.ComparatorBasedOnLongestCommonSubsequence;
import org.amse.fedotov.lcs_algo.comparator.PercentResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;

public class AlgorithmPlugin implements IAlgorithmPlugin<PercentResult> {
	
	private static final IProgramComparator<PercentResult> myProgramComparator = new ComparatorBasedOnLongestCommonSubsequence();

	public IProgramComparator<PercentResult> getProgramComparator() {
		return myProgramComparator;
	}

	public JComponent getResultsView(IAlgorithmResults<PercentResult> results, IProgram program) {
		return new BarChartPanel(new PercentResultChartModel(results, program));
	}
	
	public String getPluginActionName() {
		return "LCS";
	}
	
	public String getFullPluginActionName() {
		return "Longest Common Subsequence Algorithm";
	}


}