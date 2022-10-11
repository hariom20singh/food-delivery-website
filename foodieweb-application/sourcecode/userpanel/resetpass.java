import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JCheckBox;
public class resetpass extends JFrame {

	private JPanel contentPane;
	private JButton btnConfirm;
	private JLabel lblNewLabel;
	private JPasswordField passw;
	private JLabel lblEnterNewPassword;
	private JPasswordField password;
	private JLabel lblConfirmNewPassword;
	private JPasswordField confirmpassword;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resetpass frame = new resetpass();
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
	public resetpass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 304);
		setTitle("account manager");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 563, 304);
		ImageIcon myimg=new ImageIcon("D:\\blr1.png");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		btnConfirm = new JButton("Reset");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try {
					ArrayList<String> pass=new ArrayList<String>();
					ArrayList<String> user=new ArrayList<String>();
					if(passw.getText().equals("")||password.getText().equals("")||confirmpassword.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql=" select * from useracc";
					ResultSet rst=stmt.executeQuery(sql);
					while(rst.next())
					{
						user.add(rst.getString(1));
						pass.add(rst.getString(2));
					}
					if(!pass.get(0).equals(passw.getText()))
						throw new Exception();
					if(!password.getText().equals(confirmpassword.getText()))
					{
						error=1;
						throw new Exception();
					}
					PreparedStatement ps=conn.prepareStatement("update useracc set password='"+password.getText()+"' where user_name='"+user.get(0)+"'");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Reset Successfull");
					dispose();
					login.main(null);
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						passw.setText("");
						confirmpassword.setText("");
						password.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null, "Passwords Doesn't Match");
					else if(error==2)
						JOptionPane.showMessageDialog(null, "Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
					dispose();
					resetpass.main(null);
				}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(447, 203, 92, 34);
		btnConfirm.setOpaque(false);
		btnConfirm.addMouseListener(new MouseAdapter() {
	         Color color = btnConfirm.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnConfirm.getForeground();
	        	 btnConfirm.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnConfirm.setForeground(color);
	         }
	      });
		img.add(btnConfirm);
		
		lblNewLabel = new JLabel("Current Password");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(10, 47, 178, 34);
		img.add(lblNewLabel);
		
		passw = new JPasswordField();
		passw.setFont(new Font("Agency FB", Font.PLAIN, 20));
		passw.setEchoChar('*');
		passw.setBounds(10, 84, 248, 34);
		img.add(passw);
		
		lblEnterNewPassword = new JLabel("New Password");
		lblEnterNewPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblEnterNewPassword.setForeground(Color.white);
		lblEnterNewPassword.setBounds(291, 47, 178, 34);
		img.add(lblEnterNewPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(291, 84, 248, 34);
		img.add(password);
		
		lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblConfirmNewPassword.setForeground(Color.white);
		lblConfirmNewPassword.setBounds(10, 166, 188, 34);
		img.add(lblConfirmNewPassword);
		
		confirmpassword = new JPasswordField();
		confirmpassword.setFont(new Font("Agency FB", Font.PLAIN, 20));
		confirmpassword.setEchoChar('*');
		confirmpassword.setBounds(10, 203, 248, 34);
		img.add(confirmpassword);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(345, 203, 92, 34);
		btnBack.setOpaque(false);
		btnBack.addMouseListener(new MouseAdapter() {
	         Color color = btnBack.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnBack.getForeground();
	        	 btnBack.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnBack.setForeground(color);
	         }
	      });
		img.add(btnBack);
		
		JCheckBox checkBox = new JCheckBox("Show password");
		checkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox.isSelected())
				{
					password.setEchoChar((char)0);
					passw.setEchoChar((char)0);
					confirmpassword.setEchoChar((char)0);
				}
				else
				{
					password.setEchoChar('*');
					passw.setEchoChar('*');
					confirmpassword.setEchoChar('*');
				}
			}
		});
		checkBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		checkBox.setBounds(291, 124, 123, 21);
		checkBox.setOpaque(false);
		checkBox.setForeground(Color.white);
		img.add(checkBox);
	}
}
