import java.io.*;
import java.net.*;
import java.util.*;

public class HollomonClient {

    private String server;
    private int port;
    private Socket sock;
    private BufferedReader reader;
    private BufferedWriter writer;
    private CardInputStream cardInputStream;
    


    public HollomonClient (String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void connect() {
        try{
            //Create a socket to the server
            this.sock = new Socket(server, port);

            //Create a reader and writer to the server
            reader = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream()));
            cardInputStream = new CardInputStream(this.sock.getInputStream());

        }
        catch(IOException e){
            System.out.println("Error connecting to server: " + e.getMessage());
        }

    }

    public List<Card> login(String username, String password) {
        //ensure connected to server
        ensureConnected();


        //send login message
        try{
            writer.write(username.toLowerCase() + "\n");
            writer.write(password + "\n");
            writer.flush();

            //read the response
            String response = reader.readLine();

            //Check for successful login response
            if(response.equals("User " + username + " logged in successfully.")){
                
                List<Card> cards = new ArrayList<>();

                //read the cards
                while (true){
                    Card card = cardInputStream.readCard();
                    if(card == null){
                        break;
                    }
                    cards.add(card);
                }

                //ordering the cards
                Collections.sort(cards);

                return cards;

            }
            else{
                System.out.println("Error logging in: " + response);
                return null;
            }


        }
        catch(IOException e){
            System.out.println("Error sending login message: " + e.getMessage());
            return null;
        }
        
        

    }


    public void close() {
        //Close the connection to the server
        try{
            if(sock != null){
                sock.close();
            }
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
            if(cardInputStream != null){
                cardInputStream.close();
            }

        }
        catch(IOException e){
            System.out.println("Error closing connection: " + e.getMessage());
        }

    }

    public void ensureConnected() {
        if(sock == null || !sock.isConnected() || sock.isClosed()){
            connect();
        }
    }

    public long getCredits() {
        //ensure connected to server
        ensureConnected();

        try{
            //send the command: CREDITS
            writer.write("CREDITS" + "\n");
            writer.flush();

            //read the response
            String credits = reader.readLine();
            //read the tagline
            String tagline = reader.readLine();

            //check for successful response
            if(tagline.equals("OK")){
                return Long.parseLong(credits);
            }
            else{
                throw new IOException("Error: incorrect tagline for CREDITS command: " + tagline);
            }

        }
        catch(IOException e){
            System.out.println("Error getting credits: " + e.getMessage());
            return -1;
        }
        

    }

    public List<Card> getCards() {
        //ensure connected to server
        ensureConnected();

        try{
            //send the command: CARDS
            writer.write("CARDS" + "\n");
            writer.flush();

            List<Card> cards = new ArrayList<>();

            //read the cards
            while (true){
                Card card = cardInputStream.readCard();
                if(card == null){
                    break;
                }
                cards.add(card);
            }

            //ordering the cards
            Collections.sort(cards);

            return cards;
            
        }
        catch (IOException e){
            System.out.println("Error getting cards: " + e.getMessage());
            return null;
        }
    }

    


}
