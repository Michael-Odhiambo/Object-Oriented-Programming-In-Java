package Blackjack.Iteration3;

public interface PlayerListener {

    public void playerChanged( Player player );
    public void playerBusted( Player player );
    public void playerBlackjack( Player player );
    public void playerStanding( Player player );
    public void playerWon( Player player );
    public void playerLost( Player player );
    public void playerStandoff( Player player );

}
