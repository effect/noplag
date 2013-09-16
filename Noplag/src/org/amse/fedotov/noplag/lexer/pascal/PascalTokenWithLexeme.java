package org.amse.fedotov.noplag.lexer.pascal;

/* package */ class PascalTokenWithLexeme {
	private final String myLexeme;
	private final PascalToken myTokenType;
	
	/* package */ PascalTokenWithLexeme(String lexeme, PascalToken pascalToken) {
		myLexeme = lexeme;
		myTokenType = pascalToken;
	}
	
	/* package */ String getLexeme() {
		return myLexeme;
	}
	
	/* package */ PascalToken getType() {
		return myTokenType;
	}
}
