package org.amse.fedotov.noplag.data_storage.impl;

import org.amse.fedotov.noplag.data_storage.IProgramId;

public class ProgramId implements IProgramId {
	
	private final int myIntId;
	private final String myId;
	
	public ProgramId(int id) {
		this.myIntId = id;
		this.myId = String.valueOf(id);
	}
	
	public String getValue() {
		return myId;
	}
	
	public int getIntValue() {
		return myIntId;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + myIntId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		ProgramId other = (ProgramId) obj;
		if (myIntId != other.myIntId) {
			return false;
		}
		return true;
	}

}
