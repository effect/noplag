package org.amse.fedotov.barchart;

public class SimpleValue implements IValue {

	private final double myValue;

	public SimpleValue(int v) {
		myValue = v;
	}

	public double getValue() {
		return myValue;
	}
	
}