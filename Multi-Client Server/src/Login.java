import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.FocusAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JLabel lblEnterUsername;
	private JLabel lblEnterPassword;
	private JPasswordField passwordField;
	private JLabel lblSaconnectify;
	JPanel panel;
	String chat_username,chat_password,username,password;
	static String chat_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setName("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterUsername.setForeground(Color.LIGHT_GRAY);
		txtEnterUsername.setBounds(73, 201, 160, 31);
		txtEnterUsername.setBackground(Color.BLACK);
		contentPane.add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
		lblEnterUsername = new JLabel("Enter Username: ");
		lblEnterUsername.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblEnterUsername.setForeground(new Color(50, 205, 50));
		lblEnterUsername.setBounds(73, 175, 134, 24);
		contentPane.add(lblEnterUsername);
		
		lblEnterPassword = new JLabel("Enter Password: ");
		lblEnterPassword.setForeground(new Color(50, 205, 50));
		lblEnterPassword.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblEnterPassword.setBounds(73, 243, 134, 24);
		contentPane.add(lblEnterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBackground(Color.BLACK);
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setBounds(73, 267, 160, 31);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
				username=txtEnterUsername.getText();
				password=Arrays.toString(passwordField.getPassword());
				Class.forName("com.mysql.jdbc.Driver");
				Connection cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/encryptedchatserver?user=root&password=Sarthak@124");
				String str="select * from signup where username='"+username+"' and password='"+password+"'";
				java.sql.PreparedStatement ps4=cn.prepareStatement(str);
				ResultSet rs=ps4.executeQuery();
				while(rs.next())
				{
					chat_username=rs.getString("username");
					chat_password=rs.getString("password");
					chat_name=rs.getString("name");
				}
				if(chat_username.equals(username)&&chat_password.equals(password))
				{
					Client c=new Client();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter correct username or password");
				}
				cn.close();
				}
				catch(Exception e)
				{
					System.out.println("hello4");
					JOptionPane.showMessageDialog(null,"Enter correct username or password"+e.getMessage());
				}
			}
		});
		btnLogin.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnLogin.setBackground(new Color(50, 205, 50));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(48, 333, 93, 24);
		contentPane.add(btnLogin);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.setForeground(Color.BLACK);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Signup sg=new Signup();
				sg.setVisible(true);
			}
		});
		btnSignup.setBackground(new Color(50, 205, 50));
		btnSignup.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnSignup.setBounds(164, 333, 93, 24);
		contentPane.add(btnSignup);
		
		lblSaconnectify = new JLabel("Hacker's Haven");
		lblSaconnectify.setFont(new Font("AR DESTINE", Font.PLAIN, 25));
		lblSaconnectify.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaconnectify.setForeground(new Color(50, 205, 50));
		lblSaconnectify.setBounds(83, 69, 200, 50);
		contentPane.add(lblSaconnectify);
		
		panel=new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(38, 77, 55, 42);
		contentPane.add(panel);
		BufferedImage myPicture = null;
		try{
			myPicture = ImageIO.read(new File("D:\\JAVA PROGRAM\\End-to-End-Encryped-Chat-Server\\Webp.net-resizeimage.png"));
		}
		catch(Exception e)
		{
			System.err.println(""+e.getMessage());
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBackground(new Color(255, 255, 255));
		panel.add(picLabel);
		
	}
}
