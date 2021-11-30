import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;

public class SpaceTreasure extends Sprite {


    public SpaceTreasure(double left, double top, int width, int height, String image, ArrayList<Location> playersSpots, ArrayList<Location> treasureSpots) {
        super(left, top, width, height, image, null, playersSpots, treasureSpots);
    }



    public void step(World world)
    {

    }
}
