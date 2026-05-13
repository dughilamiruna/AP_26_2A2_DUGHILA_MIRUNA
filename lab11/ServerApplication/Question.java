package org.example;
public class Question {

    private final String question;
    private final String[] answers;
    private final int correctAnswer;

    public Question(String question, String[] answers, int correctAnswer)
    {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(String answer)
    {
        try
        {
            return Integer.parseInt(answer.trim()) == correctAnswer;
        }
        catch (NumberFormatException exception) {
            return false;
        }
    }

    public String displayQuestions()
    {
        return "\n" + question + "\n1) " + answers[0] + " 2) " + answers[1] +
                " 3) " + answers[2] + " 4) " + answers[3] + "\nAnswer (1-4): ";
    }
}