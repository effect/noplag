package org.amse.fedotov.noplag.data_storage.impl;

import java.util.List;

import org.amse.fedotov.noplag.data_storage.IDataStorage;
import org.amse.fedotov.noplag.data_storage.IMetainfo;
import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.file_utilities.FileUtils;
import org.amse.fedotov.noplag.model.IAuthor;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.IToken;
import org.amse.fedotov.noplag.model.ITokenFactory;

/**
 * <code>StoredProgram</code> contains data of program. This data gets from
 * data storage. 
 * It is using lazy load for each data of program. 
 * @author Pavel Fedotov
 *
 */
public class StoredProgram implements IProgram {
	
	private final IDataStorage myDataStorage;
	private final IProgramId myID;
	private final ITokenFactory myTokenFactory;

	private IMetainfo myMetainfo;
	
	private IAuthor myAuthor;
	private String myFilename;
	private List<IToken> myTokens;
	private byte[] mySource;
	
	/* package */ StoredProgram(IDataStorage dataStorage, IProgramId id, ITokenFactory tokenFactory) {
		this.myDataStorage = dataStorage;
		this.myID = id; 
		this.myTokenFactory = tokenFactory;
	}
	
	public IAuthor getAuthor() {
		if (myAuthor != null) {
			return myAuthor;
		}
		if (myMetainfo != null) {
			return myMetainfo.getAuthor();
		}
		myMetainfo = myDataStorage.getMetainfo(myID);
		myAuthor = myMetainfo.getAuthor();
		return myAuthor;
	}

	public String getFilename() {
		if (myFilename != null) {
			return myFilename;
		}
		if (myMetainfo != null) {
			return myMetainfo.getFilename();
		}
		myMetainfo = myDataStorage.getMetainfo(myID);
		myFilename = myMetainfo.getFilename();
		return myFilename;
	}

	public List<IToken> getTokens() {
		if (myTokens != null) {
			return myTokens;
		}
		myTokens = myDataStorage.getTokens(myID, myTokenFactory);
		return myTokens;
	}

	public byte[] getSource() {
		if (mySource != null) {
			return mySource;
		}
		mySource = FileUtils.getSource(myDataStorage.getSourceReader(myID));
		return mySource;
	}

}
