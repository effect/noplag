package org.amse.fedotov.noplag.ui.barchart_models;

import java.util.List;

import org.amse.fedotov.barchart.IBarChartModel;
import org.amse.fedotov.barchart.IValue;

public class DistributionModel implements IBarChartModel {
	
	private List<IValue> myValues;
	
	public DistributionModel(List<IValue> values) {
		myValues = values;
	}

	public void click(int index) {
	}

	public List<? extends IValue> getValues() {
		return myValues;
	}

	public void setValues(List<IValue> values) {
		myValues = values;
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
