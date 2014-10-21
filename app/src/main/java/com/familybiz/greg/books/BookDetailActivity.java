package com.familybiz.greg.books;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

	public static final String BOOK_INDEX_EXTRA = "book_index";
	int mBookIndex = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout bookLayout = new LinearLayout(this);
		bookLayout.setOrientation(LinearLayout.HORIZONTAL);

		TextView bookTitleTextView = new TextView(this);
		bookTitleTextView.setBackgroundColor(Color.LTGRAY);
		bookTitleTextView.setText("Book Title");
		bookLayout.addView(bookTitleTextView, new LinearLayout.LayoutParams(
				0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

		Button deleteButton = new Button(this);
		deleteButton.setText(getString(R.string.delete_button_title));
		bookLayout.addView(deleteButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

		setContentView(bookLayout);

		if (getIntent().hasExtra(BOOK_INDEX_EXTRA)) {
			mBookIndex = getIntent().getExtras().getInt(BOOK_INDEX_EXTRA);
			String bookTitle = Library.getInstance().getBook(mBookIndex).title;
			bookTitleTextView.setText(bookTitle);
		}

		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mBookIndex >= 0 && mBookIndex < Library.getInstance().getBookCount()) {
					Library.getInstance().removeBook(mBookIndex);
					finish();
				}
			}
		});
	}
}
