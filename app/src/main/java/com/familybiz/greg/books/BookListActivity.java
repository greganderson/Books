/* 57:32 */
package com.familybiz.greg.books;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

		mBookImageList.add(R.drawable.words_of_radiance);
		mBookImageList.add(R.drawable.mistborn);
		mBookImageList.add(R.drawable.harry_potter);
		mBookImageList.add(R.drawable.green_eggs_and_ham);

		ListView bookListView = new ListView(this);
		bookListView.setAdapter(this);
        setContentView(bookListView);
    }

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public int getCount() {
		return mBookList.size();
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
		return mBookList.get(position);
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
		String itemText = mBookList.get(position);
		int itemResource = mBookImageList.get(position);

		LinearLayout bookLayout = new LinearLayout(this);
		bookLayout.setOrientation(LinearLayout.HORIZONTAL);

		TextView bookView = null;
		//if (view != null && view.getClass() == TextView.class)
			//bookView = (TextView)view;
		//else
		bookView = new TextView(this);

		bookView.setText(itemText);

		ImageView bookImageView = new ImageView(this);
		bookImageView.setImageResource(itemResource);

		bookLayout.addView(bookImageView);
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
