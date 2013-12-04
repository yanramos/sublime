package com.example.sublime.layout;

public class PromoListItem {
	
	private String title;
	private String subtitle;
	private String icon;
	private String address;
	private boolean status;
	private int id;
	private String key;
	
	public PromoListItem(String key, String title, String subtitle, String icon, 
			String address, boolean status, int id) {
		this.key = key;
		this.title = title;
		this.subtitle = subtitle;
		this.icon = icon;
		this.address = address;
		this.status = status;
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	@Override
	public String toString(){
		return "["+id+", "+title+", "+subtitle+", "+icon+", "+address+", "+status+", ";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
