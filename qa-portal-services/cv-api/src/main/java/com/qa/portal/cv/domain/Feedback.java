package com.qa.portal.cv.domain;

public class Feedback {
	
	private String reviewer;
	private String date;
	private String comment;
	
	public Feedback() {
		super();
	}

	public Feedback(String reviewer, String date, String comment) {
		super();
		this.reviewer = reviewer;
		this.date = date;
		this.comment = comment;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
