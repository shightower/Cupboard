package org.bcc.cupboard.entity;

import java.io.Serializable;
import java.sql.Date;

public interface Tefap extends Serializable {

	public abstract long getId();
	public abstract void setId(long id);
	public abstract int getWeight();
	public abstract void setWeight(int weight);
	public abstract int getCount();
	public abstract void setCount(int count);
	public abstract Date getOrderDate();
	public abstract void setOrderDate(Date orderDate);
}