package com.mit.algorithm;

public class XY {
	String x;
	String y;
	String name;

	public XY(String x, String y) {
		super();
		this.x = x;
		this.y = y;
	}

	public XY() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "XY [x=" + x + ", y=" + y + ", name=" + name + "]";
	}

}
