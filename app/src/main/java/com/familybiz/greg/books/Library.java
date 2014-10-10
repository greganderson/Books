package com.familybiz.greg.books;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Library {

	public class Book {
		public String title;
		public Date publicationDate;

		public Book(){}

		public Book(Book book) {
			title = book.title;
			publicationDate = book.publicationDate;
		}
	}

	static ArrayList<Book> mBooks = new ArrayList<Book>();
	static File mLibraryFile = null;
	static Library mInstance = null;
	static Library getInstance() {
		if (mInstance == null)
			mInstance = new Library();
		if (mLibraryFile != null && mBooks.size() == 0)
			mInstance.loadLibrary(mLibraryFile);
		return mInstance;
	}

	private Library() {}

	public void setLibraryFile(File file) {
		mLibraryFile = file;
	}

	public File getLibraryFile() {
		return mLibraryFile;
	}

	public int getBookCount() {
		return mBooks.size();
	}

	public Book getBook(int bookIndex) {
		return new Book(mBooks.get(bookIndex));
	}

	public void addBook(Book book) {
		if (book.title == null || book.title.length() == 0)
			return;

		mBooks.add(new Book(book));

		saveLibrary(mLibraryFile);
		// TODO: Notify listener of book addition
	}

	public void removeBook(int bookIndex) {
		mBooks.remove(bookIndex);

		saveLibrary(mLibraryFile);
		// TODO: Notify listener of book removal
	}

	private void loadLibrary(File libraryFile) {
		mBooks.clear();
		try {
			FileReader textReader = new FileReader(libraryFile);
			BufferedReader bufferedTextReader = new BufferedReader(textReader);
			String jsonBookList = bufferedTextReader.readLine();

			Gson gson = new Gson();
			Book[] bookList = gson.fromJson(jsonBookList, Book[].class);
			Collections.addAll(mBooks, bookList);

			bufferedTextReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		if (mBooks.size() == 0) {
			Book book0 = new Book();
			book0.title = "Green Eggs and Ham";
			book0.publicationDate = new Date();
			mBooks.add(book0);
			Book book1 = new Book();
			book1.title = "Ender's Game";
			book1.publicationDate = new Date();
			mBooks.add(book1);
		}
		*/
	}

	private void saveLibrary(File libraryFile) {
		Gson gson = new Gson();
		String jsonBookList = gson.toJson(mBooks.toArray(new Book[mBooks.size()]));
		try {
			FileWriter textWriter = new FileWriter(libraryFile);
			BufferedWriter bufferedTextWriter = new BufferedWriter(textWriter);
			bufferedTextWriter.write(jsonBookList);
			bufferedTextWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
