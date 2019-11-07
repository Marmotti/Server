import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int nreq = 1;
        try {
            ServerSocket socket = new ServerSocket(6666);
            for(;;){
                Socket newSocket = socket.accept();
                System.out.println("Creating thread...");
                Thread thread = new ThreadHandler(newSocket, nreq);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("IO exception " + e);
            e.printStackTrace();
        }
        finally {
            System.out.println("End!");
        }
    }
}
