import java.util.ArrayList;

public interface Stratagy
{

  void newRound(int numberofOpponents, int x, int y, int worldX, int worldY);

  public Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location currentLocation);

  public Location shoot(Boolean canShoot);

  void roundEnded(boolean outcome, int[] opScores, int winner);

  void setIndex(int index);

  int getX();

  int getY();
}