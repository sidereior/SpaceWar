import java.util.ArrayList;

public interface Stratagy
{

  void newRound(int numberofOpponents, int x, int y, int worldX, int worldY);

  void getLocations(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots);

  public Location move();

  void roundEnded(boolean outcome, int[] opScores, int winner);

  void setIndex(int index);


}