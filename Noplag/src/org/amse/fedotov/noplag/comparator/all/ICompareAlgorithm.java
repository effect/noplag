package org.amse.fedotov.noplag.comparator.all;

import org.amse.fedotov.noplag.comparator.two.IProgramComparator;
import org.amse.fedotov.noplag.comparator.two.IResult;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.program_storage.IProgramStorage;

/**
 * This algorithm allows to compare a program with all programs 
 * in the database on similarity. 
 * @author Pavel Fedotov
 *
 * @param <T> program comparator result type. 
 */
public interface ICompareAlgorithm<T extends IResult<? super T>> {
	
	/**
	 * Compares the program with all programs in the database. 
	 * @param program a program. 
	 * @param programStorage a database. 
	 * @param comparator a comparator that allows compare two programs.  
	 * @param workListener algorithm work status listener. 
	 */
	public void run(IProgram program, IProgramStorage programStorage, IProgramComparator<T> comparator, IAlgorithmWorkListener<T> workListener);
	
	/**
	 * Breaks algorithm work. 
	 *
	 */
	public void breakAlgorithm();

}
