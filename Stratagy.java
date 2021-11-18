public interface Stratagy
{

  void newRound(int numberofOpponents, int x, int y, int worldX, int worldY);

  void getLocations(String locations);

  public Location move();

  void roundEnded(boolean outcome, int[] opScores, int winner);

  void setIndex(int index);


}