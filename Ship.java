import java.awt.*;
import java.util.ArrayList;

public class Ship extends Sprite{
    final double speed = 2;

    private double vx;
    private double vy;
    private Stratagy stratagy;

    public Ship(double left, double top, int width, int height, String image, Stratagy s, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
    {
        //super();
        super(left, top, width, height, image, playersSpots,  treasureSpots);
        stratagy = s;
        vx = 0;
        vy = 0;

    }

    public void getLocations(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
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


        for(Stratagy s: world.players) {
//            if(touching(s.getX(),s.getY()))
//                System.out.println("sprite at: (" + getLeft() + ", " + getTop() + ") is toucnhing sprite at: (" + s.getX() + ", " + s.getY() + ")");

//            while(touchingAfterDisplacement(s.getX(),s.getY(),vx,0)) {//needs to be fixed to include all corners and not just
//                System.out.println("oh oh");
//                if(vx>0)
//                    vx-=0.1;
//                else
//                    vx+=0.1;
//                //System.out.println("right o left");
//            }
//            while(touchingAfterDisplacement(s.getX(),s.getY(),0,vy)) {
//                if(vy>0)
//                    vy-=0.1;
//                else
//                    vy+=0.1;
//                //System.out.println("up o down");
//            }
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
