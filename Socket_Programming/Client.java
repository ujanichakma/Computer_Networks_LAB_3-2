import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("Localhost",7000);
        System.out.println("The client Running in the port number 1000");
        

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream  dis = new DataInputStream(socket.getInputStream());
       
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Please type a message to send to the server\n");
            String message = scanner.nextLine();
            dos.writeUTF(message);
            String response ;
            response = dis.readUTF();
            if(Response.equals("HI")){
                String reply;
               while((reply=dis.readUTF())!=null)
                    System.out.println(reply+"\n");
                
            }
            else{
            System.out.println(Response);
            
            if(Response=="BYE")
            {
                break;
            }
        }
        }
    
     dos.close();
        dis.close();
        socket.close();
    }
}
