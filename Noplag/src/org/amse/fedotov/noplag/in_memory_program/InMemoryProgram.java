package org.amse.fedotov.noplag.in_memory_program;

import java.io.Reader;
import java.util.List;

import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.IToken;

public class InMemoryProgram implements IProgram {
	
	private final IAuthor myAuthor;
	private final String myFilename;
	private final List<IToken> myTokens;
	private final byte[] mySource;
	
	/* package */ InMemoryProgram(IAuthor author, String filename, List<IToken> tokens, Reader sourceReader) {
		this.myAuthor = author;
		this.myFilename = filename;
		this.myTokens = tokens;
		this.mySource = FileUtils.getSource(sourceReader);
	}

	public IAuthor getAuthor() {
		return myAuthor;
	}

	public String getFilename() {
		return myFilename;
	}

	public List<IToken> getTokens() {
		return myTokens;
	}

	public byte[] getSource() {
		return mySource;
	}

}
