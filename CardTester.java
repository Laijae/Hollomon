import java.util.*;


public class CardTester {

    public static void main(String[] args){

        //Create some cards to test
        Card card1 = new Card(1, "Card1", Rank.UNIQUE);
        Card card2 = new Card(2, "Card2", Rank.UNIQUE);      
        Card card3 = new Card(3, "Card3", Rank.RARE);
        Card card4 = new Card(4, "Card4", Rank.RARE);
        Card card5 = new Card(5, "Card5", Rank.UNCOMMONN);
        Card card6 = new Card(6, "Card6", Rank.UNCOMMONN);
        Card card7 = new Card(7, "Card7", Rank.COMMON);
        Card card8 = new Card(8, "Card8", Rank.COMMON);
        
        //Testing equals
        assert card1.equals(card1) : "Error .equals() : card1 should be equal to itself";
        assert !card1.equals(card2) : "Error .equals() : card1 should not be equal to card2";
        assert !card1.equals(null) : "Error .equals() : card1 should not be equal to null";

        //Testing hashCode
        int hash1 = card1.hashCode();
        int hash2 = card2.hashCode();

        assert hash1 != hash2 : "Error .hashCode() : card1 and card2 should have different hash codes";
        assert hash1 == card1.hashCode() : "Error .hashCode() : card1 should have the same hash code";

        //Testing toString
        System.out.println("Card 1 as a string: " + card1.toString());
        System.out.println("Card 2 as a string: " + card2.toString());
        System.out.println("Card 3 as a string: " + card3.toString());

        //Testing compareTo using assert
        assert card1.compareTo(card1) == 0 : "Error .compareTo() : card1 should be equal to itself";
        assert card1.compareTo(card2) < 0 : "Error .compareTo() : card1 should be less than card2";
        assert card3.compareTo(card1) > 0 : "Error .compareTo() : card3 should be greater than card1";

        //testing adding to a hash set
        HashSet<Card> cardSet = new HashSet<>();
        //addinng cards in a randomised order
        cardSet.add(card3);
        cardSet.add(card1);
        cardSet.add(card2);
        cardSet.add(card4);
        cardSet.add(card5);
        cardSet.add(card6);

        //cards should be printed in order defined by compareTo
        System.out.println("Cards in the set should be ordered: ");
        for(Card card : cardSet){
            System.out.println(card);
        }
        
        //testing adding to a tree set
        TreeSet<Card> cardTree = new TreeSet<>();
        //adding cards in a randomised order
        cardTree.add(card3);
        cardTree.add(card1);
        cardTree.add(card2);
        cardTree.add(card4);
        cardTree.add(card5);
        cardTree.add(card6);

        //cards should be printed in order defined by compareTo
        System.out.println("Cards in the tree should be ordered: ");
        for(Card card : cardTree){
            System.out.println(card);
        }

        



    }
    
}
