import java.util.Scanner;

public class RPSDriver {

 public static void main(String[] args) {
  System.out.println("Enter an int > 0: number of rounds to play.");
  Scanner scanner = new Scanner(System.in);
  int rounds = scanner.nextInt();
  System.out.println("Rounds to play: " + rounds);
  RPSGame rps = new RPSGame(rounds);
  rps.playGame();
 }
}