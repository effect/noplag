package org.amse.fedotov.noplag.data_storage.impl;

import java.io.File;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.amse.fedotov.noplag.data_storage.IDataStorage;
import org.amse.fedotov.noplag.data_storage.IMetainfo;
import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.data_storage.impl.io.MetainfoIOAgent;
import org.amse.fedotov.noplag.data_storage.impl.io.SourceIOAgent;
import org.amse.fedotov.noplag.data_storage.impl.io.TokensIOAgent;
import org.amse.fedotov.noplag.exception.BadInputException;
import org.amse.fedotov.noplag.model.IToken;
import org.amse.fedotov.noplag.model.ITokenFactory;
import org.amse.fedotov.noplag.settings.Settings;

/**
 * {@link DataStorage} stores files on disc. 
 * @author Pavel Fedotov
 *
 */
public class DataStorage implements IDataStorage {
	
	private final MetainfoIOAgent myMetainfoIOAgent = new MetainfoIOAgent();
	private final SourceIOAgent mySourceIOAgent = new SourceIOAgent();
	private final TokensIOAgent myTokensIOAgent = new TokensIOAgent();
	
	private int myNumberOfFiles;
	private Set<IProgramId> myProgramIds;
	
	public DataStorage() {
		initProgramIds();
	}

	public IMetainfo getMetainfo(IProgramId id) throws BadInputException {
		return myMetainfoIOAgent.read(id);
	}

	public Reader getSourceReader(IProgramId id) throws BadInputException {
		return mySourceIOAgent.getReader(id);
	}

	public List<IToken> getTokens(IProgramId id, ITokenFactory tokenFactory) throws BadInputException {
		return Collections.unmodifiableList(myTokensIOAgent.read(id, tokenFactory));
	}

	public IProgramId writeProgramData(IMetainfo metainfo, List<IToken> tokens, Reader sourceReader) throws BadInputException {
		IProgramId id = new ProgramId(myNumberOfFiles);
		myNumberOfFiles++;
		
		myMetainfoIOAgent.write(id, metainfo);
		mySourceIOAgent.write(id, sourceReader);
		myTokensIOAgent.write(id, tokens);
		
		return id;
	}
	
	public List<IProgramId> loadIds() {
		List<IProgramId> list = new ArrayList<IProgramId>();
		list.addAll(myProgramIds);
		return Collections.unmodifiableList(list);
	}

	public void initProgramIds() {
		myProgramIds = new HashSet<IProgramId>();
		File dir = new File(getStoragePath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String[] list = dir.list();
		if (list == null) {
			return;
		}
		myNumberOfFiles = -1;
		// find maximum in id's
		for (int i = 0; i < list.length; i++) {
			int id = getIntPrefix(list[i]);
			myProgramIds.add(new ProgramId(id));
			if (myNumberOfFiles < id) {
				myNumberOfFiles = id;
			}
		}
		myNumberOfFiles++;
	}

	private int getIntPrefix(String filename) {
		String value = filename.substring(0, filename.indexOf("."));
		return Integer.parseInt(value);
	}

	public static String getStoragePath() {
		return Settings.getNoplagDataPath();
	}
}
