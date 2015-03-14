package com.example.autocompletetextviewcustomadapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class AutocompleteCustomArrayAdapter extends BaseAdapter implements
		Filterable {

	final String TAG = "AutocompleteCustomArrayAdapter.java";

	Context mContext;
	int layoutResourceId;
	ArrayList<KeyValue> data = null;

	public AutocompleteCustomArrayAdapter(Context mContext,
			int layoutResourceId, ArrayList<KeyValue> data) {

		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	public void changeData(ArrayList<KeyValue> data) {
		this.data = data;

		notifyDataSetChanged();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		try {

			if (convertView == null) {
				// inflate the layout
				LayoutInflater inflater = ((MainActivity) mContext)
						.getLayoutInflater();
				convertView = inflater.inflate(layoutResourceId, parent, false);
			}

			// object item based on the position
			KeyValue objectItem = data.get(position);

			// get the TextView and then set the text (item name) and tag (item
			// ID) values
			TextView textViewItem = (TextView) convertView
					.findViewById(R.id.textViewItem);
			textViewItem.setText(objectItem.getValue());

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertView;

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position).getKey();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class MyFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence filterString) {
			// this will be done in different thread
			// so you could even download this data from internet

			FilterResults results = new FilterResults();

			ArrayList<KeyValue> allMatching = new ArrayList<KeyValue>();

			// find all matching objects here and add
			// them to allMatching, use filterString.

			allMatching = data;

			results.values = allMatching;
			results.count = allMatching.size();

			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			

			ArrayList<KeyValue> allMatching = data;
			if (allMatching != null && !allMatching.isEmpty()) {
				data = allMatching;
			}

			notifyDataSetChanged();
		}

	}

	@Override
	public Filter getFilter() {
		return new MyFilter();
	}
}