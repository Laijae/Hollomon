import java.util.Objects;

public class Card implements Comparable<Card>{
    
    private long id;
    private String name;
    private Rank rank;
    private long price;

    public Card(long id, String name, Rank rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    //constructor with price
    public Card(long id, String name, Rank rank, long price) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    @Override
    public int compareTo(Card card) {
        //First compare the rank of the cards, (ranks are taken from the order they are declared in the Rank enum)
        int rankComparison = this.rank.compareTo(card.getRank());
        //If the rank is not the same return the comparison
        if(rankComparison != 0){
            return rankComparison;
        }

        //If the rank is the same, compare the name of the cards
        int nameComparison = this.name.compareTo(card.getName());
        //If the name is not the same return the comparison
        if(nameComparison != 0){
            return nameComparison;
        }

        //If the name is the same, compare the id of the cards
        int idComparison = Long.compare(this.id, card.getId());
        //If the id is not the same return the comparison
        if(idComparison != 0){
            return idComparison;
        }

        //if the rank, name and id are the same return 0
        return 0;

    }

    @Override
    public String toString(){
        return "Card [id=" + id + ", name=" + name + ", rank=" + rank + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.rank);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the card is compared with itself
        if(this == obj){
            return true;
        } 
        // Check if the object is null or is not an instance of Card
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
       
        // Check if the card has the same numeric id, name and rank
        Card card = (Card) obj;
        return (this.id == card.id) && 
                (this.name.equals(card.name)) &&
                (this.rank == card.rank);
        
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }




}
