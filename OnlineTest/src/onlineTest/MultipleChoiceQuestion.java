package onlineTest;

import java.util.Arrays;

public class MultipleChoiceQuestion extends Question {
	private String[] answer;

	public MultipleChoiceQuestion(String text, int examId, int questionNumber, double points, String[] answer) {
		super(text, examId, questionNumber, points);
		this.answer = answer;
	}

	public String[] getAnswer() {
		Arrays.sort(answer);
		return answer;
	}

}
