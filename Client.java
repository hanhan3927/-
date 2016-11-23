import java.io.*;
import java.net.*;
import java.util.*;


public class Client extends Thread{
	private Scanner in=new Scanner(System.in);
	private String msg;
	Socket socket;
	Client(String str,Socket socket){msg=str;this.socket=socket;}
	public void run()
	{
		try
		{
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(msg+ "\r\n");

		}

		catch(IOException e)
		{e.printStackTrace();}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
				Socket socket=new Socket("127.0.0.1",6666);
				int flag=0;
				while(true)
				{
					Scanner input=new Scanner(System.in,"UTF-8");
					if(flag==0)
					{
						System.out.println("Please input room number:");
						int room;
						room=input.nextInt();
						Client user=new Client(("roomnumber"+room),socket);
						user.start();
						flag=1;
					}
					
					String msg=input.nextLine();
					if(msg.equals("")){System.out.println("Bye~");break;}
					Client user=new Client(msg,socket);
					user.start();

				}
				System.out.println("disconnect!");
		   }
		catch(IOException e)
		{e.printStackTrace();}

	}

}
