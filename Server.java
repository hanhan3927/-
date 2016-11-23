import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;

class check extends Thread 
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String st = sc.nextLine().toLowerCase();
            if (st.equals("exit")){System.exit(0);}
            }

    }
}

class talk extends Thread{
    Socket socket=null;
    String IP;
    int count;
    int acc;
    int roomnumber=0;
    int flag=0;
    int roomnum()
    {
    	return roomnumber;
    }
    talk(Socket socket,int count,int acc)
    {
    	this.acc=acc;
        this.socket=socket;
        this.count=count;
    }
    public void run()
    {
	        try{
	        	Scanner in= new Scanner(socket.getInputStream(),"UTF-8");
	        	while(in.hasNext())
	        	{
	        		IP="IP:"+socket.getInetAddress();
		            String msg=in.nextLine().trim();
	        		if(flag==0)
	        		{
	        			for(int i=1;i<=10;i++)
	        			{
		        			if(msg.indexOf(("roomnumber"+i))>=0)
		        			{
		        				roomnumber=i;
		        			}
	        			}
	        			
	        		}
	        		
		            String str = IP+"["+count+"]"+" "+msg;
		            System.out.println(new String(str.getBytes("UTF-8"),"BIG5"));
		            if(msg==null){break;}
		        }
		        System.out.println("disconnect!");
	        }
	        catch(IOException e){

	            e.printStackTrace();
	        }
    }
}
public class Server extends Thread{
    private int port;
    static int count=0;
    int acc=0;
    int flag=0;
    Server(int port){this.port=port;}
    public void run()
    {
        try{
        	Scanner in=new Scanner(System.in);
            ServerSocket ssocket= new ServerSocket(port,100);
            Socket socket=new Socket;
            
            ArrayList<ArrayList<Socket>> roomsave=new ArrayList<ArrayList<Socket>>(10);
            
            while(true)
            {
            	
                System.out.println("count:"+count);     
                socket=ssocket.accept();//wait when the Client is connect         
                acc++;
                talk t1=new talk(socket,count,acc);
                t1.start();
                if(flag==0)
                {
	                while(t1.roomnum()!=0)
	                {
	                	int f=t1.roomnum();
	                    roomsave.add
	                	flag=1;
	                }
                }
                count++;
            }

        }
        catch(IOException e)
        {

            System.out.println("count:");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    check a1=new check();
    a1.start();
    Server a2=new Server(6666);
    a2.start();
    }

}
