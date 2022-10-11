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

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
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
import java.awt.event.ActionEvent;

public class rating extends JFrame {
	static String res;
	private JPanel contentPane;
	private JTextField ress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rating frame = new rating();
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
	public rating() {
		res=restaurant1.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 304);
		setTitle("rate restaurant");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 345, 304);
		ImageIcon myimg=new ImageIcon("D:\\blr1.png");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JLabel img1=new JLabel();
		img.add(img1);
		img1.setBounds(180, 187, 70, 70);
		ImageIcon myimg1=new ImageIcon("D:\\tq.png");
		Image im1=myimg1.getImage();
		Image newim1=im1.getScaledInstance(img1.getWidth(), img1.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon ii1=new ImageIcon(newim1);
		img1.setIcon(ii1);
		
		JLabel lblNewLabel = new JLabel("Order Placed!");
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 30));
		lblNewLabel.setBounds(110, 52, 139, 43);
		img.add(lblNewLabel);
		
		JLabel lblRate = new JLabel(" Rate"+"\n"+"Hotel");
		lblRate.setForeground(Color.white);
		lblRate.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblRate.setBounds(10, 105, 63, 34);
		img.add(lblRate);
		
		ress = new JTextField();
		ress.setText(res);
		ress.setFont(new Font("Agency FB", Font.PLAIN, 20));
		ress.setColumns(10);
		ress.setBounds(84, 105, 200, 34);
		img.add(ress);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner.setFont(new Font("Agency FB", Font.PLAIN, 20));
		spinner.setBounds(102, 155, 44, 32);
		img.add(spinner);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double rate,nwrate,endrate=5;
				int per;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select * from restaurant where name='"+res+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(!rs.next())
						throw new Exception();
					rate=rs.getDouble(2);
					per=rs.getInt(3);
					per+=1;
					nwrate=(int)spinner.getValue();
					endrate=(rate+nwrate)/per;
					PreparedStatement pst=conn.prepareStatement("update restaurant set rating="+endrate+",person="+per+" where name='"+res+"'");
					pst.executeUpdate();
					PreparedStatement ps=conn.prepareStatement("delete from ordered");
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Thank You, Happy Eating!");
					dispose();
					restaurant1.main(null);
				}
				catch(Exception e1)
				{
					if(endrate>=6)
					{
						JOptionPane.showMessageDialog(null, "Rate out of 1-5");
					}
					JOptionPane.showMessageDialog(null, "Error, Try again!");
				}
			}
		});
		btnSubmit.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnSubmit.setBounds(176, 155, 80, 32);
		btnSubmit.setOpaque(false);
		btnSubmit.addMouseListener(new MouseAdapter() {
	         Color color = btnSubmit.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnSubmit.getForeground();
	        	 btnSubmit.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnSubmit.setForeground(color);
	         }
	      });
		img.add(btnSubmit);
		
		
	}
}
