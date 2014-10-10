package com.familybiz.greg.books;

import android.app.Application;

import java.io.File;

public class LibraryApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		Library.getInstance().setLibraryFile(new File(getFilesDir(), "Library.txt"));
	}
}
