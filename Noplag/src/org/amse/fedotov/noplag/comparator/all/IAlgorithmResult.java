package org.amse.fedotov.noplag.comparator.all;

import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;

/**
 * {@link IAlgorithmResult} contains info about comparing one program with 
 * other one. It contains program comparator result and similar program.  
 * @author Pavel Fedotov
 *
 * @param <T> program comparator result type. 
 */
public interface IAlgorithmResult<T extends IResult<? super T>> {

	/**
	 * Returns similar program. 
	 * @return similar program.
	 */
	public IProgram getSimilarProgram();
	
	/**
	 * Returns program comparator result.
	 * @return program comparator result.
	 */
	public T getResult();
}
