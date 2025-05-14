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
            StringBuilder oct = new StringBuilder();
            StringBuilder hex = new StringBuilder();
            for (char c : clientSentence.toCharArray()) {
                bin.append(Integer.toBinaryString(c)).append(" ");
                oct.append(Integer.toOctalString(c)).append(" ");
                hex.append(Integer.toHexString(c)).append(" ");
            }

            int letterCount = clientSentence.replaceAll("\\s+", "").length();

            char[] chars = clientSentence.replaceAll("\\s+", "").toCharArray();
            Arrays.sort(chars);
            String sortedLetters = new String(chars);

            String result = ""
                + "Binario: " + bin.toString().trim() + "\n"
                + "Octal: "    + oct.toString().trim() + "\n"
                + "Hex: "      + hex.toString().trim() + "\n"
                + "Número de letras: " + letterCount + "\n"
                + "Orden alfa.: " + sortedLetters + "\n";

            outToClient.writeBytes(result);
            clientSocket.close();
        }
        welcomeSocket.close();
    }
}