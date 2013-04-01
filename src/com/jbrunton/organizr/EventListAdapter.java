package com.jbrunton.organizr;

import java.util.List;

import com.jbrunton.organizr.models.Event;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class EventListAdapter extends ArrayAdapter<Event> {

	private final List<Event> list;
	private final Activity context;

	public EventListAdapter(FragmentActivity context, List<Event> list) {
		super(context, R.layout.event_list_item, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = context.getLayoutInflater();
		View view = inflator.inflate(R.layout.event_list_item, null);
		final TextView description = (TextView) view.findViewById(R.id.description);
		// final CheckBox complete = (CheckBox) view.findViewById(R.id.complete);
		final Event event = list.get(position);
		final int defaultColor = description.getTextColors().getDefaultColor();
		description.setText(list.get(position).description);
		// complete.setChecked(list.get(position).complete);
		return view;
	}
}
