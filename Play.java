import java.util.ArrayList;

public class Play
{
  public static void main(String[] args)
  {

  //Might not work with lots of bots
    ArrayList<Stratagy> players = new ArrayList<>();

    players.add(new SSTester());
    players.add(new SSTester());
    players.add(new SSTester());
    players.add(new SSTester());
    players.add(new SSTester());

    Display display = new Display(1300, 600, players);
    display.run();

  }




}