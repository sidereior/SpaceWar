import java.awt.*;
import java.util.ArrayList;

public class Ship extends Sprite {
    final double speed = 2;
    final int radius = 150;

    private boolean jiggly = false;
    private int jiggs = 1;
    private double theta = 0;
    private double vx;
    private double vy;
    private Stratagy stratagy;
    private long start = System.currentTimeMillis();
    boolean canShoot = false;

    public Ship(double left, double top, int width, int height, String image, Stratagy s, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
    {
        //super();
        super(left, top, width, height, image, null, playersSpots, treasureSpots);
        stratagy = s;
        vx = 0;
        vy = 0;

    }

    //ArrayList<Location> play=new ArrayList<Location>();
    //        for(int i=0; i<playersSpots.size(); i++){
    //            double dist=Math.sqrt((Math.pow((playersSpots.get(i).getRow()-x),2)+Math.pow((playersSpots.get(i).getCol()-y),2)));
    //            if(dist<=250)
    //            {
    //                play.add(playersSpots.get(i));
    //            }
    //        }
    //
    //        ArrayList<Location> treas=new ArrayList<Location>();
    //        for(int i=0; i<treasureSpots.size(); i++){
    //            double dist=Math.sqrt((Math.pow((treasureSpots.get(i).getRow()-x),2)+Math.pow((treasureSpots.get(i).getCol()-y),2)));
    //            if(dist<=250)
    //            {
    //                treas.add(treasureSpots.get(i));
    //            }
    //        }


    public ArrayList<Location> removeOutsideRadius(ArrayList<Location> locs)
    {

        ArrayList<Location> radLocs = new ArrayList<>(locs);

        //System.out.println(radLocs.size());

        for(int i = 0; i < radLocs.size(); i++)
        {
            if(Math.sqrt(
                    Math.pow(radLocs.get(i).getCol()-getLeft(),2) +
                            Math.pow(radLocs.get(i).getRow()-getTop(),2))>radius)
            {
                radLocs.remove(i);
                i--;
            }

            if(i!=-1 && (Math.abs(radLocs.get(i).getCol()-getLeft())< 2 && Math.abs(radLocs.get(i).getRow()-getTop())< 2))
            {
                radLocs.remove(i);
                i--;
            }
        }
        //if(radLocs.size()!=0)
           // System.out.println("sprite at (" + (int)getLeft() + ", " + (int)getTop() + ") sees: ");
        //for (Location l: radLocs)
            //System.out.print(l.toString() + " ");
        return radLocs;
    }


    public void step(World world)
    {

        //.out.println(System.currentTimeMillis() - start);

        if(5000<System.currentTimeMillis() - start) {
            start = System.currentTimeMillis();
            canShoot = true;
        }



        Location moveTo = stratagy.move(removeOutsideRadius(world.getShipLocs()),removeOutsideRadius( world.getTreasuresLocs()), removeOutsideRadius(world.getBulletLocs()), new Location((int)getTop(),(int)getLeft()));
        Location ShootTo = stratagy.shoot(canShoot);

        if(ShootTo!=null && canShoot)
        {
            canShoot = false;
            world.makeBullet(getLeft()+getWidth()/2,getTop()-25,ShootTo.getCol(),ShootTo.getRow(),getImage());
        }






        //TODO only give objects within a certain radius!!!!



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
            jiggly = true;
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
        else
            jiggly = false;


        if (getLeft()+getWidth()/2 < 0)
            vx = Math.abs(vx);
        if (getLeft() + getWidth()/2 > world.getWidth())
            vx = -Math.abs(vx);
        if (getTop() + getHeight() > world.getHeight()-30)
            vy = -Math.abs(vy);
        if(getTop() + getHeight()/2<0 )
            vy = Math.abs(vy);


        setLeft(getLeft()+vx);
        setTop(getTop()+vy);
    }

    public double getAngle()
    {
        if(!jiggly)
            theta = Math.PI/2+Math.atan2(vy,vx);
        if(Math.random()<0.001)
            jiggs*=-1;
        return theta+=(Math.random()/100)*jiggs;
    }

    public double getVx(){return vx;}

    public double getVy(){return vy;}

    public void setVx(double vx){this.vx = vx;}

    public void setVy(double vy){this.vy = vy;}



}
