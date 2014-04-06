package org.bcc.cupboard.entity;

import java.io.Serializable;

public interface NonTefapOrder extends Serializable, Order {
	public abstract int getNumOfBags();
	public abstract void setNumOfBags(int numOfBags);
}
