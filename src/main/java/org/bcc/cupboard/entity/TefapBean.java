package org.bcc.cupboard.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TefapBean extends OrderBean implements Tefap{
	private static final long serialVersionUID = 5972164685859836735L;
	private int count;
	
	public TefapBean() {
		
	}
	
	public TefapBean(Tefap tefap) {
		super(tefap);
		setCount(tefap.getCount());
		setOrderDate(tefap.getOrderDate());
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
