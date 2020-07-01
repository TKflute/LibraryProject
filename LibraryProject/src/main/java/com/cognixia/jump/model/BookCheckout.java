package com.cognixia.jump.model;

import java.sql.Date;

public class BookCheckout {

	private int checkoutId;
	private int patronId;
	private String isbn;
	private String bookTitle;
	private Date checkedout;
	private Date dueDate;
	private Date returnDate;
	
	public BookCheckout(int checkoutId, int patronId, String isbn, String bookTitle, Date checkedout, Date dueDate,
			Date returnDate) {
		super();
		this.checkoutId = checkoutId;
		this.patronId = patronId;
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.checkedout = checkedout;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public int getPatronId() {
		return patronId;
	}

	public void setPatronId(int patronId) {
		this.patronId = patronId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Date getCheckedout() {
		return checkedout;
	}

	public void setCheckedout(Date checkedout) {
		this.checkedout = checkedout;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkoutId=" + checkoutId + ", patronId=" + patronId + ", isbn=" + isbn + ", bookTitle="
				+ bookTitle + ", checkedout=" + checkedout + ", dueDate=" + dueDate + ", returnDate=" + returnDate
				+ "]";
	}
	
	
	
	

}