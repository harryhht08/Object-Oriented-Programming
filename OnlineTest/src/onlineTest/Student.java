package onlineTest;

import java.util.HashMap;

public class Student implements Comparable<Student> {

	private String name;
	private HashMap<Integer, HashMap<Integer, Object>> performance;

	public Student(String name) {
		this.name = name;
		this.performance = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void addPerformance(int examId, int questionNumber, Object answer) {
		if (performance.keySet() == null || !performance.keySet().contains(examId)) {
			HashMap<Integer, Object> hm = new HashMap<>();
			hm.put(questionNumber, answer);
			performance.put(examId, hm);
		} else {
			performance.get(examId).put(questionNumber, answer);
		}
	}

	public HashMap<Integer, HashMap<Integer, Object>> getPerformance() {
		return performance;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Student s = (Student) o;
		return s.name.equals(this.name);
	}

	@Override
	public int compareTo(Student o) {
		return this.name.compareTo(o.name);
	}

}
