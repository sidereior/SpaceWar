import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import javax.imageio.*;
import javax.swing.*;

public class Display extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{

  private static Map<String, BufferedImage> images = new HashMap<>();
  private static final Color PRIMARY = Color.RED;
  private static final Color SECONDARY = Color.GREEN;
  public static Image getImage(String name, Color[] colors)
  {
    try
    {
      BufferedImage image = images.get(name);
      if (image == null)
      {
        URL url = Display.class.getResource(name);
        if (url == null)
          throw new RuntimeException("unable to load image:  " + name);
        image = ImageIO.read(url);
        if(colors != null) {
          for(int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
              if (image.getRGB(i, j) == PRIMARY.getRGB()) image.setRGB(i, j, colors[0].getRGB());
              else if (image.getRGB(i, j) == SECONDARY.getRGB()) image.setRGB(i, j, colors[1].getRGB());
            }
          }
        }
        images.put(name, image);
      }
      return image;
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  private int mouseMoveX;
  private int mouseMoveY;
  private JFrame frame;
  private int mouseX;
  private int mouseY;
  private World world;
  private ArrayList<Stratagy> players;
  private Queue<KeyEvent> keys;

  public Display(final int width, final int height, ArrayList<Stratagy> players)
  {
    keys = new ConcurrentLinkedQueue<KeyEvent>();
    mouseX = -1;
    mouseY = -1;
    this.players = players;

    try
    {
      SwingUtilities.invokeAndWait(new Runnable() { public void run() {
        world = new World(width, height, players);
//omoik
        frame = new JFrame();
        frame.setTitle("World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(Display.this);
        addMouseListener(Display.this);
        addMouseMotionListener(Display.this);
        frame.getContentPane().add(Display.this);

        frame.pack();
        frame.setVisible(true);
      }});
    }
    catch(Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void paintComponent(Graphics g)
  {
    try
    {
      world.paintComponent(g);
    }
    catch(Exception e)
    {
      e.printStackTrace();  //show error
      setVisible(false);  //stop drawing so we don't keep getting the same error
    }
  }

  public void run()
  {
    while (true)
    {
      frame.setTitle(world.getTitle());
      world.stepAll();
      repaint();
      try { Thread.sleep(10); } catch(Exception e) { }

      while (!keys.isEmpty())
      {
        KeyEvent event = keys.poll();
        if (event.getID() == KeyEvent.KEY_PRESSED)
          world.keyPressed(event.getKeyCode());
        else if (event.getID() == KeyEvent.KEY_RELEASED)
          world.keyReleased(event.getKeyCode());
        else
          throw new RuntimeException("Unexpected event type:  " + event.getID());
      }

      if (mouseX != -1)
      {
        world.mouseClicked(mouseX, mouseY);
        mouseX = -1;
        mouseY = -1;
      }
      //world.mouseMoved(mouseMoveX,mouseMoveY);
    }
  }

  public void keyPressed(KeyEvent e)
  {
    keys.add(e);
  }

  public void keyReleased(KeyEvent e)
  {
    keys.add(e);
  }

  public void keyTyped(KeyEvent e)
  {
    //ignored
  }

  public void mousePressed(MouseEvent e)
  {
    mouseX = e.getX();
    mouseY = e.getY();
  }

  public void mouseReleased(MouseEvent e)
  {
  }

  public void mouseClicked(MouseEvent e)
  {
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  public double getMouseX(){
    return mouseMoveX;
  }
  public double getMouseY(){
    return mouseMoveY;
  }
  @Override
  public void mouseMoved(MouseEvent e) {
    mouseMoveX = e.getX();
    mouseMoveY = e.getY();
    //System.out.println("mouse move in display: " + mouseMoveX);


  }
}