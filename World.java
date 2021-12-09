import java.awt.*;
import java.security.KeyStore.Entry;
import java.util.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class World
{
  final String treasureImageName = "treasure.png";
  final String bulletImageName = "dynamite.png";
  final String[] shipImageNames = new String[]{"spaceship-blue.png","spaceship-salmon.png","spaceship-yellow.png",
           "spaceship-pink.png","spaceship-red.png","spaceship-babyblue.png","spaceship-green.png",};
  private final int width;
  private final int height;
  private final int playerSize = 25;

  ArrayList<Stratagy> players;

  private ArrayList<Sprite> sprites;
  private ArrayList<Location> shipLocs;//TODO change to array?
  private ArrayList<Location> treasuresLocs;//TODO change to array?
  private ArrayList<Location> bulletLocs;//TODO change to array?

  private double bulletRad = 0;
  private int count;
  private  Map<String, Integer> scores = new HashMap<String, Integer>();


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
      sprites.add(new Ship(Math.random()*width, Math.random()*height, playerSize, playerSize, getImage(),s, null,null));
    }

  }

  public String getImage()
  {
    String s = shipImageNames[count];
    count++;
    return s;
  }

  public ArrayList<Location> getShipLocs() {
    return shipLocs;
  }

  public ArrayList<Location> getTreasuresLocs() {
    return treasuresLocs;
  }
  public ArrayList<Location> getBulletLocs() {
    return bulletLocs;
  }

  public void makeBullet(double x, double y, double a, double b, String playerImage)
  {
    sprites.add(new Bullet(x,y,10,10,bulletImageName,playerImage,null,null,Math.atan2(b-y,a-x)));
  }


  public void stepAll()
  {
    if(bulletRad==Math.PI*2)
      bulletRad = -Math.PI*2;
//    if(Math.random()<.01)
//      sprites.add(new SpaceTreasure(Math.random() * width, Math.random() * height, playerSize, playerSize, treasureImageName, null, null));

    //sprites.add(new SpaceTreasure((int)(Math.random()*getWidth()),(int)(Math.random()*getHeight()),playerSize,playerSize,treasureImageName, null));


    shipLocs = new ArrayList<>();
    treasuresLocs = new ArrayList<>();
    bulletLocs = new ArrayList<>();

    ArrayList<Sprite> treasures = new ArrayList<>();
    ArrayList<Sprite> bullets = new ArrayList<>();


    for (Sprite sprite : sprites) {//initial declaration

      if (sprite.getImage().equals(treasureImageName)) { // if its a treasure
        treasuresLocs.add(new Location((int)sprite.getTop(), (int)sprite.getLeft()));
        treasures.add(sprite);
      }
      else if (sprite.getImage().equals(bulletImageName)) //if its a bullet
      {
        bulletLocs.add(new Location((int) sprite.getTop(), (int) sprite.getLeft()));
        bullets.add(sprite);
      }
      else {   //if its a ship
        shipLocs.add(new Location((int) sprite.getTop(), (int) sprite.getLeft()));
        if(!scores.containsKey(sprite.getImage())){
          scores.put(sprite.getImage(),0);//sets their score as zero
        }
        else
        {
          scores.put(sprite.getImage(),scores.get(sprite.getImage()));//sets their score as zero
        }
        
      }
    }


    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite s = sprites.get(i);
      s.step(this);
      if(s.getImage().equals(treasureImageName) || s.getImage().equals(bulletImageName)) {
        // do nothuing
      }
      else
      {



      for (Sprite sprite: sprites)
      {
        if (!(sprite.getImage().equals(treasureImageName)|| sprite.getImage().equals(bulletImageName))&& !s.getImage().equals(sprite.getImage())) {
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


        //HERE
        ((Ship)s).getLocations(shipLocs,treasuresLocs);
        for(Sprite t: treasures)
        {
          if(s.touching(t))
          {
            scores.put(s.getImage(),(scores.get(s.getImage())+25));
            sprites.remove(t);
          }
        }
        for(Sprite u: bullets)
        {
          if(s.touching(u)&&(!s.getImage().equals(((Bullet)u).getPlayerImage())))//!make sure its not the same guy who just shot, how do you know who shot it
          {
            scores.put(s.getImage(),(scores.get(s.getImage())-25));//change this than to let than what you get, 20 pts?
            sprites.remove(u);//need to remove it
          }
        }
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
    sprites.add(new SpaceTreasure( x,y,playerSize,playerSize,treasureImageName,null,null));
    //System.out.println("mouseClicked:  " + x + ", " + y);
  }
  
  public void keyPressed(int key)
  {
   // System.out.println("keyPressed:  " + key);
  }
  
  public void keyReleased(int key)
  {
    //System.out.println("keyReleased:  " + key);
  }
  
  public String getTitle()
  {
    return "World";
  }

  public void paintComponent(Graphics g)
  {


    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height-30);
    g.setColor(new Color(105,105,105));
    g.fillRect(0,670,width, 30);

    for (int i = 0; i < sprites.size(); i++)
    {
      Sprite sprite = sprites.get(i);


      g.setColor(Color.WHITE);

      if(sprite.getImage().equals(treasureImageName)) {
        g.drawImage(Display.getImage(sprite.getImage(), sprite.getColors()),
                (int)sprite.getLeft(),
                (int)sprite.getTop(),
                sprite.getWidth(), sprite.getHeight(), null);
      }
      else if(sprite.getImage().equals(bulletImageName))
      {
        Graphics2D g2 = (Graphics2D)(g);

        AffineTransform old = g2.getTransform();

        g2.rotate(bulletRad+=.01, sprite.getLeft()+sprite.getWidth()/2,sprite.getTop()+sprite.getHeight()/2);

        Image image = Display.getImage(sprite.getImage(),null);


        g2.drawImage(image,
                (int)sprite.getLeft(),
                (int)sprite.getTop(),
                sprite.getWidth(), sprite.getHeight(), null);

        g2.setColor(new Color(255, 255, 255));

        g2.setTransform(old);

      }
      else
      {
        Graphics2D g2 = (Graphics2D)(g);

        AffineTransform old = g2.getTransform();

        g2.rotate(((Ship)sprite).getAngle(), sprite.getLeft()+sprite.getWidth()/2,sprite.getTop()+sprite.getHeight()/2);

        Image image = Display.getImage(sprite.getImage(),null);

        g2.drawImage(image,
                (int)sprite.getLeft(),
                (int)sprite.getTop(),
                sprite.getWidth(), sprite.getHeight(), null);

        g2.setColor(new Color(255, 255, 255));

        g2.setTransform(old);
      }
       //text right here 
       //HOW TO MAKE IT NOT GO THRU IT, IT IS REALLY ANNOYING BUT NOT REALLY A PROBLEM
       String scoreTotal="";
       
      
       for (Map.Entry<String,Integer> entry : scores.entrySet())
       {
          scoreTotal+=" " + entry.getKey() + ": " + entry.getValue();
       }
       g.setColor(Color.RED);
        int pixel=50;
        Font font = new Font("Arial", Font.PLAIN, pixel);
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        int textwidth = (int)(font.getStringBounds(scoreTotal, frc).getWidth());
        int textheight = (int)(font.getStringBounds(scoreTotal, frc).getHeight());
        while(textheight>=20 || textwidth>=1290)
        {
          pixel--;
          font = new Font("Arial", Font.PLAIN, pixel);
          frc = new FontRenderContext(new AffineTransform(), true, true);
           textwidth = (int)(font.getStringBounds(scoreTotal, frc).getWidth());
         textheight = (int)(font.getStringBounds(scoreTotal, frc).getHeight());
        }
       //1280 - (scoreTotal.length()*26)
       g.setFont(font);
       g.drawString(scoreTotal, 5, 595);
    }
  }
}