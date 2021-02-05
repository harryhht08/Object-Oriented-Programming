package onlineTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemManager implements Manager {
	private ArrayList<Exam> exams;
	private ArrayList<Student> students;
	private String[] letterGrades;
	private double[] cutoffs;

	public SystemManager() {
		exams = new ArrayList<>();
		students = new ArrayList<>();
	}

	@Override
	public boolean addExam(int examId, String title) {
		Exam e = new Exam(examId, title);
		if (exams.contains(e)) {
			return false;
		}
		exams.add(e);
		return true;
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		TrueFalseQuestion tf = new TrueFalseQuestion(text, examId, questionNumber, points, answer);
		for (Exam e : exams) {
			if (e.getId() == examId) {
				e.addQuestion(tf);
			}
		}
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		MultipleChoiceQuestion multi = new MultipleChoiceQuestion(text, examId, questionNumber, points, answer);
		for (Exam e : exams) {
			if (e.getId() == examId) {
				e.addQuestion(multi);
			}
		}
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		FillInTheBlanksQuestion fillin = new FillInTheBlanksQuestion(text, examId, questionNumber, points, answer);
		for (Exam e : exams) {
			if (e.getId() == examId) {
				e.addQuestion(fillin);
			}
		}
	}

	@Override
	public String getKey(int examId) {
		for (Exam e : exams) {
			if (e.getId() == examId) {
				String s = "";
				for (Question q : e.getQuestions()) {
					s += "Question Text: " + q.getText() + "\n";
					s += "Points: " + q.getPoints() + "\n";
					s += "Correct Answer: ";
					if (q instanceof TrueFalseQuestion) {
						s += ((TrueFalseQuestion) q).getAnswer() == true ? "True" : "False";
					}
					if (q instanceof MultipleChoiceQuestion) {
						s += getStringArray(((MultipleChoiceQuestion) q).getAnswer());
					}
					if (q instanceof FillInTheBlanksQuestion) {
						s += getStringArray(((FillInTheBlanksQuestion) q).getAnswer());
					}
					s += "\n";
				}
				return s;
			}
		}
		return "Exam not found";
	}

	private String getStringArray(String[] arr) {
		String out = "[";
		out += String.join(",", Arrays.asList(arr));
		out += "]";
		return out;
	}

	@Override
	public boolean addStudent(String name) {
		Student s = new Student(name);
		if (students.contains(s)) {
			return false;
		}
		students.add(s);
		return true;
	}

	private void answerTheQuestion(String studentName, int examId, int questionNumber, Object answer) {
		for (Exam e : exams) {
			if (e.getId() == examId) {
				e.addStudent(studentName);
				for (Question q : e.getQuestions()) {
					if (q.getQuestionNumber() == questionNumber) {
						for (Student s : students) {
							if (s.getName().equals(studentName)) {
								s.addPerformance(examId, questionNumber, answer);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		this.answerTheQuestion(studentName, examId, questionNumber, answer);
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		this.answerTheQuestion(studentName, examId, questionNumber, answer);
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		this.answerTheQuestion(studentName, examId, questionNumber, answer);
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		Exam exam = null;
		for (Exam e : exams) {
			if (e.getId() == examId) {
				exam = e;
			}
		}
		ArrayList<Question> questions = exam.getQuestions();
		double score = 0.0;
		for (Student s : students) {
			if (s.getName().equals(studentName)) {
				HashMap<Integer, Object> map = s.getPerformance().get(examId);
				for (Integer key : map.keySet()) {
					for (Question q : questions) {
						if (q.getQuestionNumber() == key) {
							score += checkAnswer(q, map.get(key));
						}
					}
				}
			}
		}
		exam.addStudent(studentName, score);
		return score;
	}

	private double checkAnswer(Question q, Object object) {
		if (q instanceof TrueFalseQuestion && object instanceof Boolean) {
			if (((TrueFalseQuestion) q).getAnswer() == (Boolean) object) {
				return q.getPoints();
			}
		} else if (q instanceof FillInTheBlanksQuestion && object instanceof String[]) {
			String[] answers = ((FillInTheBlanksQuestion) q).getAnswer();
			double out = 0.0;
			double partial = q.getPoints() / ((double) answers.length);
			for (String str : answers) {
				if (Arrays.asList((String[]) object).contains(str)) {
					out += partial;
				}
			}
			return out;
		} else if (q instanceof MultipleChoiceQuestion && object instanceof String[]) {
			String[] answers = ((MultipleChoiceQuestion) q).getAnswer();
			Arrays.sort(answers);
			Arrays.sort((String[]) object);
			if (Arrays.equals(answers, (String[]) object)) {
				return q.getPoints();
			}
		}
		return 0.0;
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		Exam exam = null;
		for (Exam e : exams) {
			if (e.getId() == examId) {
				exam = e;
			}
		}
		String output = "";
		double totalPoints = 0.0;
		double studentPoints = 0.0;
		for (Student s : students) {
			if (studentName.equals(s.getName())) {
				HashMap<Integer, Object> map = s.getPerformance().get(examId);
				for (Integer number : map.keySet()) {
					for (Question q : exam.getQuestions()) {
						if (q.getQuestionNumber() == number) {
							totalPoints += q.getPoints();
							studentPoints += checkAnswer(q, map.get(number));
							output += "Question #" + number + " " + checkAnswer(q, map.get(number)) + " points out of "
									+ q.getPoints() + "\n";
						}
					}
				}
			}
		}
		output += "Final Score: " + studentPoints + " out of " + totalPoints;
		return output;
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		double size = 0;
		double total = 0;
		for (Student s : students) {
			if (s.getName().equals(studentName)) {
				for (Exam e : exams) {
					size++;
					double examFullPoints = getExamFullPoints(e.getId());
					total += this.getExamScore(studentName, e.getId()) / examFullPoints;
				}
			}
		}
		return total / size * 100.0;
	}

	private double getExamFullPoints(int examId) {
		double total = 0;
		for (Exam e : exams) {
			if (e.getId() == examId) {
				for (Question q : e.getQuestions()) {
					total += q.getPoints();
				}
			}
		}
		return total;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		double numeric = this.getCourseNumericGrade(studentName);
		for (int i = 0; i < this.cutoffs.length; i++) {
			if (numeric >= cutoffs[i]) {
				return letterGrades[i];
			}
		}
		return null;
	}

	@Override
	public String getCourseGrades() {
		String output = "";
		Collections.sort(students);
		for (Student s : students) {
			output += s.getName() + " " + getCourseNumericGrade(s.getName()) + " " + getCourseLetterGrade(s.getName())
					+ "\n";
		}
		return output;
	}

	private void getScoreHelper() {
		for (Student s : students) {
			for (Exam e : exams) {
				getExamScore(s.getName(), e.getId());
			}
		}
	}

	@Override
	public double getMaxScore(int examId) {
		getScoreHelper();
		for (Exam e : exams) {
			if (e.getId() == examId) {
				Collection<Double> scoreList = e.getScores().values();
				return Collections.max(scoreList);
			}
		}
		return 0;
	}

	@Override
	public double getMinScore(int examId) {
		getScoreHelper();
		for (Exam e : exams) {
			if (e.getId() == examId) {
				Collection<Double> scoreList = e.getScores().values();
				return Collections.min(scoreList);
			}
		}
		return 0;
	}

	@Override
	public double getAverageScore(int examId) {
		getScoreHelper();
		for (Exam e : exams) {
			if (e.getId() == examId) {
				Collection<Double> scoreList = e.getScores().values();
				double sum = 0;
				for (Double d : scoreList) {
					sum += d;
				}
				return sum / scoreList.size();
			}
		}
		return 0;
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Manager restoreManager(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}
