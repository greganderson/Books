/* 57:32 */
package com.familybiz.greg.books;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class BookListActivity extends Activity implements ListAdapter {

	ArrayList<String> mBookList = new ArrayList<String>();
	ArrayList<Integer> mBookImageList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		// TODO: Load from file or a server online
		mBookList.add("Words of Radiance");
		mBookList.add("Mistborn");
		mBookList.add("Harry Potter and the Sorcerer's Stone");
		mBookList.add("Green Eggs and Ham");

		ListView bookListView = new ListView(this);
		bookListView.setAdapter(this);
		bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent bookDetailIntent = new Intent();
				bookDetailIntent.putExtra(BookDetailActivity.BOOK_INDEX_EXTRA, i);
				bookDetailIntent.setClass(BookListActivity.this, BookDetailActivity.class);
				startActivity(bookDetailIntent);
			}
		});
        setContentView(bookListView);
    }

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
    protected void onPause() {
        super.onPause();
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
