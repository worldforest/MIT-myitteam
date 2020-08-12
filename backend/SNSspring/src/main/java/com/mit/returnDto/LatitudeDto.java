package com.mit.returnDto;

import java.util.ArrayList;
import java.util.List;

import com.mit.algorithm.XY;

public class LatitudeDto {
	List<XY> members;
	List<XY> recomends;

	public LatitudeDto() {
		this.members = new ArrayList<XY>();
		this.recomends = new ArrayList<>();
	}

	public List<XY> getMembers() {
		return members;
	}

	public void setMembers(List<XY> members) {
		this.members = members;
	}

	public List<XY> getRecomends() {
		return recomends;
	}

	public void setRecomends(List<XY> recomends) {
		this.recomends = recomends;
	}

}
