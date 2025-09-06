import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class mail1 {
    private static DataOutputStream dos;
    private static BufferedReader br;
    public static void main(String[] args) throws Exception{
        String user = "";//sender main
        String pass = "";//app generated password lagbe
        String username =new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String(Base64.getEncoder().encode(pass.getBytes()));
        SSLSocket s = (SSLSocket)SSLSocketFactory.getDefault().createSocket("smtp.gmail.com", 465);
        dos = new DataOutputStream(s.getOutputStream());
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        send("EHLO smtp.gmail.com\r\n");
        for(int i=0;i<9;i++){
            System.out.println("SERVER: "+br.readLine());
        }

        send("AUTH LOGIN\r\n");
        System.out.println("SERVER: "+br.readLine());

        send(username +"\r\n");
         System.out.println("SERVER: "+br.readLine());
        
        send(password +"\r\n");
         System.out.println("SERVER: "+br.readLine());

         send("MAIL FROM: <s2110676134@ru.ac.bd>\r\n");
          System.out.println("SERVER: "+br.readLine());
        
        send("RCPT TO: <bdmachinelearning.210@gmail.com>\r\n");
         System.out.println("SERVER: "+br.readLine());
        send("DATA\r\n");
        

         String ip_address = getIpAddress();
        //  String time = getTime();
         send("From: sender mail ekhane \r\n");
         send("To: receiver mail ekhane\r\n");
         send("Subject: Exam Mail 2110676134\r\n");
         send("\r\n");
         send("Hi ");

         send(".\r\n");

          System.out.println("SERVER: "+br.readLine());
        send("Quit\r\n");
     

    } 

    public static void send(String s)throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: "+s);
    }

    public static String getIpAddress(){
        try{
            InetAddress in = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(in);
            byte [] mac = ni.getHardwareAddress();
            if(mac==null)return "Error ip address";
            StringBuilder s = new StringBuilder();
            for(byte b:mac){
                s.append(String.format("%02X:", b));
            }
            return s.substring(0,s.length()-1);
        }
        catch(Exception e){
            return("error");
        }

    }

    
}
