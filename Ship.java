import java.awt.*;
import java.util.ArrayList;

public class Ship extends Sprite {
    final double speed = 2;

    private double vx;
    private double vy;
    private Stratagy stratagy;
    private long start = System.currentTimeMillis();
    boolean canShoot = true;

    public Ship(double left, double top, int width, int height, String image, Stratagy s, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots)
    {
        //super();
        super(left, top, width, height, image, null, playersSpots, treasureSpots);
        stratagy = s;
        vx = 0;
        vy = 0;

    }

    public void step(World world)
    {

        System.out.println(System.currentTimeMillis() - start);

        if(5000<System.currentTimeMillis() - start) {
            start = System.currentTimeMillis();
            canShoot = true;
        }
        Location moveTo = stratagy.move(world.getShipLocs(), world.getTreasuresLocs());
        Location ShootTo = stratagy.shoot(canShoot);

        if(ShootTo!=null && canShoot)
        {
            canShoot = false;
            world.makeBullet(getLeft(),getTop(),ShootTo.getCol(),ShootTo.getRow(),getImage());
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
