package org.amse.fedotov.noplag.comparator.all;

import org.amse.fedotov.noplag.comparator.two.IResult;

/**
 * Listens for work of some algorithm. It keeps information about 
 * status of algorithm work. 
 * @author Pavel Fedotov
 *
 */
public interface IAlgorithmWorkListener<T extends IResult<? super T>> {
	
	/**
	 * Increase number of done operations on some value.  
	 * @param value number of done operations. 
	 */
	public void increaseDone(int value);
	
	/**
	 * Set number of all operations. 
	 * @param value number of all operations. 
	 */
	public void setMax(int value);
	
	/**
	 * Set results of algorithm work.
	 * @param results results of algorithm work.
	 */
	public void setResults(IAlgorithmResults<T> results);

}
