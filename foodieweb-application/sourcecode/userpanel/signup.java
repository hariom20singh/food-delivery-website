
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField confirmpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("Sign up");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 700, 700);
		ImageIcon myimg=new ImageIcon("D:\\home2.jpg");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JLabel lblDelizh = new JLabel("FOODIEweb - Sign Up");
		lblDelizh.setForeground(new Color(255, 145, 0));
		lblDelizh.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizh.setBounds(230, 155, 350, 50);
		img.add(lblDelizh);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(80, 220, 45, 23);
		img.add(lblNewLabel);
		
		name = new JTextField();
		name.setFont(new Font("Agency FB", Font.PLAIN, 20));
		name.setBounds(80, 255, 248, 34);
		img.add(name);
		name.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblUsername.setBounds(340, 220, 79, 25);
		img.add(lblUsername);
		
		username = new JTextField();
		username.setFont(new Font("Agency FB", Font.PLAIN, 20));
		username.setColumns(10);
		username.setBounds(340, 255, 248, 34);
		img.add(username);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(80, 305, 79, 23);
		img.add(lblPassword);
		
		password = new JPasswordField();
		password.setEchoChar('*');
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setBounds(80, 340, 248, 34);
		img.add(password);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblConfirmPassword.setBounds(340, 305, 141, 23);
		img.add(lblConfirmPassword);
		
		confirmpassword = new JPasswordField();
		confirmpassword.setEchoChar('*');
		confirmpassword.setFont(new Font("Agency FB", Font.PLAIN, 20));
		confirmpassword.setBounds(340, 340, 248, 34);
		img.add(confirmpassword);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					password.setEchoChar((char)0);
					confirmpassword.setEchoChar((char)0);
				}
				else
				{
					password.setEchoChar('*');
					confirmpassword.setEchoChar('*');
				}
					
			}
		});
		chckbxNewCheckBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		chckbxNewCheckBox.setBounds(80, 385, 153, 21);
		chckbxNewCheckBox.setOpaque(false);
		img.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Signup");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try {
					if(name.getText().equals("")||username.getText().equals("")||password.getText().equals("")||confirmpassword.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					PreparedStatement ps=conn.prepareStatement("insert into useracc values(?,?,?)");
					if(!password.getText().equals(confirmpassword.getText()))
					{
						error=1;
						throw new Exception();
					}
					ps.setString(1,name.getText());
					ps.setString(2,username.getText());
					ps.setString(3,password.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Signup Successfull");
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						confirmpassword.setText("");
						username.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Fields");
					else
						JOptionPane.showMessageDialog(null, "Username Already Taken");
				}
			}
		});
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				welcome.main(null);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
	         Color color = btnNewButton.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnNewButton.getForeground();
	        	 btnNewButton.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	        	 btnNewButton.setForeground(color);
	         }
	      });
		btnNewButton.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnNewButton.setBounds(475, 420, 92, 34);
		img.add(btnNewButton);
		
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				welcome.main(null);
			}
		});
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				welcome.main(null);
			}
		});
		button.addMouseListener(new MouseAdapter() {
	         Color color = button.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = button.getForeground();
	        	 button.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	        	 button.setForeground(color);
	         }
	      });
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(350, 420, 92, 34);
		img.add(button);
		
		JLabel cp=new JLabel("Register and get cheeky 50% off*");
		cp.setForeground(new Color(255, 140, 0));
		cp.setFont(new Font("Myriad Pro", Font.ITALIC, 20));
		cp.setBounds(205, 472, 480, 70);
		img.add(cp);
	}
}
