import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        // Se crea un servidor en el puerto 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Servidor escuchando en puerto 6789...");

        while (true) {
            // Espera y acepta una conexión entrante del cliente
            Socket clientSocket = welcomeSocket.accept();

            
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

            
            String clientSentence = inFromClient.readLine();

            
            if (clientSentence.equals("EXIT")) {
                outToClient.writeBytes("Adiós\n");
                clientSocket.close();
                break;
            }

            // Se preparan las representaciones binaria, octal y hexadecimal de cada carácter
            StringBuilder bin = new StringBuilder();
            StringBuilder oct = new StringBuilder();
            StringBuilder hex = new StringBuilder();
            for (char c : clientSentence.toCharArray()) {
                bin.append(Integer.toBinaryString(c)).append(" ");
                oct.append(Integer.toOctalString(c)).append(" ");
                hex.append(Integer.toHexString(c)).append(" ");
            }

            // Se cuentan las letras (ignorando espacios)
            int letterCount = clientSentence.replaceAll("\\s+", "").length();

            // Se ordenan alfabéticamente los caracteres (sin espacios)
            char[] chars = clientSentence.replaceAll("\\s+", "").toCharArray();
            Arrays.sort(chars);
            String sortedLetters = new String(chars);

            
            String result = ""
                + "Binario: " + bin.toString().trim() + "\n"
                + "Octal: " + oct.toString().trim() + "\n"
                + "Hex: " + hex.toString().trim() + "\n"
                + "Número de letras: " + letterCount + "\n"
                + "Orden alfa.: " + sortedLetters + "\n";

          
            outToClient.writeBytes(result);
            clientSocket.close();
        }

        
        welcomeSocket.close();
    }
}
