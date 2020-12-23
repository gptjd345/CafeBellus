package com.bellus.web.model.view.dto;

public class ViewDTO {
	private int pnum; //피키캐스트 이미지 번호
	private String imagepath; //피키캐스트 이미지 path
	private String figcaption; //피키캐스트 figcaption에 쓸 짧은 글
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public String getFigcaption() {
		return figcaption;
	}
	public void setFigcaption(String figcaption) {
		this.figcaption = figcaption;
	}
	
	@Override
	public String toString() {
		return "ViewDTO [pnum=" + pnum + ", imagepath=" + imagepath + ", figcaption=" + figcaption + "]";
	}
	
	
}
