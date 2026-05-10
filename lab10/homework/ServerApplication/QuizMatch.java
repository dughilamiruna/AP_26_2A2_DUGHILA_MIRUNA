import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class QuizMatch implements Runnable {
    private final Player player1;
    private final Player player2;
    private final List<Question> questions;
    private final int timeLimit=10;

    public QuizMatch(Player player1, Player player2, List<Question> questions) {
        this.player1 = player1;
        this.player2 = player2;
        this.questions = questions;
    }

    @Override
    public void run()
    {
        Print("Game starting! You have " + timeLimit + " seconds per question.");

        for(Question question:questions)
        {
            Print(question.displayQuestions());
            long startTime=System.currentTimeMillis();

            Callable<String> task1 = player1.input::readLine;
            Callable<String> task2 = player2.input::readLine;

            String answer1 =null;
            String answer2 =null;
            long elapsed=0;

           try 
           {
                List<Future<String>> futures = GameServer.pool.invokeAll(
                        Arrays.asList(task1, task2),
                        timeLimit,
                        TimeUnit.SECONDS
                );

                elapsed = System.currentTimeMillis() - startTime;

                try 
                { 
                    answer1 = futures.get(0).get(); 
                } 
                catch (Exception e) { }
                try 
                { 
                    answer2 = futures.get(1).get(); 
                } 
                catch (Exception e) { }

            } 
            catch (InterruptedException exception) {
                System.out.println("Meci întrerupt.");
            }

            Evaluate(player1, answer1, question, elapsed);
            Evaluate(player2, answer2, question, elapsed);

            Print("Current Score -> P1: " + player1.getScore() + " | P2: " + player2.getScore());
        }

        Winner();
    }

    private void Evaluate(Player player, String answer, Question question, long time)
    {
        if(answer!=null && question.isCorrect(answer))
        {
            player.addScore();
            player.addTime(time);
            player.output.println("Correct! Time: " + (time / 1000.0) + "s");
        } 
        else 
        {
            player.output.println("Wrong or out of time!");
        }
    }

    private void Print(String message)
    {
        player1.output.println(message);
        player2.output.println(message);
    }

    private void Winner()
    {
        Print("GAME OVER");
        Player winner=null;

        if (player1.getScore() > player2.getScore()) 
        {
                winner = player1;
        }
        else if (player2.getScore() > player1.getScore()) 
        {
            winner = player2;
        }
        else 
        {
            if (player1.getResponseTime() < player2.getResponseTime()) winner = player1;
            else if (player2.getResponseTime() < player1.getResponseTime()) winner = player2;
        }

        if (winner != null) 
        {
            Print("THE WINNER IS " + winner.getName() + "!");
        } 
        else {
            Print("IT'S A TIE!");
        }
    }
}
