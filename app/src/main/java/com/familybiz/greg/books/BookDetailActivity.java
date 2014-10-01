package com.familybiz.greg.books;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

	public static final String BOOK_TITLE_EXTRA = "book_title";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView bookTitleTextView = new TextView(this);
		bookTitleTextView.setText("Book Title");
		bookTitleTextView.setBackgroundColor(Color.GRAY);
		setContentView(bookTitleTextView);

		if (getIntent().hasExtra(BOOK_TITLE_EXTRA)) {
			String bookTitle = getIntent().getExtras().getString(BOOK_TITLE_EXTRA);
			bookTitleTextView.setText(bookTitle);
		}
	}
}
