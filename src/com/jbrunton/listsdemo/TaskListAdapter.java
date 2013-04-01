package com.jbrunton.listsdemo;

import java.util.List;

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

public class TaskListAdapter extends ArrayAdapter<Task> {

	private final List<Task> list;
	private final Activity context;

	public TaskListAdapter(FragmentActivity context, List<Task> list) {
		super(context, R.layout.task_list_item, list);
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
		View view = inflator.inflate(R.layout.task_list_item, null);
		final TextView description = (TextView) view.findViewById(R.id.description);
		final CheckBox complete = (CheckBox) view.findViewById(R.id.complete);
		final Task task = list.get(position);
		final int defaultColor = description.getTextColors().getDefaultColor();
		complete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				task.complete = buttonView.isChecked();
				int paintFlags = description.getPaintFlags();
				if (buttonView.isChecked()) {
					paintFlags |= Paint.STRIKE_THRU_TEXT_FLAG;
					description.setTextColor(Color.GRAY);
				} else {
					paintFlags ^= Paint.STRIKE_THRU_TEXT_FLAG;
					description.setTextColor(defaultColor);
				}
				description.setPaintFlags(paintFlags);
			}
		});
		description.setText(list.get(position).description);
		complete.setChecked(list.get(position).complete);
		return view;
	}
}
