package com.jbrunton.organizr.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
@SuppressWarnings("deprecation")
public class Event {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Event> EVENTS = new ArrayList<Event>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Event> EVENT_MAP = new HashMap<String, Event>();

	static {
		// Add 3 sample items.
		addEvent(new Event("1", "Event 1",
				new Date(2013, 05, 01, 1000, 00),
				new Date(2013, 05, 01, 1100, 00)));
		addEvent(new Event("2", "Event 2",
				new Date(2013, 05, 02, 1000, 00),
				new Date(2013, 05, 02, 1100, 00)));
	}

	private static void addEvent(Event event) {
		EVENTS.add(event);
		EVENT_MAP.put(event.id, event);
	}

	public String id;
	public String description;
	public Date start;
	public Date end;

	public Event(String id, String description, Date start, Date end) {
		this.id = id;
		this.description = description;
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return description;
	}
}

