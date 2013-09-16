package org.amse.fedotov.noplag.plugin_api;

import javax.swing.JComponent;

import org.amse.fedotov.noplag.comparator.all.IAlgorithmResults;
import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;

public interface IAlgorithmPlugin<T extends IResult<? super T>> {
	
	public IProgramComparator<T> getProgramComparator();
	
	public JComponent getResultsView(IAlgorithmResults<T> results, IProgram program);
	
	public String getPluginActionName();
	
	public String getFullPluginActionName();

}
