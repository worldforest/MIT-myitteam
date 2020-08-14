package com.mit.returnDto;

import java.util.ArrayList;
import java.util.List;

import com.mit.algorithm.XY;

public class LatitudeDto {
	XY center;
	List<XY> members;

	public XY getCenter() {
		return center;
	}

	public void setCenter(XY center) {
		this.center = center;
	}

	public LatitudeDto() {
		this.members = new ArrayList<XY>();
		this.center = new XY();
	}

	public List<XY> getMembers() {
		return members;
	}

	public void setMembers(List<XY> members) {
		this.members = members;
	}

}
