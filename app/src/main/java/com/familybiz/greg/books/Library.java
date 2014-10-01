package com.familybiz.greg.books;

import java.util.ArrayList;
import java.util.Date;

public class Library {

	public class Book {
		public String title;
		public Date publicationDate;
	}

	ArrayList<Book> mBooks = new ArrayList<Book>();

	public int getBookCount() {
		return mBooks.size();
	}

	public Book getBook(int bookIndex) {
		return mBooks.get(bookIndex);
	}

	public void addBook(Book book) {
		// TODO: Validate book before adding to list
		if (book.title == null || book.title.equals(""))
			return;

		mBooks.add(book);

		// TODO: Notify listener of book addition
	}

	public void removeBook(int bookIndex) {
		mBooks.remove(bookIndex);

		// TODO: Notify listener of book removal
	}

}
