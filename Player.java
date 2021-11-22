public class Player
{
  //testing
  private Stratagy strategy;
  private int roundsWon;
  private int roundsLost;
  private int[] previouseNums;
  private int[] lastWinner;
  public Player(Stratagy strategy)
  {
    this.strategy = strategy;
    roundsWon=0;
    roundsLost=0;
  }


  public String getName()
  {
    return strategy.getClass().getName();
  }
  
  public void roundEnded(boolean outcome, int[] nums, int[] winner)
  {

  }


  

  public int getRoundsWon()
  {
    return roundsWon;
  }

}
