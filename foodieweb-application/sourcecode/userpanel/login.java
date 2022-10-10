import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	
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
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("log in");
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
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setBounds(245, 210, 75, 30);
		img.add(lblNewLabel);
		
		username = new JTextField();
		username.setFont(new Font("Agency FB", Font.PLAIN, 20));
		username.setBounds(245, 245, 248, 34);
		img.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(245, 285, 86, 30);
		img.add(lblPassword);

		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(245, 320, 248, 34);
		img.add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
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
					String sql="Select * from useracc where user_name='"+username.getText()+"'and password='"+password.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(rs.next())
					{
						/*PreparedStatement ps=conn.prepareStatement("insert into log values(?,?,?)");
						ps.setString(1,rs.getString(1));
						ps.setString(2,username.getText());
						ps.setString(3,password.getText());
						ps.executeUpdate();*/
						dispose();
						restaurant1.main(null);
					}
					else
					{
						String sql1="Select * from useracc where user_name='"+username.getText()+"'";
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
					{
						username.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null, "Username Doesn't Exists");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Fields");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
				}
			}
		});
		btnLogin.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnLogin.setBounds(400, 420, 92, 34);
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.addMouseListener(new MouseAdapter() {
	         Color color = btnLogin.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnLogin.getForeground();
	             btnLogin.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             btnLogin.setForeground(color);
	         }
	      });
		img.add(btnLogin);
		
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
		checkBox.setBounds(245, 365, 123, 21);
		checkBox.setOpaque(false);
		img.add(checkBox);
		
		JLabel label = new JLabel("FOODIEweb - Sign in");
		label.setForeground(new Color(255, 140, 0));
		label.setFont(new Font("Agency FB", Font.BOLD, 30));
		label.setBounds(254, 145, 350, 50);
		img.add(label);
		
		JButton button = new JButton("Back");
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
		button.setBounds(245, 420, 92, 34);
		img.add(button);
	}

}
