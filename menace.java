import java.util.ArrayList;

public class menace implements Stratagy{
    private int worldX;
    private int worldY;
    private int gotoX;
    private int gotoY;
    private int x;
    private int y;
    private ArrayList<Location> otherShipsSpots;
    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) { //Moves arround until finds a ship and follows ship arround while shooting it
        this.x = x;
        this.y = y;
        this.worldX = worldX;
        this.worldY = worldY;
        gotoX = (int)(worldX*Math.random());
        gotoY = (int)(worldY*Math.random());

    }

    @Override
    public Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location currentLocation) {
        otherShipsSpots = playersSpots;
        x = currentLocation.getCol();
        y = currentLocation.getRow();
        if(playersSpots.size()>0)
        {
            double dist = Integer.MAX_VALUE;
            int j = -1;
            for(int i = 0; i<playersSpots.size(); i++) {
                double distTemp = Math.sqrt(
                        Math.pow(x - playersSpots.get(i).getCol(), 2) +
                                Math.pow(y - playersSpots.get(i).getRow(), 2));
                if (distTemp < dist) {
                    dist = distTemp;
                    j = i;
                }
            }
            if(Math.abs(playersSpots.get(j).getCol()-x)>50 && Math.abs(playersSpots.get(j).getRow()-y)>50 )
                return playersSpots.get(j);
            return new Location(x,y);
        }
        if(currentLocation.getCol()==gotoX && currentLocation.getRow()==gotoY)
        {
            gotoX = (int)(worldX*Math.random());
            gotoY = (int)(worldY*Math.random());
        }
        return new Location(gotoY,gotoX);
    }

    @Override
    public Location shoot(Boolean canShoot) {

        if(otherShipsSpots.size()>0)
        {
            double dist = Integer.MAX_VALUE;
            int j = -1;
            for(int i = 0; i<otherShipsSpots.size(); i++) {
                double distTemp = Math.sqrt(
                        Math.pow(x - otherShipsSpots.get(i).getCol(), 2) +
                                Math.pow(y - otherShipsSpots.get(i).getRow(), 2));
                if (distTemp < dist) {
                    dist = distTemp;
                    j = i;
                }
            }
            return otherShipsSpots.get(j);
        }
        return null;
    }
}
