package org.amse.fedotov.noplag.program_storage;

import java.util.Set;

import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.program_storage.impl.ProgramStorage;

/**
 * This is inteface for storage of programs.
 * @author Pavel Fedotov
 * @see ProgramStorage
 */
public interface IProgramStorage extends Iterable<IProgram> {

	/**
	 * Adds a program to the program storage.
	 * @param program adding program. 
	 * @throws BadInputException
	 */
	public void addProgram(IProgram program);
	
	/**
	 * Returns set of authors in database.
	 * @return set of authors in database
	 */
	public Set<IAuthor> getAuthors();
	
	/**
	 * Returns number of programs in storage. 
	 * @return number of programs in storage. 
	 */
	public int getSize();

}
