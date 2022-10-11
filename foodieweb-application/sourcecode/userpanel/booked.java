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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class booked extends JFrame {

	private JPanel contentPane;
	private JTextField payment;
	private JTextField tota;
	private JTextField dedu;
	private JTextField topa;
	static double tot;
	static double dedud;
	static double top;
	static String rest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					booked frame = new booked();
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
	public booked() {
		tot=confirm.cost;
		dedud=confirm.cost-confirm.endcost;
		top=confirm.endcost;
		rest=restaurant1.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		setTitle("Order");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 450, 270);
		ImageIcon myimg=new ImageIcon("D:\\blr1.png");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JLabel lblPayementMethod = new JLabel("Payement Method:");
		lblPayementMethod.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPayementMethod.setBounds(10, 13, 139, 23);
		lblPayementMethod.setForeground(Color.white);
		img.add(lblPayementMethod);
		
		payment = new JTextField();
		payment.setFont(new Font("Agency FB", Font.PLAIN, 20));
		payment.setColumns(10);
		payment.setBounds(151, 13, 248, 34);
		img.add(payment);
		
		JLabel label_1 = new JLabel("Total:");
		label_1.setForeground(Color.white);
		label_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_1.setBounds(10, 57, 92, 23);
		img.add(label_1);
		
		tota = new JTextField();
		tota.setText(Double.toString(tot));
		tota.setFont(new Font("Agency FB", Font.PLAIN, 20));
		tota.setColumns(10);
		tota.setBounds(151, 57, 248, 34);
		img.add(tota);
		
		JLabel lblDeduction = new JLabel("Discount:");
		lblDeduction.setForeground(Color.white);
		lblDeduction.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblDeduction.setBounds(10, 101, 92, 23);
		img.add(lblDeduction);
		
		dedu = new JTextField();
		dedu.setText(Double.toString(dedud));
		dedu.setFont(new Font("Agency FB", Font.PLAIN, 20));
		dedu.setColumns(10);
		dedu.setBounds(151, 101, 248, 34);
		img.add(dedu);
		
		JLabel lblToPay = new JLabel("To Pay:");
		lblToPay.setForeground(Color.white);
		lblToPay.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblToPay.setBounds(10, 145, 92, 23);
		img.add(lblToPay);
		
		topa = new JTextField();
		topa.setText(Double.toString(top));
		topa.setFont(new Font("Agency FB", Font.PLAIN, 20));
		topa.setColumns(10);
		topa.setBounds(151, 145, 248, 34);
		img.add(topa);
		
		JButton btnPlaceOrder = new JButton("Place"+"\n"+"Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					PreparedStatement pst=conn.prepareStatement("insert into history values(?,?)");
					pst.setString(1,rest);
					pst.setDouble(2,top);
					pst.executeUpdate();
					dispose();
					rating.main(null);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnPlaceOrder.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnPlaceOrder.setBounds(307, 189, 92, 34);
		btnPlaceOrder.setOpaque(false);
		btnPlaceOrder.addMouseListener(new MouseAdapter() {
	         Color color = btnPlaceOrder.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnPlaceOrder.getForeground();
	        	 btnPlaceOrder.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnPlaceOrder.setForeground(color);
	         }
	      });
		img.add(btnPlaceOrder);
		
		JButton btnBack = new JButton("Cancel");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Order Cancelled!");
				dispose();
				confirm.cost=0;
				confirm.ded=0;
				confirm.endcost=0;
				confirm.main(null);
			}
		});
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(205, 189, 92, 34);
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
		
		payment.setText("Cash On Delivery");
	}

}
