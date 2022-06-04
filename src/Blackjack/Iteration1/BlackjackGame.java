package Blackjack.Iteration1;

public class BlackjackGame {

    public static void main( String[] args ) {
        DeckPile cards = new DeckPile();
        for ( int i = 0; i < 4; i++ ) {
            cards.shuffle();
            Deck deck = new Deck();
            deck.addToStack( cards );
            cards.shuffle();
        }
        Hand dealerHand = new Hand();
        BlackjackDealer dealer = new BlackjackDealer( "Dealer", dealerHand, cards );
        Hand humanHand = new Hand();
        Player player = new HumanPlayer( "Michael", humanHand );
        dealer.addListener( Console.INSTANCE );
        player.addListener( Console.INSTANCE );
        dealer.addPlayer( player );
        dealer.newGame();
    }
}
