import java.awt.*;
import java.util.*;

public class World
{
  final String treasureImageName = "triangle.png";
  final String[] shipImageNames = new String[]{"CyanCircle.png", "PinkCircle.png", "PurpleCircle.png", "RedCircle.png","circleBlue.png","circleBlue.png"};

  ArrayList<Stratagy> players;

  private ArrayList<Sprite> sprites;
  private final int width;
  private final int height;
  private int count;



  public World(int w, int h, ArrayList<Stratagy> players)//
  {
    width = w;
    height = h;
    this.players = players;
    count = 0;
    
    sprites = new ArrayList<>();

    for(int i = 0; i<players.size(); i++) {
      Stratagy s = players.get(i);
      int x = (int)(Math.random()*width);
      int y = (int)(Math.random()*height);
      s.newRound(players.size(),x,y,width,height);
      s.setIndex(i);
      sprites.add(new Ship(Math.random()*width, Math.random()*height, 50, 50, getImage(),s, null,null));
    }
    //sprites.add(new SpaceTreasure( 300,500,50,50,treasureImageName,null, null));
    //sprites.add(new SpaceTreasure( 150,150,50,50,treasureImageName,null));
  }

  public String getImage()
  {
    String s = shipImageNames[count];
    count++;
    return s;
  }
  
  public void stepAll()
  {
    if(Math.random()<.03)
      sprites.add(new SpaceTreasure(Math.random() * width, Math.random() * height, 50, 50, treasureImageName, null, null));

    //sprites.add(new SpaceTreasure((int)(Math.random()*getWidth()),(int)(Math.random()*getHeight()),50,50,treasureImageName, null));


    ArrayList<Location> shipLocs = new ArrayList<>();
    ArrayList<Location> treasuresLocs = new ArrayList<>();

    ArrayList<Sprite> treasures = new ArrayList<>();


    for (Sprite sprite : sprites) {

      if (sprite.getImage().equals(treasureImageName)) {
        treasuresLocs.add(new Location((int)sprite.getTop(), (int)sprite.getLeft()));
        treasures.add(sprite);
      }
      else {
        shipLocs.add(new Location((int) sprite.getTop(), (int) sprite.getLeft()));
      }
    }


    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      s.step(this);
      if(s.getImage().equals(treasureImageName)) {
        // do nothuing
      }
      else
      {


      for (Sprite sprite: sprites)
      {
        if (!sprite.getImage().equals(treasureImageName)&& !s.getImage().equals(sprite.getImage())) {
          double push = .3;
          while ((sprite.touching(s))) {

            if (sprite.getLeft() > s.getLeft()) {
              sprite.setLeft(sprite.getLeft() + push);
              s.setLeft(s.getLeft() - push);
            } else {
              sprite.setLeft(sprite.getLeft() - push);
              s.setLeft(s.getLeft() + push);
            }
            if (sprite.getTop() > s.getTop()) {
              sprite.setTop(sprite.getTop() + push);
              s.setTop(s.getTop() - push);
            } else {
              sprite.setTop(sprite.getTop() - push);
              s.setTop(s.getTop() + push);
            }
          }
        }
      }



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
      g.drawImage(Display.getImage(sprite.getImage(), sprite.getColors()),
              (int)sprite.getLeft(),
              (int)sprite.getTop(),
              sprite.getWidth(), sprite.getHeight(), null);

      g.setColor(Color.WHITE);

      if(sprite.getImage().equals(treasureImageName)) {//do nothing
      }
        else
        {
          for (Sprite s : sprites) {
            if (!s.getImage().equals(sprite.getImage())) {
              if (sprite.touching(s)) {
                g.drawString("touching",
                        (int) sprite.getLeft(),
                        (int) sprite.getTop() + sprite.getWidth());


              }
            }
          }
        }
    }
  }
}