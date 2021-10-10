package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String title;
	private String desc;
	private String current_date;
	private String category;
	private String due_date;
	private int is_completed;

	Date now = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public TodoItem(String title, String desc) {
		this.title = title;
		this.desc = desc;
		this.current_date = sdf.format(now);
	}

	public TodoItem(String title, String desc, String date) {
		this.title = title;
		this.desc = desc;
		this.current_date = date;
	}

	public TodoItem(String title, String desc, String category, String due_date) {
		this.title = title;
		this.desc = desc;
		this.current_date = sdf.format(now);
		this.category = category;
		this.due_date = due_date;
	}

	public TodoItem(String title, String desc, String category, String due_date, String date) {
		this.title = title;
		this.desc = desc;
		this.current_date = date;
		this.category = category;
		this.due_date = due_date;
	}

	public TodoItem(String title, String category, String desc, String due_date, int is_completed) {
		this.title = title;
		this.desc = desc;
		this.current_date = sdf.format(now);
		this.category = category;
		this.due_date = due_date;
		this.is_completed = is_completed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(String current_date) {
		this.current_date = current_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	@Override
	public String toString() {
		String record = id + ". [" + category + "] " + title;
		if (is_completed > 0)
			record += "[V]";
		record += " - " + desc + " -" + due_date + " -Time written: " + current_date;
		return record;
	}

	public String toSaveString() {
		return title + "##" + category + "##" + desc + "##" + due_date + "##" + current_date + "##" + is_completed
				+ "\n";
	}
}
