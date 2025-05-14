import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

        // Se establece conexión con el servidor en el puerto 6789
        Socket socketConnection = new Socket("DESKTOP-HLD5CDS", 6789);

        // Preparar flujos de salida al servidor y entrada desde el servidor
        DataOutputStream outputWriter = new DataOutputStream(socketConnection.getOutputStream());
        BufferedReader serverResponseReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));

        String line;
        System.out.println("Ingrese texto (EXIT para terminar):");

        // Bucle principal para leer y enviar texto al servidor
        while ((line = userInputReader.readLine()) != null) {
            
            outputWriter.writeBytes(line + "\n");

            
            if (line.equals("EXIT")) {
                break;
            }

            
            String responseLine;
            while ((responseLine = serverResponseReader.readLine()) != null) {
                System.out.println(responseLine);
            }
        }

        
        socketConnection.close();
        userInputReader.close();
    }
}
