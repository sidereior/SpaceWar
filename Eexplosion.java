import java.awt.*;

public class Eexplosion extends Sprite {

    private final String[] images = new String[]{"explosions/1.png","explosions/2.png","explosions/3.png","explosions/4.png","explosions/5.png"
            ,"explosions/6.png","explosions/7.png","explosions/8.png","explosions/9.png", "square.png"};

    private double spriteIndex = 0;
    private int time;

    Eexplosion(int left, int top, int width, int height)
    {
        super(left, top, width, height, "explosions/1.png", (Color[]) null, null, null);
        time =  (int)System.currentTimeMillis();
    }

    @Override
    public String getImage() {
        spriteIndex+=0.1;
        return images[(int)spriteIndex];
    }


}
