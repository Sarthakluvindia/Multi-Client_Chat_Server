import java.io.*;
import java.awt.*;
import java.net.*;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.SwingConstants;

import org.omg.PortableInterceptor.INACTIVE;
public class Client extends WindowAdapter implements Runnable, ActionListener{
Frame f;
TextField t1,t2;
Button b1,b2;
List l;
List clients;
Socket s;
	public Client() 
	{
	f=new Frame("Client");
	t1=new TextField(20);
	t2=new TextField(20);
	t1.setBackground(Color.BLACK);
	t1.setForeground(Color.LIGHT_GRAY);
	t1.setText(Login.chat_name);
	t2.setBackground(Color.BLACK);
	t2.setForeground(Color.LIGHT_GRAY);
	l=new List();
	l.setBackground(Color.BLACK);
	l.setForeground(Color.LIGHT_GRAY);
	clients=new List();
	clients.setBackground(Color.BLACK);
	clients.setForeground(Color.LIGHT_GRAY);
	b1=new Button("Connect");
	b1.setFont(new Font("Courier New", Font.PLAIN, 11));
	b1.setBackground(new Color(50, 205, 50));
	b1.setForeground(Color.BLACK);
	b2=new Button("Send");
	b1.addActionListener(this);
	b2.addActionListener(this);
	b2.setFont(new Font("Courier New", Font.PLAIN, 11));
	b2.setBackground(new Color(50, 205, 50));
	b2.setForeground(Color.BLACK);
	Panel p1=new Panel();
	p1.setBackground(Color.BLACK);
	Panel p2=new Panel();
	p2.setBackground(Color.BLACK);
	p1.add(t1);
	p1.add(b1);
	f.addWindowListener(this);
	p2.add(t2);
	p2.add(b2);
	f.add(p1,BorderLayout.NORTH);
	f.add(p2,BorderLayout.SOUTH);
	f.add(l);
	f.add(clients,BorderLayout.WEST);
	f.setVisible(true);
	f.setSize(300,300);
	}

	void readRead()
	{
		while(true)
		{
		try
		{
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Object o=ois.readObject();
			System.out.println("hello");
			if(o instanceof ArrayList)
			{
				clients.clear();
				ArrayList l=(ArrayList)o;
				System.out.println("hello1");
				for(Object o1:l)
				{
					clients.add(o1.toString().replaceAll("[0-9]","").trim());
				}
			}
			else
			{
				System.out.println("hello2");
				l.add(o.toString());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		}		
	}
	public static void main(String[] args) 
	{
		Client c=new Client();
	}
	public void run()
	{
		readRead();
		System.out.println("hello3");
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		try
		{
			s=new Socket("localhost",2030);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(t1.getText());
			System.out.println("hello4");
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			ArrayList l=(ArrayList)ois.readObject();
			for(Object o:l)
			{
				clients.add(o.toString().replaceAll("[0-9]","").trim());
			}
			Thread t=new Thread(this);
			t.start();
			System.out.println("hello5");
		}
		catch(Exception e1)
		{
			System.out.println("Error"+e1.getMessage());
		}
		}
		else
		{
			try
			{
			System.out.println("Sent"+t2.getText());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(clients.getSelectedIndex()+"-->"+t2.getText());
				System.out.println("hello6");
			}
			catch(Exception e1)
			{
				System.out.println("Error"+e1.getMessage());
			}
		}
		
	}
public void windowClosing(WindowEvent e)
{
	System.exit(0);
}
}
