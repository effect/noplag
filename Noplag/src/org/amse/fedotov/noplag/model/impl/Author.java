package org.amse.fedotov.noplag.model.impl;

import org.amse.fedotov.noplag.model.IAuthor;

public class Author implements IAuthor {

	private final String myName;

	public Author(final String name) {
		this.myName = name;
	}

	public String getName() {
		return myName;
	}
	
	@Override
	public String toString() {
		return myName;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((myName == null) ? 0 : myName.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Author other = (Author) obj;
		if (myName == null) {
			if (other.myName != null)
				return false;
		} else if (!myName.equals(other.myName))
			return false;
		return true;
	}

	public int compareTo(IAuthor o) {
		return myName.compareToIgnoreCase(o.getName());
	}

}
