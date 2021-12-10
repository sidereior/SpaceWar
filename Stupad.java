import java.util.ArrayList;

public class Stupad implements Stratagy{
    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) {

    }

    @Override
    public Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location currentLocation) {

        if(treasureSpots.size()!=0)
            return new Location(100-treasureSpots.get(0).getCol(),100-treasureSpots.get(0).getRow());
        return new Location(300,300);
    }

    @Override
    public Location shoot(Boolean canShoot) {
        return new Location(500,500);
    }
}
