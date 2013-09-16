package org.amse.fedotov.noplag.data_storage;

import org.amse.fedotov.noplag.model.IAuthor;

/**
 * IMetainfo contains data about program stored in data storage. 
 * @author Pavel Fedotov
 *
 */
public interface IMetainfo {

	/**
	 * Returns author of program.
	 * @return author of program.
	 */
	public IAuthor getAuthor();
	
	/**
	 * Returns filename of initial program. 
	 * @return filename of initial program. 
	 */
	public String getFilename();
}
