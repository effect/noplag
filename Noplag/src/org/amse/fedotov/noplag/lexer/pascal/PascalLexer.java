/*
Pascal - Syntax in BNF Notation:
http://www2.informatik.uni-halle.de/lehre/pascal/sprache/pas_bnf.html
*/

package org.amse.fedotov.noplag.lexer.pascal;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import org.amse.fedotov.noplag.lexer.ILexer;
import org.amse.fedotov.noplag.model.IToken;

public class PascalLexer implements ILexer {
	
	private final static char[] DELIMETERS = {' ', ';', ',', '\n', '\t', '\r'};

	private final static PascalToken[] TYPES_FOR_RESERVED_WORDS = {PascalToken.AND, PascalToken.ARRAY, PascalToken.BEGIN, 
		PascalToken.CASE, PascalToken.CONST, PascalToken.DIV, PascalToken.DOWNTO, PascalToken.DO, PascalToken.ELSE, PascalToken.END, 
		PascalToken.FILE, PascalToken.FOR, PascalToken.FUNCTION, PascalToken.GOTO, PascalToken.IF, PascalToken.IN, PascalToken.LABEL, 
		PascalToken.MOD, PascalToken.NIL, PascalToken.NOT, PascalToken.OF, PascalToken.OR, PascalToken.PACKED, PascalToken.PROCEDURE, 
		PascalToken.PROGRAM, PascalToken.RECORD, PascalToken.REPEAT, PascalToken.SET, PascalToken.THEN, PascalToken.TO, PascalToken.TYPE, 
		PascalToken.UNTIL, PascalToken.VAR, PascalToken.WHILE, PascalToken.WITH};
	
	private final static HashMap<String, PascalToken> RESERVED_WORDS; 
	static {
		RESERVED_WORDS = new HashMap<String, PascalToken>();
		for (PascalToken type : TYPES_FOR_RESERVED_WORDS) {
			RESERVED_WORDS.put(type.toString().toLowerCase(), type);
		}
	}

	private final Reader myReader;
	private State myState = State.START;
	private final StringBuilder myBuffer = new StringBuilder();
	private final StringBuilder myLexeme = new StringBuilder();
	private PascalToken myTokenType;
	private PascalTokenWithLexeme myToken;
	private char mySymbol = ' ';
	private boolean isEndOfInput = false;
	
	private enum State {
		START {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isLetter(symbol)) {
					lexeme.append(symbol);
					return State.WORD;
				} else if (isDigit(symbol)) {
					lexeme.append(symbol);
					return State.INT;
				} else if (isDelimeter(symbol)) {
					return this;
				} else {
					switch (symbol) {
					case '+': 
						lexer.myTokenType = PascalToken.ADD_OPERATION;
						lexeme.append(symbol);
						return State.START;
					case '-':
						lexer.myTokenType = PascalToken.SUBSTRACT_OPERATION;
						lexeme.append(symbol);
						return State.START;
					case '*':
						lexer.myTokenType = PascalToken.MULTIPLY_OPERATION;
						lexeme.append(symbol);
						return State.START;
					case '(':
						lexer.myTokenType = PascalToken.BRACKET_LEFT;
						lexeme.append(symbol);
						return State.START;
					case ')': 
						lexer.myTokenType = PascalToken.BRACKET_RIGHT;
						lexeme.append(symbol);
						return State.START;
					case '[':
						lexer.myTokenType = PascalToken.BRACKET_ARRAY_LEFT;
						lexeme.append(symbol);
						return State.START;
					case ']':
						lexer.myTokenType = PascalToken.BRACKET_ARRAY_RIGHT;
						lexeme.append(symbol);
						return State.START;
					case '^':
						lexer.myTokenType = PascalToken.CIRCUMFLEX;
						lexeme.append(symbol);
						return State.START;
					case '@':
						lexer.myTokenType = PascalToken.AT;
						lexeme.append(symbol);
						return State.START;
					case '<':
						lexeme.append(symbol);
						return State.LESS;
					case '>':
						lexeme.append(symbol);
						return State.GREATER;
					case '=':
						lexeme.append(symbol);
						return State.EQUAL;
					case ':': 
						lexeme.append(symbol);
						return State.COLON;
					case '/':
						lexeme.append(symbol);
						return State.SLASH;
					case '{':
						lexeme.append(symbol);
						return State.FIGURE_BRACKET;
					case '\'': 
						lexeme.append(symbol);
						return State.APOSTROPHE;
					case '"': 
						lexeme.append(symbol);
						return State.QOUTATION;
					case '.': 
						lexeme.append(symbol);
						return State.DOT;
					default: 
						lexeme.append(symbol);
						return this;
					}
				}
			}
		}, 
		WORD {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isLetter(symbol) || isDigit(symbol)) {
					lexeme.append(symbol);
					return this;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.ID;
					String cur = lexeme.toString().toLowerCase();
					if (RESERVED_WORDS.containsKey(cur)) {
						lexeme.delete(0, lexeme.length());
						lexeme.append(cur);
						lexer.myTokenType = RESERVED_WORDS.get(cur);				
					}
					return State.START;
				}
			}
		},
		INT {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return this;
				} else if (symbol == '.') {
					lexeme.append(symbol);
					return State.INT_DOT;
				} else if (isE(symbol)) {
					lexeme.append(symbol);
					return State.INT_E;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		INT_DOT {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return State.INT_DOT_INT;
				} else if (symbol == '.') {
					lexeme.deleteCharAt(lexeme.length() - 1);
					buffer.append("..");
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		},
		INT_DOT_INT {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return this;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		INT_E {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return State.INT_E_INT;
				} else if (isSign(symbol)) {
					lexeme.append(symbol);
					return State.INT_E_SIGN;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		INT_E_INT {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return this;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		INT_E_SIGN {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					lexeme.append(symbol);
					return State.INT_E_INT;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		SLASH {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '/') {
					lexeme.append(symbol);
					return State.SLASH_SLASH;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.DIVISION_OPERATION;
					return State.START;
				}
			}
		}, 
		SLASH_SLASH {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isEndOfLine(symbol)) {
					lexer.myTokenType = PascalToken.COMMENTS;
					return State.START;
				} else {
					lexeme.append(symbol);
					return this;
				}
			}
		}, 
		COLON {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '=') {
					lexeme.append(symbol);
					return State.ASSIGN;
				} else {
					buffer.append(symbol);
					// assume that colon is delimeter
					lexeme.delete(0, lexeme.length());
					return State.START;
				}
			}
		}, 
		ASSIGN {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (isDigit(symbol)) {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.ASSIGN;
					return State.INT;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.ASSIGN;
					return State.START;
				}
			}
		}, 
		GREATER {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '=') {
					lexeme.append(symbol);
					return State.GREATER_EQUAL;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.COMPARE;
					return State.START;
				}
			}
		}, 
		GREATER_EQUAL {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				buffer.append(symbol);
				lexer.myTokenType = PascalToken.COMPARE;
				return State.START;
			}
		}, 
		LESS {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '=') {
					lexeme.append(symbol);
					return State.LESS_EQUAL;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.COMPARE;
					return State.START;
				}
			}
		}, 
		LESS_EQUAL {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				buffer.append(symbol);
				lexer.myTokenType = PascalToken.COMPARE;
				return State.START;
			}
		}, 
		EQUAL {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				buffer.append(symbol);
				lexer.myTokenType = PascalToken.COMPARE;
				return State.START;
			}
		}, 
		NOT_EQUAL {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				buffer.append(symbol);
				lexer.myTokenType = PascalToken.COMPARE;
				return State.START;
			}
		},
		FIGURE_BRACKET {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '}') {
					lexeme.append(symbol);
					lexer.myTokenType = PascalToken.COMMENTS;
					return State.START;
				} else {
					lexeme.append(symbol);
					return this;
				}
			}
		}, 
		APOSTROPHE {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '\'') {
					return State.AFTER_APOSTROPHE;
				} else {
					lexeme.append(symbol);
					return this;
				}
			}
		}, 
		AFTER_APOSTROPHE {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '\'') {
					lexeme.append(symbol);
					return State.APOSTROPHE;
				} else {
					lexeme.append("'");
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				}
			}
		}, 
		QOUTATION {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '"') {
					lexer.myTokenType = PascalToken.VALUE;
					return State.START;
				} else {
					lexeme.append(symbol);
					return this;
				}
			}
		}, 
		DOT {
			public State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer) {
				if (symbol == '.') {
					lexeme.append(symbol);
					lexer.myTokenType = PascalToken.DOT_DOT;
					return State.START;
				} else {
					buffer.append(symbol);
					lexer.myTokenType = PascalToken.DOT;
					return State.START;
				}
			}
		};
		
		public abstract State nextState(char symbol, StringBuilder lexeme, StringBuilder buffer, PascalLexer lexer);
	}
	
	public PascalLexer(Reader reader) {
		myReader = reader;
	}

	public IToken nextToken() throws IOException {
		return nextExtendedToken().getType();
	}
	
	private PascalTokenWithLexeme nextExtendedToken() throws IOException {
		if (myToken != null) {
			PascalTokenWithLexeme pascalTokenWithLexeme = myToken;
			myToken = null;
			return pascalTokenWithLexeme;
		}
		while (myState != State.START || myLexeme.length() == 0) { 
			if (myBuffer.length() == 0) {
				int curSymbol = myReader.read();
				if (curSymbol == -1) {
					// End of input
					if (!isEndOfInput) {
						mySymbol = '\n';
						isEndOfInput = true;
					} else {
						return null;
					}
					
				} else {
					mySymbol = (char) curSymbol;
				}
			} else {
				mySymbol = myBuffer.charAt(0);
				myBuffer.deleteCharAt(0);
			}                                                 

			myState = myState.nextState(mySymbol, myLexeme, myBuffer, this);
		}
		
		PascalTokenWithLexeme res = new PascalTokenWithLexeme(myLexeme.toString(), myTokenType);
		myLexeme.delete(0, myLexeme.length());
		return res;
	}
	
	public boolean hasNextToken() throws IOException {
		if (myToken == null) {
			myToken = nextExtendedToken();
		} 
		return myToken != null;
	}
	
	private static boolean isLetter(char ch) {
		return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
	}
	
	private static boolean isDigit(char ch) {
		return ('0' <= ch && ch <= '9');
	}
	
	private static boolean isDelimeter(char ch) {
		for (char c : DELIMETERS) {
			if (c == ch) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isE(char ch) {
		return (ch == 'e' || ch == 'E');
	}
	
	private static boolean isSign(char ch) {
		return (ch == '+' || ch == '-');
	}
	
	private static boolean isEndOfLine(char ch) {
		return (ch == '\n' || ch == '\r');
	}
	
}
