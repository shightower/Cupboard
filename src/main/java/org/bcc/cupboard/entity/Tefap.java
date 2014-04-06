package org.bcc.cupboard.entity;

import java.io.Serializable;

public interface Tefap extends Serializable, Order {
	public abstract int getCount();
	public abstract void setCount(int count);
}