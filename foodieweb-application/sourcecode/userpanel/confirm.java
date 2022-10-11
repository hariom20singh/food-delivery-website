import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class confirm extends JFrame {
	private JComboBox comboBox;
	private JPanel contentPane;
	private JTable table;
	private JTextField total;
	private JTextField code;
	static double cost=0,endcost,ded=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					confirm frame = new confirm();
					frame.setVisible(true);
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql1="Select * from ordered";
					ResultSet rst=stmt.executeQuery(sql1);
					frame.table.setModel(DbUtils.resultSetToTableModel(rst));
					ResultSet rs=stmt.executeQuery(sql1);
					while(rs.next())
						cost+=rs.getInt(3);
					frame.total.setText(Double.toString(cost));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public confirm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("book food");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 700, 700);
		ImageIcon myimg=new ImageIcon("D:\\home1.jpg");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 400, 255);
		img.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(Color.white);
		lblTotal.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblTotal.setBounds(260, 420, 92, 23);
		img.add(lblTotal);
		
		total = new JTextField();
		total.setFont(new Font("Agency FB", Font.PLAIN, 20));
		total.setColumns(10);
		total.setBounds(315, 415, 92, 34);
		img.add(total);
		
		JLabel lblCouponoptional = new JLabel("Coupon(Optional)");
		lblCouponoptional.setForeground(Color.white);
		lblCouponoptional.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCouponoptional.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblCouponoptional.setBounds(435, 145, 135, 34);
		img.add(lblCouponoptional);
		
		code = new JTextField();
		code.setFont(new Font("Agency FB", Font.PLAIN, 20));
		code.setColumns(10);
		code.setBounds(435, 185, 100, 34);
		img.add(code);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cash On Delivery", "Net Banking", "Debit Card"}));
		comboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		comboBox.setBounds(435, 270, 230, 34);
		img.add(comboBox);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setForeground(Color.white);
		lblPaymentMethod.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPaymentMethod.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPaymentMethod.setBounds(435, 230, 135, 34);
		img.add(lblPaymentMethod);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try
					{
						if(!code.getText().equals(""))
						{
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
							Statement stmt=conn.createStatement();
							String sql="Select deduction from coupon where code='"+code.getText()+"'";
							ResultSet rs=stmt.executeQuery(sql);
							if(!rs.next())
								throw new Exception();
							ded=rs.getDouble(1);
						}
						endcost=cost-(cost*ded);
						if(comboBox.getSelectedItem().equals("Cash On Delivery"))
						{
							booked.main(null);						
						}
						else if(comboBox.getSelectedItem().equals("Net Banking"))
						{
							JOptionPane.showMessageDialog(null,"Currently unavailable");
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Currently unavailable");	
						}
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null,"Coupon Code Doesn't Exists");
					}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(550, 330, 92, 34);
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
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.main(null);
			}
		});
		button_1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_1.setBounds(439, 330, 92, 34);
		button_1.setOpaque(false);
		button_1.addMouseListener(new MouseAdapter() {
	         Color color = button_1.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = button_1.getForeground();
	        	 button_1.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             button_1.setForeground(color);
	         }
	      });
		img.add(button_1);
		
		JButton button = new JButton("Coupons");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coupon.main(null);
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(550, 185, 92, 34);
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
	}

}
