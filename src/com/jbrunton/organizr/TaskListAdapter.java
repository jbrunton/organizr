package com.jbrunton.organizr;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bindroid.BindingMode;
import com.bindroid.converters.BoolConverter;
import com.bindroid.ui.CompoundButtonCheckedProperty;
import com.bindroid.ui.UiBinder;
import com.jbrunton.organizr.models.Task;

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
		final View view = inflator.inflate(R.layout.task_list_item, null);
		
		final TextView description = (TextView) view.findViewById(R.id.description);
		final CheckBox complete = (CheckBox) view.findViewById(R.id.complete);
		final Task task = list.get(position);
		final int defaultColor = description.getTextColors().getDefaultColor();
		
		description.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent detailIntent = new Intent(context, TaskDetailActivity.class);
				detailIntent.putExtra(TaskDetailFragment.ARG_ITEM_ID, task.id);
				context.startActivity(detailIntent);
			}
		});
		
		// TouchDelegate to make check box easier to click ÐÊsee
		// http://stackoverflow.com/a/1343796
		//
		view.post( new Runnable() {
		    // Post in the parent's message queue to make sure the parent
		    // lays out its children before we call getHitRect()
		    public void run() {
		        final Rect r = new Rect();
		        complete.getHitRect(r);
		        r.top -= 4;
		        r.bottom += 4;
		        r.left -= 4;
		        r.right += 8;
		        view.setTouchDelegate( new TouchDelegate(r, complete));
		    }
		});

		UiBinder.bind(view, R.id.description, "PaintFlags", task, "Complete", BindingMode.ONE_WAY, new DisabledPaintFlagsConverter(description));
		UiBinder.bind(view, R.id.description, "TextColor", task, "Complete", BindingMode.ONE_WAY, new DisabledColorConverter(Color.GRAY, defaultColor));
		UiBinder.bind(new CompoundButtonCheckedProperty(complete), task, "Complete", BindingMode.TWO_WAY);
		UiBinder.bind(view, R.id.description, "Text", task, "Description", BindingMode.ONE_WAY);
		
		return view;
	}
}
