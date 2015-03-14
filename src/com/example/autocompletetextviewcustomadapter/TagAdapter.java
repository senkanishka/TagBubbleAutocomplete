package com.example.autocompletetextviewcustomadapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TagAdapter extends BaseAdapter {

	private Context ctx;
	private ArrayList<KeyValue> data;
	KeyValue tempValues = null;
	int i = 0;

	public TagAdapter(Context _ctx, ArrayList<KeyValue> d) {

		ctx = _ctx;
		data = d;

	}

	public int getCount() {
		if (data != null && !data.isEmpty()) {
			return data.size();
		}
		return 0;
	}

	public Object getItem(int position) {
		return position;
	}

	public void changeData(ArrayList<KeyValue> _data) {
		data = _data;
		notifyDataSetChanged();
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {

		public TextView tag_name;

	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		if (data.get(position).isShown()) {
			View vi = convertView;
			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) ctx
						.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				vi = mInflater.inflate(R.layout.tag, null);
				holder = new ViewHolder();
				holder.tag_name = (TextView) vi.findViewById(R.id.tag_name);

				vi.setTag(holder);
			} else
				holder = (ViewHolder) vi.getTag();

			holder.tag_name.setText(data.get(position).getValue());

			return vi;
		}
		return null;
	}

	public interface OnCartItemDeletedListener {
		public void onCartItemsDeleted(int position);
	}

}
