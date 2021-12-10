import java.util.ArrayList;

public class SSGotoNearest implements Stratagy
{
    private ArrayList<Location> otherShipsSpots;
    private ArrayList<Location> treasureSpots;
    private int x;
    private int y;

    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) {
        otherShipsSpots = new ArrayList<>();
        treasureSpots = new ArrayList<>();
        this.x = x;
        this.y = y;
    }


    @Override
    public Location move(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, ArrayList<Location> bulletSpots, Location curSpot) {
        this.treasureSpots = treasureSpots;
        x = curSpot.getCol();
        y = curSpot.getRow();


        double dist = Integer.MAX_VALUE;
        int j = -1;
        for(int i = 0; i<treasureSpots.size(); i++)
        {
            double distTemp = Math.sqrt(
                    Math.pow(x-treasureSpots.get(i).getCol(),2) +
                            Math.pow(y-treasureSpots.get(i).getRow(),2));
            if(distTemp<dist) {
                j = i;
                dist = distTemp;
            }

        }
        if(j!=-1) {
            return treasureSpots.get(j);
        }
        else
            return new Location(y,x);
    }

    @Override
    public Location shoot(Boolean canShoot) {
        return null;
        //return new Location(worldY/2,worldX/2);
    }




}
