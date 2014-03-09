package org.bcc.cupboard.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderBean implements Order {
	
	protected long orderNum;
	protected Date orderDate;
	protected int orderWeight;
	protected int numOfBags;

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderNum()
	 */
	@Override
	public long getOrderNum() {
		return orderNum;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderNum(long)
	 */
	@Override
	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderDate()
	 */
	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderDate(java.sql.Date)
	 */
	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getOrderWeight()
	 */
	@Override
	public int getOrderWeight() {
		return orderWeight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setOrderWeight(int)
	 */
	@Override
	public void setOrderWeight(int orderWeight) {
		this.orderWeight = orderWeight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#getNumOfBags()
	 */
	@Override
	public int getNumOfBags() {
		return numOfBags;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Order#setNumOfBags(int)
	 */
	@Override
	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
}
