package org.amse.fedotov.noplag.model;

/**
 * This interface represents author of program. 
 * @author Pavel Fedotov
 * @see Author
 */
public interface IAuthor extends Comparable<IAuthor> {

	/**
	 * Returns name (full info) of author. 
	 * @return name (full info) of author. 
	 */
	public String getName();
}
