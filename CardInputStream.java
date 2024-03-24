import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class CardInputStream extends InputStream{

    private BufferedReader reader;

    public CardInputStream(InputStream input) {
        this.reader = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public int read() {
        try{
            return reader.read();
        }
        catch(IOException e){
            System.out.println("Error reading input stream: " + e.getMessage());
            return -1;
        }
    }
    
    @Override
    public void close() {
        try{
            reader.close();
        }
        catch(IOException e){
            System.out.println("Error closing input stream: " + e.getMessage());
        }
      
    }

    public String readResponse(){
        try{
            return reader.readLine();
        }
        catch(IOException e){
            System.out.println("Error reading response: " + e.getMessage());
            return null;
        }
    }

    public Card readCard(){
        try{
            //expecting tagline "CARD"
            if (!reader.readLine().equals("CARD")){
                throw new IOException("expecting CARD tagline");
            }

            //read the id
            long id = Long.parseLong(reader.readLine());

            //read the name
            String name = reader.readLine();

            //read the rank
            Rank rank = Rank.valueOf(reader.readLine());

            //read price
            long price = Long.parseLong(reader.readLine());

            //create card object
            Card card = new Card(id, name, rank, price);
         
            return card;
            
        }
        catch(IOException e){
            System.out.println("Error reading card: " + e.getMessage());
            return null;
        }


    }

}
