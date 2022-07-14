package model;

import java.util.*;
import javafx.collections.*;

public class Student {
	private University university;
	private String number;
	private String name;
	private String attendance;
	private boolean scholarship;
	private ObservableList<Activity> activities = FXCollections.observableArrayList();

	public Student() {
	}

	public Student(University university, String number, String name, String attendance, boolean scholarship) {
		this.university = university;
		this.number = number;
		this.name = name;
		this.attendance = attendance;
		this.scholarship = scholarship;
	}

	public final University getUniversity() { return university; }
	public final String getNumber() { return number; }
	public final String getName() { return name; }
	public final String getAttendance() { return attendance; }
	public final boolean getScholarship() { return scholarship; }
	public final ObservableList<Activity> getActivities() { return activities; }

	public boolean isEnrolledIn(Activity activity) {
		return activities.contains(activity);
	}

	public boolean matches(String number) {
		return this.number.equals(number);
	}

	public void enrol(Activity activity) throws Exception {
		if (!activity.canEnrol())
			throw new Exception("Activity is already full");
		Activity existingActivity = activity(activity.getSubject().getNumber(), activity.getGroup());
		if (existingActivity != null)
			withdraw(existingActivity);
		System.out.println(activities.size());
		activities.add(activity);
		System.out.println(activities.size());
		activity.enrol();
	}

	public void withdraw(Activity activity) {
		activities.remove(activity);
		activity.withdraw();
	}

	private Activity activity(int subjectNumber, String group) {
		for (Activity activity : activities)
			if (activity.matches(subjectNumber, group))
				return activity;
		return null;
	}

	public void remove() {
		for (Activity activity : activities)
			activity.withdraw();
		activities.clear();
	}

	@Override
	public String toString() {
		return number + " - " + name;
	}
}
