package org.amse.fedotov.noplag.model;

import java.util.List;

/**
 * This interface represents a program.
 * 
 * @author Pavel Fedotov
 */
public interface IProgram {

	/**
	 * Returns filename of the program. This is name of file that was
	 * initially added to the database.
	 * @return filename of the program.
	 */
	public String getFilename();

	/**
	 * Returns a sequence of bytes that forms source file in UTF-8 encoding.    
	 * @return a sequence of bytes that forms source file in UTF-8 encoding.  
	 */
	public byte[] getSource();

	/**
	 * Returns list of tokens (unmodifiable).
	 * 
	 * @return list of tokens (unmodifiable).
	 */
	public List<IToken> getTokens();

	/**
	 * Returns author of the program.
	 * 
	 * @return author of the program.
	 */
	public IAuthor getAuthor();

}
