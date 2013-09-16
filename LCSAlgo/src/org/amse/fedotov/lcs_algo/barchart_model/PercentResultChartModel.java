package org.amse.fedotov.lcs_algo.barchart_model;

import java.util.ArrayList;
import java.util.List;

import org.amse.fedotov.barchart.IValue;
import org.amse.fedotov.lcs_algo.comparator.PercentResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.ui.MainFrame;
import org.amse.fedotov.noplag.ui.ProgramsFrame;
import org.amse.fedotov.noplag.ui.barchart_models.ProgramResultChartModel;

public class PercentResultChartModel extends ProgramResultChartModel {
	
	private static final double MAX_PERCENTS = 100.0;
	private final List<PercentResultWrapper> myValues;
	private final IProgram myProgram;
	
	public PercentResultChartModel(IAlgorithmResults<PercentResult> results, IProgram program) {
		List<PercentResultWrapper> values = new ArrayList<PercentResultWrapper>();
		for (IAlgorithmResult<PercentResult> r : results.getResults()) {
			values.add(new PercentResultWrapper(r));
		}
		myValues = values;
		myProgram = program;
	}
	
	public void click(int index) {
		IAlgorithmResult<PercentResult> result = myValues.get(index).getPercentResult();
		IProgram similarProgram = result.getSimilarProgram();
		ProgramsFrame progFrame = MainFrame.getProgramsFrame();
		progFrame.setPrograms(myProgram, similarProgram);
		progFrame.setVisible(true);
	}

	public List<? extends IValue> getValues() {
		return myValues;
	}


	public double getMaxValue() {
		return MAX_PERCENTS;
	}
	
}
