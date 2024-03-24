import java.io.*;
import java.net.*;
import java.util.*;

public class HollomonClient {

    private String server;
    private int port;
    private Socket sock;
    private BufferedReader reader;
    private BufferedWriter writer;


    public HollomonClient (String server, int port) {
        this.server = server;
        this.port = port;
    }

    public void connect() {
        try{
            //Create a socket to the server
            this.sock = new Socket(server, port);

            //Create a reader and writer to the server
            reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

        }
        catch(IOException e){
            System.out.println("Error connecting to server: " + e.getMessage());
        }

    }

    public List<Card> login(String username, String password) {
        //connect to server
        connect();

        //send login message
        try{
            writer.write(username.toLowerCase() + "\n");
            writer.write(password + "\n");
            writer.flush();

            //read the response
            String response = reader.readLine();

            //Check for successful login response
            if(response.equals("User " + username + " logged in successfully.")){
                CardInputStream cardInputStream = new CardInputStream(sock.getInputStream());
                List<Card> cards = new ArrayList<>();

                //read the cards
                while (true){
                    Card card = cardInputStream.readCard();
                    if(card == null){
                        break;
                    }
                    cards.add(card);
                }
                

                cardInputStream.close();

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
        }
        catch(IOException e){
            System.out.println("Error closing connection: " + e.getMessage());
        }

    }


}
