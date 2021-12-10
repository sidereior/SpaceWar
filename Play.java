import java.util.ArrayList;

public class Play
{
    public static void main(String[] args)
    {

        //Might not work with lots of bots
        ArrayList<Stratagy> players = new ArrayList<>();


        players.add(new menace());
        players.add(new SSGotoNearest());
        players.add(new SSGotoNearest());



        Display display = new Display(1300, 600, players);
        display.run();

    }




}
