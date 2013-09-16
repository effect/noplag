package org.amse.fedotov.cs_algo.comparator;

/* package */ class CommonSubstringPart {
	
	private final int myLeftA;
	private final int myRigthA;
	private final int myLeftB;
	private final int myRightB;
	
	/* package */ CommonSubstringPart(int leftA, int rigthA, int leftB, int rightB) {
		this.myLeftA = leftA;
		this.myRigthA = rigthA;
		this.myLeftB = leftB;
		this.myRightB = rightB;
	}

	public int getLeftA() {
		return myLeftA;
	}

	public int getLeftB() {
		return myLeftB;
	}

	public int getRigthA() {
		return myRigthA;
	}
	
	public int getRightB() {
		return myRightB;
	}
	
	public int getLength() {
//		assert (myRigthA - myLeftA) == (myRightB - myLeftB); 
		return myRigthA - myLeftA;
	}

}
