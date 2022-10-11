import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome frame = new welcome();
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
	public welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("foodie.com");
		JPanel p=new JPanel();
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		p.setLayout(null);
		JLabel img=new JLabel();
		p.add(img);
		img.setBounds(0, 0, 700, 700);
		ImageIcon myimg=new ImageIcon("D:\\home2.jpg");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JLabel plt=new JLabel();
		plt.setBounds(278, 269, 150, 150);
		ImageIcon m1=new ImageIcon("D:\\plt2.png");
		Image m2=m1.getImage();
		Image m3=m2.getScaledInstance(plt.getWidth(), plt.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon ii=new ImageIcon(m3);
		plt.setIcon(ii);
		plt.setOpaque(false);
		img.add(plt);
		
		JLabel img2=new JLabel();
		img.add(img2);
		img2.setBounds(540, 30, 130, 130);
		ImageIcon myimg2=new ImageIcon("D:\\logo.png");
		Image im2=myimg2.getImage();
		Image newim2=im2.getScaledInstance(img2.getWidth(), img2.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon ii2=new ImageIcon(newim2);
		img2.setIcon(ii2);
		
		JLabel lblDelizh = new JLabel("FOODIEweb");
		lblDelizh.setForeground(new Color(255, 140, 0));
		lblDelizh.setFont(new Font("Agency FB", Font.BOLD, 50));
		lblDelizh.setBounds(264, 145, 350, 50);
		img.add(lblDelizh);
		
		JLabel lblGoGrabYour = new JLabel("Grab Your Taste!");
		lblGoGrabYour.setForeground(new Color(255, 140, 0));
		lblGoGrabYour.setFont(new Font("Agency FB", Font.PLAIN, 30));
		lblGoGrabYour.setBounds(283, 205, 188, 25);
		img.add(lblGoGrabYour);
		
		JButton btnAdmin = new JButton("Register");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				signup.main(null);
			}
		});
		btnAdmin.setOpaque(false);
		btnAdmin.setBorder(BorderFactory.createLineBorder(new Color(255,140,0), 1, true));
		btnAdmin.setContentAreaFilled(false);
		btnAdmin.setBorderPainted(true);
	    btnAdmin.setFocusPainted(false);
		btnAdmin.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnAdmin.setBounds(186, 300, 92, 34);
		btnAdmin.addMouseListener(new MouseAdapter() {
	         Color color = btnAdmin.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnAdmin.getForeground();
	             btnAdmin.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             btnAdmin.setForeground(color);
	         }
	      });
		img.add(btnAdmin);
		
		JButton btnUser = new JButton("Login");
		btnUser.setBorder(BorderFactory.createLineBorder(new Color(255,140,0), 1, true));
		btnUser.setOpaque(false);
		btnUser.setContentAreaFilled(false);
		btnUser.setBorderPainted(true);
		btnUser.setFocusPainted(false);
		btnUser.addMouseListener(new MouseAdapter() {
	         Color color = btnUser.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnUser.getForeground();
	             btnUser.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             btnUser.setForeground(color);
	         }
	      });
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					PreparedStatement ps1=conn.prepareStatement("delete from log");
					ps1.executeUpdate();
					dispose();
					login.main(null);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnUser.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnUser.setBounds(425, 300, 92, 34);
		img.add(btnUser);
		
		JLabel lblNewLabel = new JLabel("Nothing Brings People Together Like...");
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 462, 426, 46);
		img.add(lblNewLabel);
		
		JLabel lblGoodFood = new JLabel("Good Food!");
		lblGoodFood.setForeground(new Color(255, 140, 0));
		lblGoodFood.setFont(new Font("Agency FB", Font.PLAIN, 30));
		lblGoodFood.setBounds(300, 500, 200, 30);
		img.add(lblGoodFood);
	}

}
