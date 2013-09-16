package org.amse.fedotov.noplag.lexer.pascal;

import org.amse.fedotov.noplag.model.IToken;

/* package */ enum PascalToken implements IToken {
	ID, 
	ADD_OPERATION,                      // + 
	SUBSTRACT_OPERATION,                // -
	MULTIPLY_OPERATION,                 // *
	DIVISION_OPERATION,                 // /
	BRACKET_LEFT,                       // (
	BRACKET_RIGHT,                      // )
	BRACKET_ARRAY_LEFT,                 // [ 
	BRACKET_ARRAY_RIGHT,                // ] 
	COMMA,                              // ,
	CIRCUMFLEX,                         // ^
	AT,                                 // @
	AND, ARRAY, BEGIN, 
	CASE, CONST, DIV, DOWNTO, DO, ELSE, END, 
	FILE, FOR, FUNCTION, GOTO, IF, IN, LABEL, 
	MOD, NIL, NOT, OF, OR, PACKED, PROCEDURE, 
	PROGRAM, RECORD, REPEAT, SET, THEN, TO, TYPE, 
	UNTIL, VAR, WHILE, WITH,
	VALUE,
	COMMENTS, 
	ASSIGN, 
	COMPARE,                            // > >= < <= <> =
	DOT,                                // .
	DOT_DOT;                            // ..

	public int getId() {
		return this.ordinal();
	}                           
}
