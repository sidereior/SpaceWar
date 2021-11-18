import java.util.ArrayList;

public class Ship extends Sprite{
    final double speed = 2;

    private double vx;
    private double vy;
    private Stratagy stratagy;

    public Ship(double left, double top, int width, int height, String image, String location, Stratagy s)
    {
        //super();
        super(left, top, width, height, image, location);
        stratagy = s;
        vx = 0;
        vy = 0;

    }

    public void getLocations(Location[] playersSpots, ArrayList<Location> treasureSpots)
    {

        //TODO only give objects within a certain radius!!!!
        stratagy.getLocations(playersSpots, treasureSpots);
    }


    public void step(World world)
    {
            //TODO FIX WHEN TRESURE IS DIRECTLY ABOVE SHIP IT STOPS MOVING

        Location moveTo = stratagy.move();
        if(Math.abs(moveTo.getCol()-getLeft()) >1.1 &&
                Math.abs(moveTo.getRow()-getTop())>1.1) {

            vx = Math.cos(Math.atan2(moveTo.getRow()-getTop(),moveTo.getCol()-getLeft())) * speed;
            vy = Math.sin(Math.atan2(moveTo.getRow()-getTop(),moveTo.getCol()-getLeft())) * speed;
        }
        else
        {
            vx = 0;
            vy = 0;
        }


        if (getLeft()+getWidth()/2 < 0)
            vx = Math.abs(vx);
        if (getLeft() + getWidth()/2 > world.getWidth())
            vx = -Math.abs(vx);
        if (getTop() + getHeight()/2 > world.getHeight())
            vy = -Math.abs(vy);
        if(getTop() + getHeight()/2<0 )
            vy = Math.abs(vy);

        setLeft(getLeft()+vx);
        setTop(getTop()+vy);







    }
}
