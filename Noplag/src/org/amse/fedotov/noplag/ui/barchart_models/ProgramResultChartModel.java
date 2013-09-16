package org.amse.fedotov.noplag.ui.barchart_models;

import org.amse.fedotov.barchart.IBarChartModel;
import org.amse.fedotov.barchart.IBarChartModelListener;
import org.amse.fedotov.noplag.model.IProgram;

public abstract class ProgramResultChartModel implements IBarChartModel {
	
	protected IBarChartModelListener myModelListener;
	protected IProgram myProgram;
	
	public void setProgram(IProgram program) {
		myProgram = program;
	}
	
	public void setModelListener(IBarChartModelListener listener) {
		myModelListener = listener;
	}

}
