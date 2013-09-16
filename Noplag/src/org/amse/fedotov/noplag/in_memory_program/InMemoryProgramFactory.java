package org.amse.fedotov.noplag.in_memory_program;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.amse.fedotov.noplag.lexer.ILexer;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.IToken;

public class InMemoryProgramFactory {

	private final static InMemoryProgramFactory myFactory = new InMemoryProgramFactory(); 
	
	private InMemoryProgramFactory() {
	}
	
	public static InMemoryProgramFactory getInstance() {
		return myFactory;
	}

	public IProgram create(IAuthor author, String filename, Reader reader, ILexer lexer) throws IOException {
		List<IToken> tokens = new ArrayList<IToken>();
		while (lexer.hasNextToken()) {
			tokens.add(lexer.nextToken());
		}
		return new InMemoryProgram(author, filename, tokens, reader);
	}
}
