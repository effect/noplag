package org.amse.fedotov.noplag.comparator.two;

import org.amse.fedotov.noplag.model.IProgram;

/**
 * Compares two programs on similarity. 
 * @author Pavel Fedotov
 *
 * @param <T> result type. 
 */
public interface IProgramComparator<T extends IResult<? super T>> {
	
	/**
	 * Compares two programs on similarity.
	 * @param prog1 first program. 
	 * @param prog2 second program.
	 * @return similarity of programs. 
	 */
	public T compare(IProgram prog1, IProgram prog2);

}
