
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        BufferedReader userInputReader = new BufferedReader(
                new InputStreamReader(System.in));
        
        Socket socketConnection = new Socket("host", "6789");
        
        
        DataOutputStream outputWriter = new DataOutputStream(
                socketConnection.getInputStream());
        
        BufferedReader serverResponseReader = new BufferedReader(
                new InputStreamReader(socketConnection.getInputStream()));

        String line;
        System.out.println("Ingrese texto para convertir: \n (*EXIT* para terminar):");
        while ((line = userInputReader.readLine()) != null) {
            outputWriter.writeBytes(line + "\n");
            if (line.equals("EXIT")) {
                break; 
            }
            String response = serverResponseReader.readLine; 
            System.out.println("RESPUESTA DEL SERVIDOR:\n" + response);
        }

        socketConnection.close;  
        userInputReader.close();
    }
}