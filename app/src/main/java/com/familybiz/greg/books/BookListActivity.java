/* 57:32 */
package com.familybiz.greg.books;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class BookListActivity extends Activity implements ListAdapter {

	ArrayList<String> mBookList = new ArrayList<String>();
	ArrayList<Integer> mBookImageList = new ArrayList<Integer>();
	ListView mBookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    final LinearLayout rootLayout = new LinearLayout(this);
	    rootLayout.setOrientation(LinearLayout.VERTICAL);

		mBookListView = new ListView(this);
		mBookListView.setAdapter(this);
	    rootLayout.addView(mBookListView, new LinearLayout.LayoutParams(
			    ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));

	    Button addBookButton = new Button(this);
	    addBookButton.setText(getString(R.string.add_book_button_title));
	    rootLayout.addView(addBookButton, new LinearLayout.LayoutParams(
			    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(rootLayout);

	    mBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
			    Intent bookDetailIntent = new Intent();
			    bookDetailIntent.putExtra(BookDetailActivity.BOOK_INDEX_EXTRA, i);
			    bookDetailIntent.setClass(BookListActivity.this, BookDetailActivity.class);
			    startActivity(bookDetailIntent);
		    }
	    });

	    addBookButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			    Intent addBookIntent = new Intent();
			    addBookIntent.setClass(BookListActivity.this, BookAddActivity.class);
			    startActivity(addBookIntent);
		    }
	    });

	    Library.getInstance().setOnBookSetChangedListenter(new Library.OnBookSetChangedListenter() {
		    @Override
		    public void onBookSetChanged() {
			    runOnUiThread(new Runnable() {
				    @Override
				    public void run() {
					    mBookListView.invalidateViews();
				    }
			    });
		    }
	    });

	    Timer addBookTimer = new Timer();
	    addBookTimer.schedule(new TimerTask() {
		    @Override
		    public void run() {
			    Library.Book book = new Library.Book();
			    book.title = "A book published " + (new Date()).toString();
			    book.publicationDate = new Date();
				Library.getInstance().addBook(book);
		    }
	    }, 5000, 5000);
    }

    @Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int getCount() {
		return Library.getInstance().getBookCount();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public Object getItem(int position) {
		return Library.getInstance().getBook((int)getItemId(position));
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public int getItemViewType(int i) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		String itemText = Library.getInstance().getBook(position).title;

		LinearLayout bookLayout = new LinearLayout(this);
		bookLayout.setOrientation(LinearLayout.HORIZONTAL);

		TextView bookView = null;
		//if (view != null && view.getClass() == TextView.class)
			//bookView = (TextView)view;
		//else
		bookView = new TextView(this);

		bookView.setText(itemText);
		bookLayout.addView(bookView);

		return bookLayout;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int i) {
		return true;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver dataSetObserver) {

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

	}
}
