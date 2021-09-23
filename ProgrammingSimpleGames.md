Board games, card games, dice games, and - of course - computer games are everywhere. In this section we'll look at several games and their computer implementation, beginning with a simple dice game called Game50. Along the way we'll develop an idiom for thinking about a broad set of games, and we'll use this idiom to guide the implementation of several example game programs.

Let's introduce the notion of an idiom as a model for program development with the following simple example. We use angle-bracket notation to indicate an informal action or actions. Here is that simple idiom.

```
 <Read data>
 <Calculate result>
 <Report result>
```

This sequence of broad actions, captures, for example, the following problem: "Read a string from the keyboard, and then report the number of occurences of the letters 'A' or 'a' in the entered text." A reasonable solution uses two classes, a driver class, say StringDriver, and a second class, called StringWork. The driver reads the data, then makes a StringWork object, passing along the just-read string as a parameter of the StringWork constructor. The letter count is made by a StringWork method, and a second method from the class reports the result. Here is the code.

```java
import java.util.Scanner;

public class StringDriver {

  public static void main(String[] args) {
    System.out.println("Enter a string");
    Scanner scan = new Scanner(System.in);
    String s = scan.nextLine();  // read data
    StringWork work = new StringWork(s);
    work.processString(); // calclulate result
    System.out.println(work.getCount()); // report result
  }
}
public class StringWork {
  private String s;
  private int count = 0;

  public StringWork(String str) {
    s = str;
  }

  public void processString() {
    for(int j = 0; j < s.length(); j++) {
      char c = s.charAt(j);
      if ((c == 'a') || (c == 'A')) {
        count++;
      }
    }
  }

  public int getCount() {
    return count;
  }
}
```

Now let's look at our target simple game, Game50, and its associated idiom. The goal of the game is to roll a pair of dice repeatedly, add up the values of the rolls, and score a total value of 50 or greater. More specifically, a player enters a number of dice rolls - say 6 - and then tries to reach 50 with 6 or fewer rolls. Thus these rolls: 3-4-3-5-5-12 constitute a loss (6 rolls, but a total of only 32), while 10-9-12-11-8 and 10-12-11-9-9 constitutes wins, since totals of 50 and 51 are reached, and in only 5 rolls. And there's one final rule: if the accumulated value ever reaches 13 exactly, the game is an instant loss. So this roll sequence is an immediate loss: 5-5-3. It reaches 13 after 3 rolls.

This game matches the broad idiom we introduced above: Read the data (how many rolls are possible); calulate the result (roll the dice until you get to 50 or until you run out of rolls, or until you hit 13); and finally report a win or a loss. Again, a two class solution is appropriate: DiceGameDriver and Game50. Here is DiceGameDriver:

```java
import java.util.Scanner;
public class DiceGameDriver {
  public static void main(String[] args) {
    System.out.println("Enter an int > 4: target rolls in Fifty game");
    Scanner s = new Scanner(System.in);
    int rollLimit = s.nextInt();
    System.out.println("Roll Limit: " + rollLimit);
    Game50 d = new Game50(rollLimit);
    d.playGame();
  }
}
```

The critical element here is the playGame method, and for its elaboration we introduce a second idiom:

```
 <initialize game>
 while <game is not over>
   <advance the game>
   <display the game state>
 <judge and report win-loss>
```

Note that while this idiom for implementing the play of a game is quite general, it is also not completely appropriate for all games. For Tic-Tac-Toe and other two player games for example, we would need to capture the way game play alternates between the players, and this would entail modifying the stated idiom appropriately. Still, as presented the idiom we've given is a reasonable starting place for many games, including our dice game.

A second consideration - and this is critical for all object-oriented problem solving - what is the state of a game? The state of a game corresponds to the attribute profile for Game50 objects. These will be the instance variables (and constants) of the class. Here is our list:

- The game target value - here a constant, 50
- The bound on the number of rolls allowed to reach the target value
- The roll count
- The running total (sum) of dice roll values
- Winner or Loser? We need to track this attribute because we may discover in the middle of a game that we've hit the loser value 13
  Now we are in a position to begin to write the Game50 code. We'll start with the following version, which is wildly incomplete - there are five unimplemented methods included in playGame. Still, it does a decent job of breaking the problem of coding into doable parts.

```java
public class Game50 {

  static final int SIZE = 50; // target score to be equaled or exceeded
  private int bound; // number of allowable rolls to reach 50
  private int rollCount;  // number of rolls
  private int total;  // accumulated dice roll sum
  private boolean winner;  // game a win or loss?

  public Game50(int rollBound) {
      bound = rollBound; // rollBound value supplied in constructor call in driver class
  }

public void playGame(){
 initializeGame();
 while(!gameOver()) {
   advancePlay();
   showGame();
  }
 judgeAndReport();
}
```

Notice that the partially written class Game50 is, to the extent possible, consistent with the method calls made in the driver class. That is, the Game50 constructor is set up to get the user-supplied value for the number of rolls available for reaching 50, and playGame() is defined to be a void method that takes no arguments. Also, notice that the five methods identified inside playGame are named in a way that identifies each method's role or subtask. The methods are also linked by name to the idiom components they represent. If we can successfully implement these five methods, then we will have successfully implemented the game. We emphasize that this decompostion is critical for successful program implementation. Even this relatively simple program is too complicated to be implemented without forethought - and the decomposition gives us separate simple pieces that are easy to implement using standard Java primitives.

The simplest of the methods identified in playGame is initializeGame. The method sets up the program's Game50 object for a game episode. Its role, then, is to initialize that object's instance variables for an episode. Among the five attributes in the class, SIZE, a constant, is already set, and the value of bound is set by the class constructor. The other three, rollCount, total, and winner, must be initialized. Of the three only winner's initial value requires much thinking. We recommend that you initialize it to true.

Next let's tackle the gameOver method. A game ends if rollCount equals or exceeds the value of the class attribute bound, or if total reaches or exceeds SIZE, or if total holds the value 13. The question of whether the game just ended is a winner or loser is left to the judgeAndReport method

Here is our solution for advancePlay. We implement it using several helper methods. Note that they are all private methods. They are called only from inside the Game50 class.

```java
    private void advancePlay(){
      int j = roll();  // rolls a pair of dice TODO
      rollCount++;
      incrementTotal(j);
    }

    private void incrementTotal(int curRoll){
      total = total + curRoll;
    }
```

How do we draw the board? That is, how do we implement the showGame method? Here is an image for how we want the run of a game to be displayed in an example where the value of bound has been set to 8:

```
  xxx4xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
  xxxxxxxxxx11xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
  xxxxxxxxxxxxxxxxxxxx21xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
  xxxxxxxxxxxxxxxxxxxxxxxx25xxxxxxxxxxxxxxxxxxxxxxxxx
  xxxxxxxxxxxxxxxxxxxxxxxxxxx28xxxxxxxxxxxxxxxxxxxxxx
  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx37xxxxxxxxxxxxx
  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx48xx
  xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx52
```

We show the accumulated total for each toss of the dice by positioning a display of that value for total in the appropriate column, i.e., the first total, 4, appears in column 4, the second value of total, 11, appears in column 11, and so forth. All other positions up to 50 are marked with an 'x'. If total exceeds 50, which of course will end the game, the final value appears at the end of the row. Here is the code:

```java
   private void showGame(){
    for(int i = 1; i <= SIZE; i++) {
      if (i == total) {
        System.out.print(i);
      }
      else {
        System.out.print('x');
      }
    }

    if (total > SIZE) {
      System.out.print(total);
    }
    System.out.println();
   }
```

Finally we consider the judgeAndReport method. This method is called after the main while loop of playGame has ended, which means the game must be over. But does the sequence of rolls constitute a win or a loss? There are two ways in which a game is judged a loss: it's a loss if the value of total takes on the exact value 13, or if rollCount reaches the value of bound, but total is still less than 50. Since we initialize winner to true in initializeGame, winner remains true unless, when the game is over, either total's value is below SIZE or total hits 13 exactly. Here is the code for judgeAndReport.

```java
   private void judgeAndReport(){
    if ((total < SIZE) || (total == 13)) {
      winner = false;
    }
    System.out.print("Rolls made: " + rollCount + "  ");
    System.out.println("Winner?: " + winner);
   }
```

Here is a complete run.

```
  Enter an int > 4: target rolls in Fifty game
 [value of 5 is entered]
Roll Limit: 5
xxxxx6xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
xxxxxxxxxxxx13xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
Rolls made: 2  Winner?: false
```

Some final points:

- Notice that all the methods in Game50 are private except playGame. We've done this because only playGame is called outside the class.
- We've developed an initializeGame method even though we could just as easily have initialized the three instance variables directly as part of their declaration statements. We've done this because we are trying to anticipate other uses for the class. For example we might be interested in the relative frequency of wins to losses in games with the bound set to a particular value, say 7. If we want to run the game repeatedly, say 10,000 times, so that we can compute an estimate for the win/loss ratio, we want a mechanism for resetting the game repeatedly - and this could be accomplished using initializeGame.
- The notion of an idiom, which we've introduced in this section, is a distant cousin of a general powerful idea in object-oriented (Java) programming called a software pattern. Software patterns are general frameworks for software development which place all programmers on "the same page" when it comes to framing common software development practices. This idea is immensely useful: if you're relying on a particular pattern for a piece of code you're developing, and if I am working on the same development team as you, then I have very strong expectations about the structure of your code, making it easy for me to understanding what you've done, and easy for me to spot mistakes in your code.
