import java.awt.*;
import java.util.ArrayList;

public class Ship extends Sprite {
    final double speed = 2;

    private double vx;
    private double vy;
    private Stratagy stratagy;

    public Ship(double left, double top, int width, int height, String image, Stratagy s, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
    {
        //super();
        super(left, top, width, height, image, null, playersSpots, treasureSpots);
        stratagy = s;
        vx = 0;
        vy = 0;

    }

    public void getLocations(ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
    {

          ArrayList<Location> play=new ArrayList<Location>();
        for(int i=0; i<playersSpots.size(); i++){
            double dist=Math.sqrt((Math.pow((playersSpots.get(i).getRow()-x),2)+Math.pow((playersSpots.get(i).getCol()-y),2)));
            if(dist<=250)
            {
                play.add(playersSpots.get(i));
            }
        }   
       
        ArrayList<Location> treas=new ArrayList<Location>();
        for(int i=0; i<treasureSpots.size(); i++){
            double dist=Math.sqrt((Math.pow((treasureSpots.get(i).getRow()-x),2)+Math.pow((treasureSpots.get(i).getCol()-y),2)));
            if(dist<=250)
            {
                treas.add(treasureSpots.get(i));
            }
        }    
         
        stratagy.getLocations(play, treas);
        //changes here
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

        if(vx+vy==0){
            int r = (int)(Math.random()*4);
            if(r==0)
                vx=Math.random();
            if(r==1)
                vx=-Math.random();
            if(r==2)
                vy=Math.random();
            if(r==3)
                vy=-Math.random();
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

    public double getVx(){return vx;}

    public double getVy(){return vy;}

    public void setVx(double vx){this.vx = vx;}

    public void setVy(double vy){this.vy = vy;}



}
