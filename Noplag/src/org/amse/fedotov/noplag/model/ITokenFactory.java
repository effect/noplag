package org.amse.fedotov.noplag.model;

/**
 * This interface represents factory that creates tokens. 
 * @author Pavel Fedotov
 *
 */
public interface ITokenFactory {

	/**
	 * Creates token with given id. 
	 * @param id id of token. 
	 * @return token with given id.
	 */
	public IToken createToken(int id);
}
