package org.bcc.cupboard.entity;

import java.sql.Date;

public interface Order {

	public abstract long getOrderNum();
	public abstract void setOrderNum(long orderNum);
	public abstract Date getOrderDate();
	public abstract void setOrderDate(Date orderDate);
	public abstract int getOrderWeight();
	public abstract void setOrderWeight(int orderWeight);
	public abstract int getNumOfBags();
	public abstract void setNumOfBags(int numOfBags);
}