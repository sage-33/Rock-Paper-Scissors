# Rock Paper Scissors

## Introduction

This two-person hand game is ancient, and seems to have originated in China around two thousand years ago. In the game two players make one of three hand shapes simultaneously: a fist (rock); a cupped hand with fingers together (paper); or first two fingers extended and separated (scissors). If players have the same shape, the game is a draw; otherwise paper wins over (covers) rock, scissors wins over (cuts) paper; and rock wins over (smashes) scissors. For a more complete account of the game and its history see (https://en.wikipedia.org/wiki/Rock-paper-scissors).

In our version of the game, a user enters a number of rounds to be played, e.g., 5. The computer version then runs five rounds of the game, reporting a winner (or no winner, in the case of identical hand shapes). Here is a sample run from our Java version:

```
Enter an int > 0: number of rounds to play.
 [value 5 is entered]
Rounds to play: 5
Rock vs. Paper: Player Two wins.
Paper vs. Rock: Player One wins.
Scissors vs. Scissors: Nobody wins.
Scissors vs. Rock: Player Two wins.
Paper vs. Paper: Nobody wins.

Player One rounds won: 1
Player Two rounds won: 2
Winner?: Player Two
```

We have developed a Java version of the game, and your job is to complete the implementation.

## Clone Project

Begin by cloning the provided project and importing it into your workspace.

`git clone <url for github repository>`

After cloning this repository you want to run these commands

`git checkout -b development`

This will create a branch named development and switch you to it. The development branch is where you will make all your commits.

### Import into Eclipse

You should then go to Eclipse, which hopefully has finished installing.

In the top toolbar, click File --> Import. Double-click on General and then `Projects from Folder or Archive`. Click `Directory` and find the folder inside the repository named `RPS` and click Open. The project should pop up in the text box Projects. Click finish and you should be good to go!

## Complete RPSGame.java

First, here is the driver class:

```java
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
```

Your job is to complete the RPSGame to fit the description above.

## Notes

- You need to comment your methods according to this [Javadoc Guide](https://github.com/jd12/liferay-portal/blob/master/readme/ADVANCED_JAVADOC_GUIDELINES.markdown).

- Use the [Game50 Example](./ProgrammingSimpleGames.md) as a guide to approaching this project
