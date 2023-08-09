import java.util.Random;
import java.util.Scanner;

class MathQuiz {
    private static final int NUM_PLAYERS = 2;
    private static final int NUM_QUESTIONS_PER_PLAYER = 3;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[][] scores = new int[NUM_PLAYERS][NUM_QUESTIONS_PER_PLAYER]; // Массив для хранения счета каждого игрока

        for (int player = 0; player < NUM_PLAYERS; player++) {
            System.out.println("Player " + (player + 1) + ", get ready!");

            for (int i = 0; i < NUM_QUESTIONS_PER_PLAYER; i++) {
                int num1 = random.nextInt(100);
                int num2 = random.nextInt(100);
                char[] operators = {'+', '-', '*', '/'};
                char operator = operators[random.nextInt(operators.length)];

                int correctAnswer = calculateAnswer(num1, num2, operator);

                System.out.print("Question " + (i + 1) + ": " + num1 + " " + operator + " " + num2 + " = ");
                int userAnswer = scanner.nextInt();

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                    scores[player][i] = 5;
                } else {
                    System.out.println("Incorrect!");
                    scores[player][i] = -5;
                }

                System.out.println("Your answer: " + userAnswer + ". Correct answer: " + correctAnswer);
            }

            int playerScore = calculatePlayerScore(scores[player]);
            System.out.println("Player " + (player + 1) + ", your final score: " + playerScore);
        }

        System.out.println("Quiz completed for all players!");
    }

    private int calculateAnswer(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return Calculator.add(num1, num2);
            case '-':
                return Calculator.subtract(num1, num2);
            case '*':
                return Calculator.multiply(num1, num2);
            case '/':
                return Calculator.divide(num1, num2);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private int calculatePlayerScore(int[] playerScores) {
        int totalScore = 0;
        for (int score : playerScores) {
            totalScore += score;
        }
        return totalScore;
    }
}
