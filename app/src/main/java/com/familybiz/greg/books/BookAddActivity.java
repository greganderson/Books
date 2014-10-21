package com.familybiz.greg.books;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class BookAddActivity extends Activity {

	EditText mBookTitleEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout rootLayout = new LinearLayout(this);
		rootLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(rootLayout);

		LinearLayout titleLayout = new LinearLayout(this);
		titleLayout.setOrientation(LinearLayout.HORIZONTAL);
		rootLayout.addView(titleLayout, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		TextView bookTitleLabel = new TextView(this);
		bookTitleLabel.setText(getString(R.string.book_title_label));
		titleLayout.addView(bookTitleLabel, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		mBookTitleEditText = new EditText(this);
		titleLayout.addView(mBookTitleEditText, new LinearLayout.LayoutParams(
				0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

		Button saveBookButton = new Button(this);
		saveBookButton.setText(getString(R.string.add_book_button_title));
		rootLayout.addView(saveBookButton, new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		saveBookButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String bookTitle = mBookTitleEditText.getText().toString();

				if (bookTitle.length() == 0) {
					Toast.makeText(view.getContext(), "Book must have a title.", Toast.LENGTH_SHORT).show();
					return;
				}

				Library.Book book = new Library.Book();
				book.title = bookTitle;
				book.publicationDate = new Date();
				Library.getInstance().addBook(book);
				finish();
			}
		});
	}
}
