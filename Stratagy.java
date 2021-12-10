import java.util.ArrayList;

public interface Stratagy
{

  void newRound(int numberofOpponents, int x, int y, int worldX, int worldY);

  Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location currentLocation);

  Location shoot(Boolean canShoot);


}