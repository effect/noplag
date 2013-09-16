package org.amse.fedotov.noplag.program_storage.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.amse.fedotov.noplag.data_storage.IDataStorage;
import org.amse.fedotov.noplag.data_storage.IMetainfo;
import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.data_storage.impl.Metainfo;
import org.amse.fedotov.noplag.data_storage.impl.StoredProgramFactory;
import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.ITokenFactory;
import org.amse.fedotov.noplag.program_storage.IProgramStorage;
import org.amse.fedotov.noplag.settings.Settings;

public class ProgramStorage implements IProgramStorage {
	
	private final IDataStorage myDataStorage;
	private final List<IProgram> myPrograms;
	private final StoredProgramFactory myProgramFactory = new StoredProgramFactory();
	
	public ProgramStorage(IDataStorage dataStorage, ITokenFactory tokenFactory) {
		myDataStorage = dataStorage;
		myPrograms = new ArrayList<IProgram>();
		for (IProgramId id : myDataStorage.loadIds()) {
			myPrograms.add(myProgramFactory.create(myDataStorage, id, tokenFactory));
		}
	}
	
	public Iterator<IProgram> iterator() {
		return Collections.unmodifiableList(myPrograms).iterator();
	}
	
	public void addProgram(IProgram program) {
		myPrograms.add(program);
		// Add program data to the data storage 
		IMetainfo metainfo = new Metainfo(program.getAuthor(), program.getFilename());
		Reader reader;
		try {
			reader = new InputStreamReader(new ByteArrayInputStream(program.getSource()), Settings.getInternalCharset());
			myDataStorage.writeProgramData(metainfo, program.getTokens(), reader);
		} catch (UnsupportedEncodingException e) {
			BadInputException ex = new BadInputException("Can't find " + Settings.getInternalCharset() + " charset");
			ex.initCause(e);
			throw ex;
		}
	}

	public Set<IAuthor> getAuthors() {
		Set<IAuthor> authors = new HashSet<IAuthor>();
		for (IProgram p : myPrograms) {
			authors.add(p.getAuthor());
		}
		return Collections.unmodifiableSet(authors);
	}

	public int getSize() {
		return myPrograms.size();
	}
	
}
