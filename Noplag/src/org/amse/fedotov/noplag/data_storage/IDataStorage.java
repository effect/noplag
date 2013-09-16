package org.amse.fedotov.noplag.data_storage;

import java.io.Reader;
import java.util.List;

import org.amse.fedotov.noplag.model.IToken;
import org.amse.fedotov.noplag.model.ITokenFactory;

/**
 * <code>IDataStorage</code> stores data of programs in file system. 
 * @author Pavel Fedotov
 *
 */
public interface IDataStorage {
	
	/**
	 * Returns metainfo of the program with given id. 
	 * @param id id of the program. 
	 * @return metainfo of the program. 
	 */
	public IMetainfo getMetainfo(IProgramId id);
	
	/**
	 * Returns unmodifiable list of tokens of the program with given id. 
	 * @param id id of the program. 
	 * @param tokenFactory token factory. 
	 * @return unmodifiable list of tokens of the program. 
	 */
	public List<IToken> getTokens(IProgramId id, ITokenFactory tokenFactory);
	
	/**
	 * Returns reader the program reads from. 
	 * @param id id of the program. 
	 * @return reader the program reads from.
	 */
	public Reader getSourceReader(IProgramId id);
	
	/**
	 * Returns unmodifiable list with all program Ids in the data storage. 
	 * @return unmodifiable list with all program Ids. 
	 */
	public List<IProgramId> loadIds();
	
	/**
	 * Stores program data to the file system and returns Id for this data. 
	 * @param metainfo metainfo of the program. 
	 * @param tokens list of tokens of the program.  
	 * @param sourceReader reader the program source reads from. 
	 * @return Id for this program data. 
	 */
	public IProgramId writeProgramData(IMetainfo metainfo, List<IToken> tokens, 
			Reader sourceReader);

}
