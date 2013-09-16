package org.amse.fedotov.noplag.data_storage.impl;

import org.amse.fedotov.noplag.data_storage.IDataStorage;
import org.amse.fedotov.noplag.data_storage.IProgramId;
import org.amse.fedotov.noplag.model.IProgram;
import org.amse.fedotov.noplag.model.ITokenFactory;

public class StoredProgramFactory {

	public IProgram create(IDataStorage dataStorage, IProgramId id, ITokenFactory tokenFactory) {
		return new StoredProgram(dataStorage, id, tokenFactory);
	}

}
