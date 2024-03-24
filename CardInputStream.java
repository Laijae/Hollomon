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
            //Check tagline
            String tagline = readResponse();
            if(tagline.equals("OK")){
                return null;
            }
            else if(!tagline.equals("CARD")){ //if tagline is not CARD or OK, throw an exception
                throw new IOException("error reading card: expected CARD tagline, got " + tagline);
            }

            //read card details
            long id = Long.parseLong(readResponse());
            String name = readResponse();
            Rank rank = Rank.valueOf(readResponse());
            long price = Long.parseLong(readResponse());
            //create and send
            return new Card(id, name, rank, price);
        }
        catch(IOException e){
            System.out.println("Error reading card: " + e.getMessage());
            return null;
        }
    }

}
