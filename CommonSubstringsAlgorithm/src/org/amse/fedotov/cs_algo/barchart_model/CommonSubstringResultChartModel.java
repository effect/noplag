package org.amse.fedotov.cs_algo.barchart_model;

import java.util.ArrayList;
import java.util.List;

import org.amse.fedotov.barchart.IValue;
import org.amse.fedotov.cs_algo.comparator.CommonSubstringsResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResult;
import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.ui.MainFrame;
import org.amse.fedotov.noplag.ui.ProgramsFrame;
import org.amse.fedotov.noplag.ui.barchart_models.ProgramResultChartModel;

public class CommonSubstringResultChartModel extends ProgramResultChartModel {
	
	private final List<CommonSubstringsResultWrapper> myValues;
	private final IProgram myProgram;
	
	public CommonSubstringResultChartModel(IAlgorithmResults<CommonSubstringsResult> results, IProgram program) {
		List<CommonSubstringsResultWrapper> values = new ArrayList<CommonSubstringsResultWrapper>();
		for (IAlgorithmResult<CommonSubstringsResult> r : results.getResults()) {
			values.add(new CommonSubstringsResultWrapper(r));
		}
		myValues = values;
		myProgram = program;
	}
	
	public void click(int index) {
		IAlgorithmResult<CommonSubstringsResult> result = myValues.get(index).getAlgorithmResult();
		IProgram similarProgram = result.getSimilarProgram();
		ProgramsFrame progFrame = MainFrame.getProgramsFrame();
		progFrame.setPrograms(myProgram, similarProgram);
		progFrame.setVisible(true);
	}
	
	public List<? extends IValue> getValues() {
		return myValues;
	}

	public double getMaxValue() {
		List<? extends IValue> values = getValues();
		double max = -1;
		for (IValue value : values) {
			if (max < value.getValue()) {
				max = value.getValue();
			}
		}
		return max;
	}
	
}
