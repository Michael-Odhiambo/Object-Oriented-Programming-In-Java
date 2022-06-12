package Blackjack.Iteration3;

/**
 * This defines the interface that a class must implement if its instances are to be
 * used as Dealers. It makes sense to make this ann interface since a Player may also
 * act as a Dealer and Java lacks multiple inheritance. The dealer interface is an
 * example of a Mixin. Mixins add additional capabilities to an object. Dealer would
 * add more capabilities to a Player that implements it.
 */

public interface Dealer {

    public void hit( Player player );

    // Used by the player to communicate state to the dealer
    public void blackjack( Player player );
    public void busted( Player player );
    public void standing( Player player );

    public void doneBetting( Player player );
}
