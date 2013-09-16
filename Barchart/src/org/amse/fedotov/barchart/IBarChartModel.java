package org.amse.fedotov.barchart;

import java.util.List;

/**
 * This interface declares methods for barchart listeners. 
 * @author Pavel Fedotov
 *
 */
public interface IBarChartModel {
	
	/**
	 * Action performed when click on index-th item. 
	 * @param index number of column that was clicked. 
	 */
	public void click(int index);
	
	/**
	 * Returns list of values. 
	 */
	public List<? extends IValue> getValues();
	
	/**
	 * Returns maximal available value.
	 * @return maximal available value.
	 */
	public double getMaxValue();
	
}
