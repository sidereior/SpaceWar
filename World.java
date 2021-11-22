import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class World
{
  final String shipImageName = "circle.png";
  final String treasureImageName = "triangle.png";

  public static void main(String[] args)
  {
      run();
  }
  
  public static void run()
  {
    Display display = new Display(1300, 700);
    display.run();
  }
  
  private ArrayList<Sprite> sprites;
  private final int width;
  private final int height;
  
  public World(int w, int h)
  {
    width = w;
    height = h;
    
    sprites = new ArrayList<>();
    double dir;


    ArrayList<Stratagy> players = new ArrayList<>();

    Stratagy tester = new SSTester();
    players.add(tester);



    for(int i = 0; i<players.size(); i++) {
      Stratagy s = players.get(i);
      int x = (int)(Math.random()*width);
      int y = (int)(Math.random()*height);
      s.newRound(players.size(),x,y,width,height);
      s.setIndex(i);
      sprites.add(new Ship(50, 50, 50, 50, shipImageName,s, null,null));
    }
    sprites.add(new SpaceTreasure( 300,500,50,50,treasureImageName,null, null));
    //sprites.add(new SpaceTreasure( 150,150,50,50,treasureImageName,null));




  }
  
  public void stepAll()
  {
//    if(Math.random()<.003)
//      sprites.add(new SpaceTreasure( 400,100,50,50,treasureImageName,null));

    //sprites.add(new SpaceTreasure((int)(Math.random()*getWidth()),(int)(Math.random()*getHeight()),50,50,treasureImageName, null));


    ArrayList<Location> shipLocs = new ArrayList<>();
    ArrayList<Location> treasuresLocs = new ArrayList<>();

    ArrayList<Sprite> treasures = new ArrayList<>();


    for (Sprite sprite : sprites) {
      if (sprite.getImage().equals(shipImageName)) {
        shipLocs.add(new Location((int) sprite.getTop(), (int) sprite.getLeft()));
      }
      if (sprite.getImage().equals(treasureImageName)) {
        treasuresLocs.add(new Location((int)sprite.getTop(), (int)sprite.getLeft()));
        treasures.add(sprite);
      }
    }


    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      s.step(this);
      if(s.getImage().equals(shipImageName)) {
        ((Ship)s).getLocations(shipLocs,treasuresLocs);
        for(Sprite t: treasures)
          if(s.touching(t))
            sprites.remove(t);
      }
    }
  }
  
  public int getWidth()
  {
    return width;
  }
  
  public int getHeight()
  {
    return height;
  }
  
  public int getNumSprites()
  {
    return sprites.size();
  }
  
  public Sprite getSprite(int index)
  {
    return sprites.get(index);
  }

  public void mouseClicked(int x, int y)
  {
    sprites.add(new SpaceTreasure( x,y,50,50,treasureImageName,null,null));
    System.out.println("mouseClicked:  " + x + ", " + y);
  }
  
  public void keyPressed(int key)
  {
    System.out.println("keyPressed:  " + key);
  }
  
  public void keyReleased(int key)
  {
    System.out.println("keyReleased:  " + key);
  }
  
  public String getTitle()
  {
    return "World";
  }

  public void paintComponent(Graphics g)
  {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);
//      g.drawImage(Display.getImage(sprite.getImage(), sprite.getColors()),
//                  (int)sprite.getLeft(), (int)sprite.getTop(),
//                  sprite.getWidth(), sprite.getHeight(), null);
      g.drawImage(Display.getImage(sprite.getImage()),
              (int)sprite.getLeft(),
              (int)sprite.getTop(),
              sprite.getWidth(), sprite.getHeight(), null);
    }
  }
}