package com.jbrunton.organizr;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bindroid.BindingMode;
import com.bindroid.ui.CompoundButtonCheckedProperty;
import com.bindroid.ui.EditTextTextProperty;
import com.bindroid.ui.UiBinder;
import com.jbrunton.organizr.models.Task;

/**
 * A fragment representing a single Task detail screen. This fragment is either
 * contained in a {@link TaskListActivity} in two-pane mode (on tablets) or a
 * {@link TaskDetailActivity} on handsets.
 */
public class TaskDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private Task task;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public TaskDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			this.task = Task.TASK_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_task_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		final EditText description = (EditText) rootView.findViewById(R.id.description);
		final TextView completeLabel = (TextView) rootView.findViewById(R.id.complete_label);
		final CheckBox complete = (CheckBox) rootView.findViewById(R.id.complete);
		final int defaultColor = completeLabel.getTextColors().getDefaultColor();

		UiBinder.bind(rootView, R.id.complete_label, "PaintFlags", task, "Complete", BindingMode.ONE_WAY, new DisabledPaintFlagsConverter(description));
		UiBinder.bind(rootView, R.id.complete_label, "TextColor", task, "Complete", BindingMode.ONE_WAY, new DisabledColorConverter(Color.GRAY, defaultColor));
		UiBinder.bind(new CompoundButtonCheckedProperty(complete), task, "Complete", BindingMode.TWO_WAY);
		UiBinder.bind(new EditTextTextProperty(description), task, "Description", BindingMode.TWO_WAY);
		
		return rootView;
	}
}
