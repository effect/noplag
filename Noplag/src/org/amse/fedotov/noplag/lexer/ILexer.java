package org.amse.fedotov.noplag.lexer;

import java.io.IOException;

import org.amse.fedotov.noplag.model.IToken;

/**
 * This interface represents the lexer for programming language.
 * @author Pavel Fedotov
 *
 */
public interface ILexer {

	/**
	 * Gets next lexeme of the program. 
	 * @return next token.
	 * 
	 * @throws IOException if an I/O error occured.
	 * @throws NullPointerException if end of stream occured.
	 */
	public IToken nextToken() throws IOException ;
	
	/**
	 * Tests if stream contains at least one lexeme.
	 * @return <code>true</code> if and only if stream contains at least
	 * one lexeme; <code>false</code> otherwise (it means that end of 
	 * stream occured).
	 * 
	 * @throws IOException if an I/O error occured. 
	 */
	public boolean hasNextToken() throws IOException;
}
