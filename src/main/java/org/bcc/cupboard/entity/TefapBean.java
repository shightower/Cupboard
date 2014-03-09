package org.bcc.cupboard.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TefapBean implements Tefap {

	protected long id;
	protected int weight;
	protected int count;
	protected Date orderDate;	

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getId()
	 */
	@Override
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setId(long)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getWeight()
	 */
	@Override
	public int getWeight() {
		return weight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setWeight(int)
	 */
	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setCount(int)
	 */
	@Override
	public void setCount(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#getOrderDate()
	 */
	@Override
	public Date getOrderDate() {
		return orderDate;
	}

	/* (non-Javadoc)
	 * @see org.bcc.cupboard.entity.jpa.Tefap#setOrderDate(java.sql.Date)
	 */
	@Override
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
