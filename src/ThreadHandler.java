import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadHandler extends Thread{

    private Socket newSocket;
    private int n;

    ThreadHandler(Socket newSocket, int n) {
        this.newSocket = newSocket;
        this.n = n;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(newSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));
            output.println("Hello :: enter QUIT to exit \n");
            boolean moreData = true;
            String newLine;

            while (moreData){
                newLine = input.readLine();
                System.out.println("Message '" + newLine +"' echoed back to client.");
                if (newLine == null){
                    System.out.println("line == null lol");
                    moreData = false;
                }
                else {
                    output.println("From server: " + newLine + ". \n");
                    if (newLine.trim().equals("QUIT"))
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
