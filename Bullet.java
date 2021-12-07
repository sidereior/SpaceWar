import java.util.ArrayList;

public class Bullet extends Sprite{

    final double speed = 12;

    private double theta;
    private String playerImage;


    Bullet(double left, double top, int width, int height, String image,String playerImage, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots, double theta)
    {
        super(left, top, width, height, image, null, playersSpots, treasureSpots);

        this.theta = theta;
        this.playerImage = playerImage;
    }

    @Override
    public void step(World world) {
        setLeft(getLeft()+speed*Math.cos(theta));
        setTop(getTop()+speed*Math.sin(theta));
    }
}
