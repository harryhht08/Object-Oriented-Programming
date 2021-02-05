package onlineTest;

public class TrueFalseQuestion extends Question {
	private boolean answer;

	public TrueFalseQuestion(String text, int examId, int questionNumber, double points, boolean answer) {
		super(text, examId, questionNumber, points);
		this.answer = answer;
	}

	public boolean getAnswer() {
		return answer;
	}

}
