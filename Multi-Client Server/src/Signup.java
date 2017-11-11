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

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.DriverManager;
import java.util.Arrays;

import javax.swing.Icon;


public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JLabel lblEnterUsername;
	private JLabel lblEnterPassword;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblSaconnectify;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Signup() {
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
		
		JLabel lblEnterName = new JLabel("Enter Name: ");
		lblEnterName.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblEnterName.setForeground(new Color(50, 205, 50));
		lblEnterName.setBounds(73, 59, 93, 24);
		contentPane.add(lblEnterName);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBackground(new Color(0, 0, 0));
		textField.setBounds(73, 81, 160, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAge = new JLabel("Enter Age: ");
		lblEnterAge.setForeground(new Color(50, 205, 50));
		lblEnterAge.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblEnterAge.setBounds(73, 118, 93, 24);
		contentPane.add(lblEnterAge);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setBackground(new Color(0, 0, 0));
		textField_1.setBounds(73, 139, 160, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSignup = new JButton("Signup");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/encryptedchatserver?user=root&password=Sarthak@124");
					String str="insert into signup values(?,?,?,?)";
					java.sql.PreparedStatement ps=cn.prepareStatement(str);
					ps.setString(1,textField.getText());	
					ps.setString(2,textField_1.getText());
					ps.setString(3,txtEnterUsername.getText());
					ps.setString(4,Arrays.toString(passwordField.getPassword()));
					ps.executeUpdate();
					cn.close();
					Login l=new Login();
					l.setVisible(true);
					textField.setText(" ");
					textField_1.setText(" ");
					txtEnterUsername.setText("");
					passwordField.setText("");
				}
				catch(Exception f)
				{
					JOptionPane.showMessageDialog(null, f.getMessage());
				}
			}
		});
		btnSignup.setBackground(new Color(50, 205, 50));
		btnSignup.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnSignup.setBounds(100, 330, 100, 24);
		contentPane.add(btnSignup);
		
		lblSaconnectify = new JLabel("Hacker's Haven");
		lblSaconnectify.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaconnectify.setForeground(new Color(50, 205, 50));
		lblSaconnectify.setFont(new Font("AR DESTINE", Font.PLAIN, 20));
		lblSaconnectify.setBounds(47, 11, 172, 37);
		contentPane.add(lblSaconnectify);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 11, 55, 42);
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
