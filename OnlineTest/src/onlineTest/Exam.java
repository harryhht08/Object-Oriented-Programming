package onlineTest;

import java.util.ArrayList;
import java.util.HashMap;

public class Exam {
	private int examId;
	private String title;
	private ArrayList<Question> questions;
	private HashMap<String, Double> scores; // student name & student's numerical grade of the exam

	public Exam(int examId, String title) {
		this.title = title;
		this.examId = examId;
		this.scores = new HashMap<>();
		this.questions = new ArrayList<>();
	}

	public void addQuestion(Question q) {
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getQuestionNumber() == q.getQuestionNumber()) {
				questions.remove(i);
				break;
			}
		}
		questions.add(q);
	}

	public int getId() {
		return examId;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void addStudent(String s) {
		if (!scores.keySet().contains(s)) {
			scores.put(s, 0.0);
		}
	}

	public void addStudent(String s, Double score) {
		scores.put(s, score);
	}
	
	public HashMap<String, Double> getScores() {
		return scores;
	}
	

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Exam)) {
			return false;
		}
		Exam e = (Exam) o;
		return e.examId == this.examId && e.title.equals(this.title);
	}

}
