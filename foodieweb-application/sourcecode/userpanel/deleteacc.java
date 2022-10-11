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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class deleteacc extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					deleteacc frame = new deleteacc();
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
	public deleteacc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 304);
		setTitle("account manager");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 284, 304);
		ImageIcon myimg=new ImageIcon("D:\\blr1.png");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JLabel lblNewLabel = new JLabel("Click Confrim To Delete Your Account");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 133, 226, 35);
		img.add(lblNewLabel);
		
		JLabel lblPermanentlythisAction = new JLabel("Permanently. (This Action Cannot Be");
		lblPermanentlythisAction.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPermanentlythisAction.setForeground(Color.white);
		lblPermanentlythisAction.setBounds(10, 156, 222, 35);
		img.add(lblPermanentlythisAction);
		
		JLabel lblUndone = new JLabel("Undone)");
		lblUndone.setForeground(Color.white);
		lblUndone.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblUndone.setBounds(10, 178, 222, 35);
		img.add(lblUndone);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(64, 223, 92, 34);
		button.setOpaque(false);
		button.addMouseListener(new MouseAdapter() {
	         Color color = button.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = button.getForeground();
	        	 button.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             button.setForeground(color);
	         }
	      });
		img.add(button);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					ArrayList<String> pass=new ArrayList<String>();
					ArrayList<String> user=new ArrayList<String>();
					if(password.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select user_name,password from log";
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next())
					{
						user.add(rs.getString(1));
						pass.add(rs.getString(2));
					}
					if(!pass.get(0).equals(password.getText()))
						throw new Exception();
					PreparedStatement ps=conn.prepareStatement("delete from useracc where user_name='"+user.get(0)+"'");
					ps.executeUpdate();
					PreparedStatement ps1=conn.prepareStatement("delete from log");
					ps1.executeUpdate();
					dispose();
					welcome.main(null);
					JOptionPane.showMessageDialog(null,"Account Deleted");
				}
				catch(Exception e1)
				{	
					if(error!=2)
						password.setText("");
					if(error==2)
						JOptionPane.showMessageDialog(null,"Enter password");
					else
						JOptionPane.showMessageDialog(null,"Incorrect Password");
				}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(166, 223, 92, 34);
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
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.white);
		lblPassword.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPassword.setBounds(10, 52, 178, 34);
		img.add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Agency FB", Font.PLAIN, 20));
		password.setEchoChar('*');
		password.setBounds(10, 89, 248, 34);
		img.add(password);
	}
}
