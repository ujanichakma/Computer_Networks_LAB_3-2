import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Az {

    private static DataOutputStream dos;
    private static BufferedReader br;
    public static void main(String[] args) throws Exception{
        String user="mdalaminbabu.210@gmail.com";
        String pass="kfwp myvv rqhn ipmx";
        String username = new String(Base64.getEncoder().encode(user.getBytes()));
        String password = new String (Base64.getEncoder().encode(pass.getBytes()));
        SSLSocket s = (SSLSocket)SSLSocketFactory.getDefault().createSocket("smtp.gmail.com",465 );
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
        send("MAIL FROM: <mdalaminbabu.210@gmail.com>\r\n");
         System.out.println("SERVER: "+br.readLine());
        send("RCPT TO: <bdmachinelearning.210@gmail.com>\r\n");
         System.out.println("SERVER: "+br.readLine());
        send("DATA\r\n");
        String mac = getMacAddress();
        send("FROM:mdalaminbabu.210@gmail.com\r\n");
        send("TO:bdmachinelearning.210@gmail.com\r\n");
        send("Subject: Computer Networks "+ LocalDateTime.now());
        send("MAC ADDRESS : "+mac+"\r\n");
        send("HI this is Al-Amin here\r\n");
        send(".\r\n");
         System.out.println("SERVER: "+br.readLine());
         send("Quit\r\n");

    }


    public static void send(String s)throws Exception{
        dos.writeBytes(s);
        Thread.sleep(1000);
        System.out.println("CLIENT: " +s);
    
}

public static String getMacAddress(){
    try{
    InetAddress ip = InetAddress.getLocalHost();
    NetworkInterface ni = NetworkInterface.getByInetAddress(ip);
    byte[]mac = ni.getHardwareAddress();
    if(mac==null)return "Mac Address Not Found";
    StringBuilder sb = new StringBuilder();
    for(byte b:mac){
        sb.append(String.format("%02x-",b));
    }
    return sb.substring(0,sb.length()-1);
}
catch(Exception e){
    return "Error";
}
}
}
