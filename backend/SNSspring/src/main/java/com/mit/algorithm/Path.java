package com.mit.algorithm;

public class Path {
	String path;
	String im;

	public Path() {
//		배포시
		this.path = "https://i3b306.p.ssafy.io";
		this.im = "/home/ubuntu/";
		
//		배포안할시 개발
//		this.path = "http://localhost:9999";
//		this.im = "C://";
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}
	
	
}
