import java.util.ArrayList;

public class SSTester implements Stratagy
{
    private int index;

    private Location[] otherShipsSpots;
    private ArrayList<Location> treasureSpots;
    private int x;
    private int y;
    private int worldX;
    private int worldY;

    @Override
    public void newRound(int numberofOpponents, int x, int y, int worldX, int worldY) {
        otherShipsSpots = new Location[numberofOpponents];
        treasureSpots = new ArrayList<>();
        this.x = x;
        this.y = y;
        this.worldX = worldX;
        this.worldY = worldY;
    }

    @Override
    public void getLocations(String locations) {
        //TODO just move arraylist
        treasureSpots = new ArrayList<>();
        int semis = locations.indexOf(";;");
        String ships = locations.substring(0,semis);
        String treasures = locations.substring(semis+1,locations.length()-1);
        String[] otherShipsSpotsString = ships.split(";");
        String[] otherTreasuresSpotsString = new String[0];
        if(treasures.length()>1)
           otherTreasuresSpotsString = treasures.split(";");
        for(int i = 0; i< otherShipsSpotsString.length; i++)
            otherShipsSpots[i] = Location.StringToLocation(otherShipsSpotsString[i]);
        //#ImATerribleCoder ;)
        for(int i = 1; i< otherTreasuresSpotsString.length; i++) {
            treasureSpots.add(Location.StringToLocation(otherTreasuresSpotsString[i]));
        }
        x = otherShipsSpots[index].getCol();
        y = otherShipsSpots[index].getRow();
    } //DO NOT EDIT

    @Override
    public Location move() {
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
            System.out.println("moving from (" + x + "," + y + ") to " + treasureSpots.get(j).toString());
            System.out.println(treasureSpots.get(j).toString());
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
