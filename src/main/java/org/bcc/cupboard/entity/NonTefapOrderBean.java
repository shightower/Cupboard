package org.bcc.cupboard.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NonTefapOrderBean extends OrderBean implements NonTefapOrder {
	private static final long serialVersionUID = -4606535037047408005L;
	private int numOfBags;
	
	public NonTefapOrderBean() {
		
	}
	
	public NonTefapOrderBean(NonTefapOrder order) {
		super(order);
		setNumOfBags(order.getNumOfBags());
	}

	public int getNumOfBags() {
		return numOfBags;
	}

	public void setNumOfBags(int numOfBags) {
		this.numOfBags = numOfBags;
	}
}
