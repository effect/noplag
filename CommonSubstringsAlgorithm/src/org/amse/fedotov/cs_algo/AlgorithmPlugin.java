package org.amse.fedotov.cs_algo;

import javax.swing.JComponent;

import org.amse.fedotov.barchart.BarChartPanel;
import org.amse.fedotov.cs_algo.barchart_model.CommonSubstringResultChartModel;
import org.amse.fedotov.cs_algo.comparator.CommonSubstringsResult;
import org.amse.fedotov.cs_algo.comparator.ComparatorBasedOnCommonSubstrings;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.plugin_api.IAlgorithmPlugin;

public class AlgorithmPlugin implements IAlgorithmPlugin<CommonSubstringsResult> {
	
	private static final IProgramComparator<CommonSubstringsResult> myProgramComparator = new ComparatorBasedOnCommonSubstrings();

	public IProgramComparator<CommonSubstringsResult> getProgramComparator() {
		return myProgramComparator;
	}

	public JComponent getResultsView(IAlgorithmResults<CommonSubstringsResult> results, IProgram program) {
		return new BarChartPanel(new CommonSubstringResultChartModel(results, program));
	}
	
	public String getPluginActionName() {
		return "ACS";
	}
	
	public String getFullPluginActionName() {
		return "All Common Substrings Algorithm";
	}

}
