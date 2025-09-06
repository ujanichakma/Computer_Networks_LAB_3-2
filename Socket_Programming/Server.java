import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sever {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("Server is running on the port 1000\n");

        Socket socket2 = serverSocket.accept();
        DataOutputStream dos2 = new DataOutputStream(socket2.getOutputStream());
        DataInputStream dis2 = new DataInputStream(socket2.getInputStream());
        while(true){
            String s;
            s = dis2.readUTF();
            System.out.println("The message from client is :"+s);
            if(s.equals("HI")){
               for(int i=0;i<8;i++){
             dos2.writeUTF(s);
               }
            }
            else if(s.equals("HELLO")){
                dos2.writeUTF("Hello Client\n");
            }
            else if(s.equals("DATE")){
                String date = getdate();
                dos2.writeUTF(date);
            }
           else if(s.equals("QUIT")){
                dos2.writeUTF("BYE");
                break;
            }
            else{
                dos2.writeUTF("INVALID");
               
            }
        }
        dos2.close();
        dis2.close();
        socket2.close();
    }
    private static String getdate()throws Exception{
        SimpleDateFormat f = new SimpleDateFormat("HH:MM:SS");
        Date date = new Date();
        return f.format(date);


    }
}
