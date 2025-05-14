import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("Servidor escuchando en puerto 6789...");
        while (true) {
            Socket clientSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

            String clientSentence = inFromClient.readLine();
            if (clientSentence.equals("EXIT")) {
                outToClient.writeBytes("Adiós\n");
                clientSocket.close();
                break;
            }

            StringBuilder bin = new StringBuilder();
            for (char c : clientSentence.toCharArray()) {
                bin.append(Integer.toBinaryString(c)).append(" ");
            }

            StringBuilder oct = new StringBuilder();
            for (char c : clientSentence.toCharArray()) {
                oct.append(Integer.toOctalString(c)).append(" ");
            }

            int letterCount = clientSentence.replaceAll("\\s+", "").length();

            String[] words = clientSentence.split("\\s+");
            Arrays.sort(words);
            String sorted = String.join(" ", words);

            String result = "Binario: " + bin.toString().trim() + "\n"
                    + "Octal: " + oct.toString().trim() + "\n"
                    + "Número de letras: " + letterCount + "\n"
                    + "Orden alfa.: " + sorted + "\n";

            outToClient.writeBytes(result);
            clientSocket.close();
        }
        welcomeSocket.close();
    }
}