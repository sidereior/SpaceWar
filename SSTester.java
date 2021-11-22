import java.util.ArrayList;

public class SSTester implements Stratagy
{
    private int index;

    private ArrayList<Location> otherShipsSpots;
    private ArrayList<Location> treasureSpots;
    private int x;
    private int y;
    private int worldX;
    private int worldY;

    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) {
        otherShipsSpots = new ArrayList<>();
        treasureSpots = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    @Override
    public void getLocations(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots) {
        //TODO just move arraylist
        this.otherShipsSpots = playersSpots;
        this.treasureSpots = treasureSpots;

       //System.out.println(index);

        x = otherShipsSpots.get(index).getCol();
        y = otherShipsSpots.get(index).getRow();
    } //DO NOT EDIT

    @Override
    public Location move() {
        //if(treasureSpots.size()>0)
            //System.out.println(treasureSpots.get(0).toString());
        //else
            //System.out.println("bruh");
        //return location of where you want to move to
        //keep in mind that your ships spot is included in otherShipSpots
        //write code here:

        double dist = 999999;
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
            //System.out.println("moving from (" + x + "," + y + ") to " + treasureSpots.get(j).toString());
            //System.out.println(treasureSpots.get(j).toString());
            return treasureSpots.get(j);
        }
        else
            return new Location(y,x);
    }

    @Override
    public void roundEnded(boolean outcome, int[] opScores, int winner) {
        //You may edit here but it is not require
    }

    @Override
    public void setIndex(int index)
    {
        this.index = index;
    }
}
