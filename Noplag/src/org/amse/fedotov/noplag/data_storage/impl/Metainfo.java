package org.amse.fedotov.noplag.data_storage.impl;

import org.amse.fedotov.noplag.data_storage.IMetainfo;
import org.amse.fedotov.noplag.model.IAuthor;

public class Metainfo implements IMetainfo {
	
	private final IAuthor myAuthor;
	private final String myFilename;

	public Metainfo(final IAuthor author, final String filename) {
		this.myAuthor = author;
		this.myFilename = filename;
	}

	public IAuthor getAuthor() {
		return myAuthor;
	}

	public String getFilename() {
		return myFilename;
	}

}
