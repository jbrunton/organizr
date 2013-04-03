package com.jbrunton.organizr;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

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

		complete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				task.complete = buttonView.isChecked();
				int paintFlags = completeLabel.getPaintFlags();
				if (buttonView.isChecked()) {
					paintFlags |= Paint.STRIKE_THRU_TEXT_FLAG;
					completeLabel.setTextColor(Color.GRAY);
				} else {
					paintFlags ^= Paint.STRIKE_THRU_TEXT_FLAG;
					completeLabel.setTextColor(defaultColor);
				}
				completeLabel.setPaintFlags(paintFlags);
			}
		});
		
		description.addTextChangedListener(new TextWatcher() {
	        public void afterTextChanged(Editable s) {
	        	task.description = description.getText().toString();
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
	        public void onTextChanged(CharSequence s, int start, int before, int count) {}
	    });

		description.setText(task.description);
		complete.setChecked(task.complete);
		
		return rootView;
	}
}
