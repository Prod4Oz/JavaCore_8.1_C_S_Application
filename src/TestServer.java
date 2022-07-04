import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TestServer {
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(8080);


        log("Server start");

        try {
            while (true) {
                //  Ждем подключения клиента и получаем потоки для дальнейшей работы
                Socket clientSocket = server.accept();// ждем подключения
                handle(clientSocket);
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            server.close();
        }
    }

    private static void handle(Socket clientSocket) {
        log("client connected: " + clientSocket.getRemoteSocketAddress());

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("New connection accepted");

            final String name = in.readLine();

            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}