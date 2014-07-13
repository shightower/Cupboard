package org.bcc.cupboard.entity;

import java.io.Serializable;
import java.util.Date;

public interface Order extends Serializable{
	public abstract long getOrderNum();
	public abstract void setOrderNum(long orderNum);
	public abstract Date getOrderDate();
	public abstract void setOrderDate(Date orderDate);
	public abstract int getOrderWeight();
	public abstract void setOrderWeight(int orderWeight);
	public abstract int getIsPending();
	public abstract void setIsPending(int isPending);
	public abstract boolean isPending();
	public abstract int getTefapCount();	
	public abstract void setTefapCount(int count);
	public abstract int getNumOfBags();	
	public abstract void setNumOfBags(int numOfBags);
	public abstract String getOrderType();
	public abstract void setOrderType(String orderType);
	public abstract boolean isTefap();
}