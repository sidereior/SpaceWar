import java.util.ArrayList;

public class Idiot implements Stratagy{
    private int worldX;
    private int worldY;
    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    @Override
    public Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location currentLocation) {
        return new Location((int)(Math.random()*worldX),(int)(Math.random()*worldY));
    }

    @Override
    public Location shoot(Boolean canShoot) {
        return null;
    }
}
