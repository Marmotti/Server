import java.io.*;
import java.net.Socket;

public class ThreadHandler extends Thread{

    private Socket newSocket;
    private int n;
    ObjectOutputStream out;
    ObjectInputStream in;

    ThreadHandler(Socket newSocket, int n) {
        this.newSocket = newSocket;
        this.n = n;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(newSocket.getOutputStream(), true);
            output.println("Hello :: enter QUIT to exit \n");
            boolean moreData = true;
            String newLine;

            while (moreData){
                in = new ObjectInputStream(newSocket.getInputStream());
                System.out.println("Message received.");
                if (in == null){
                    System.out.println("line == null lol");
                    moreData = false;
                }
            }

            newSocket.close();
            System.out.println("Disconnected from client number " + n);
        } catch (IOException e) {
            System.out.println("IO exception " + e);
            e.printStackTrace();
        }
    }
}
