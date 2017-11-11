import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.util.ArrayList;
import java.io.*;
public class Server extends WindowAdapter{
Frame f;
List l;
static Socket s[];
ServerSocket ss;
ArrayList a;
 List msgs;
	public Server() {
		a=new ArrayList();
		msgs=new List();
		msgs.setBackground(Color.BLACK);
		msgs.setForeground(Color.LIGHT_GRAY);
		f=new Frame("Chat Server");
		l=new List();
		l.setBackground(Color.BLACK);
		l.setForeground(Color.LIGHT_GRAY);
		f.add(l,BorderLayout.WEST);
		f.add(msgs);
		f.setVisible(true);
		f.setSize(400,400);
		f.addWindowListener(this);
		int k=0;
		try
		{
		s=new Socket[50];
		ss=new ServerSocket(2030);
		while(true)
		{
			s[k]=ss.accept();
			ObjectInputStream ois=new ObjectInputStream(s[k].getInputStream());
			String uname=ois.readObject().toString().replaceAll("[0-9]", " ").trim()+":"+s[k].getPort();
			l.add(uname);
			System.out.println(l);
			System.out.println("hello8");
			a.add(uname.replaceAll("[0-9]","").trim());
	        sendAll(s, k, a);
	        MyThread m=new MyThread(uname,s[k],msgs);
	        m.start();
	        k++;
	        System.out.println("hello9");
			
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
void sendAll(Socket s[],int k,ArrayList a)
{
	try
	{
		for(int i=0;i<=k;i++)
		{
		ObjectOutputStream oos=new ObjectOutputStream(s[i].getOutputStream());
		oos.writeObject(a);
		System.out.println("hello10");
		}
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
}
	public static void main(String[] args) {
		Server s=new Server();

	}
public void windowClosing(WindowEvent e)
{
	System.exit(0);
}
}
