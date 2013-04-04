package com.jbrunton.organizr.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bindroid.trackable.TrackableField;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Task {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Task> TASKS = new ArrayList<Task>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Task> TASK_MAP = new HashMap<String, Task>();

	static {
		// Add 3 sample items.
		addTask(new Task("1", "Task 1", false));
		addTask(new Task("2", "Task 2", false));
		addTask(new Task("3", "Task 3", true));
	}

	private static void addTask(Task task) {
		TASKS.add(task);
		TASK_MAP.put(task.id, task);
	}

	public String id;

	private TrackableField<Boolean> complete = new TrackableField<Boolean>();
	public Boolean getComplete() { return this.complete.get(); }
	public void setComplete(Boolean value) { this.complete.set(value); }
	
	private TrackableField<String> description = new TrackableField<String>();
	public String getDescription() { return this.description.get(); }
	public void setDescription(String value) { this.description.set(value); }

	public Task(String id, String description, Boolean complete) {
		this.id = id;
		this.setDescription(description);
		this.setComplete(complete);
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}

