package org.amse.fedotov.noplag.lexer.pascal;

import org.amse.fedotov.noplag.model.IToken;
import org.amse.fedotov.noplag.model.ITokenFactory;

public class PascalTokenFactory implements ITokenFactory {
	
	private static final PascalTokenFactory myFactory = new PascalTokenFactory();
	
	private PascalTokenFactory() {
	}
	
	public static PascalTokenFactory getInstance() {
		return myFactory;
	}
	 
	public IToken createToken(int id) {
		return PascalToken.values()[id];
	}

}
