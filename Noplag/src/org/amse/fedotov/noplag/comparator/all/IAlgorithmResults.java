package org.amse.fedotov.noplag.comparator.all;

import java.util.List;

import org.amse.fedotov.noplag.comparator.two.IResult;

/**
 * {@link IAlgorithmResults} contains list of results 
 * of compare algorithm working. 
 * @author Pavel Fedotov
 *
 * @param <T> program comparator result type. 
 */
public interface IAlgorithmResults<T extends IResult<? super T>> {

	/**
	 * Returns sorted list of compare algorithm results. 
	 * @return sorted list of compare algorithm results.
	 */
	public List<IAlgorithmResult<T>> getResults();
}
