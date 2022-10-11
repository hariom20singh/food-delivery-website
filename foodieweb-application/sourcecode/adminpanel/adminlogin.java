package adminpanel;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class adminlogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin frame = new adminlogin();
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
	public adminlogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 304);
		setTitle("AdminPanel");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblDelizhAdmin = new JLabel("FOODIEweb");
		lblDelizhAdmin.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhAdmin.setBounds(82, 10, 154, 25);
		contentPane.add(lblDelizhAdmin);
		
		JLabel lblUsernmae = new JLabel("AdminName");
		lblUsernmae.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblUsernmae.setBounds(10, 45, 154, 25);
		contentPane.add(lblUsernmae);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(10, 124, 154, 25);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setFont(new Font("Agency FB", Font.PLAIN, 20));
		username.setBounds(10, 80, 248, 34);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(10, 159, 248, 34);
		contentPane.add(password);
		
		JCheckBox checkBox = new JCheckBox("Show password");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected())
					password.setEchoChar((char)0);
				else
					password.setEchoChar('*');
			}
		});
		checkBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		checkBox.setBounds(10, 199, 123, 21);
		contentPane.add(checkBox);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(username.getText().equals("")||password.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select * from adminacc where user_name='"+username.getText()+"'and password='"+password.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
					{
						dispose();
						adminedit.main(null);
					}
					else
					{
						String sql1="Select * from adminacc where user_name='"+username.getText()+"'";
						ResultSet rs1=stmt.executeQuery(sql1);
						if(!rs1.next())
						{
							error=1;
							throw new Exception();
						}
						else
							throw new Exception();
					}
				}
				catch(Exception e1)
				{
					if(error!=2)
						password.setText("");
					if(error==1)
					{
						username.setText("");
						JOptionPane.showMessageDialog(null,"Admin Doesn't Exists");
					}
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
				}
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(166, 229, 92, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_1.setBounds(72, 229, 92, 34);
		contentPane.add(button_1);
	}

}
