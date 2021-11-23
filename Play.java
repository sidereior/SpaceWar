import java.util.ArrayList;

public class Play
{
  public static void main(String[] args)
  {



    ArrayList<Stratagy> players = new ArrayList<>();

    players.add(new SSTester());
    players.add(new SSTester());


    Display display = new Display(1300, 700, players);
    display.run();






    //int winner = SpaceWar.play(players,1);



    //shipsTester(10000000);

  }




}