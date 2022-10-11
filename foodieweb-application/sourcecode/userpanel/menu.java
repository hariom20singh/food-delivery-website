import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;

import net.proteanit.sql.DbUtils;

import javax.swing.event.ChangeEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
public class menu extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String res;
	private JSlider slider;
	private JLabel lblFood;
	private JTextField food;
	private JLabel lblPrice;
	private JSlider slider_1;
	private JButton btnAdd;
	private JButton btnBack;
	private JLabel lblQuantity;
	private JSpinner spinner = new JSpinner();
	private JButton btnView;
	private JButton btnRemove;
	private JButton btnPlaceOrder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	public menu() {
		String res=restaurant1.rest;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("menu");
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
		table.setRowHeight(40);
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		scrollPane.setViewportView(table);

		lblFood = new JLabel("Food");
		lblFood.setForeground(Color.white);
		lblFood.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblFood.setBounds(439, 154, 92, 23);
		img.add(lblFood);
		
		food = new JTextField();
		food.setFont(new Font("Agency FB", Font.PLAIN, 20));
		food.setColumns(10);
		food.setBounds(439, 180, 208, 34);
		img.add(food);
		
		lblPrice = new JLabel("Sort Price");
		lblPrice.setForeground(Color.white);
		lblPrice.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrice.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblPrice.setBounds(440, 295, 200, 36);
		img.add(lblPrice);
		
		slider = new JSlider();
		slider.setMinimum(10);
		slider.setMaximum(1000);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					if(slider.getValue()==1000)
					{
						PreparedStatement pst=conn.prepareStatement("select * from "+res+"");
						ResultSet rst=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rst));
					}	
					else
					{
						PreparedStatement ps=conn.prepareStatement("select * from "+res+" where price<="+slider.getValue()+"");
						ResultSet rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				}
				catch(Exception e1)
				{
					
				}
			}
		});
		slider.setValue(1000);
		slider.setFont(new Font("Agency FB", Font.PLAIN, 20));
		slider.setBounds(440, 340, 200, 22);
		img.add(slider);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.white);
		lblQuantity.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblQuantity.setBounds(439, 230, 92, 23);
		img.add(lblQuantity);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		spinner.setFont(new Font("Agency FB", Font.PLAIN, 20));
		spinner.setForeground(Color.white);
		spinner.setBounds(440, 260, 44, 32);
		img.add(spinner);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					ArrayList<Integer> pr=new ArrayList<Integer>();
					if(food.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select * from "+res+" where food='"+food.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(!rs.next())
						throw new Exception();
					pr.add(rs.getInt(2));
					PreparedStatement ps=conn.prepareStatement("insert into ordered values(?,?,?)");
					ps.setString(1,food.getText());
					ps.setInt(2,(int)spinner.getValue());
					ps.setInt(3,pr.get(0)*(int)spinner.getValue());
					ps.executeUpdate();
					pr.removeAll(pr);
					food.setText("");
					spinner.setValue(1);
				}
				catch(Exception e1)
				{
					if(error!=1)
						food.setText("");
					JOptionPane.showMessageDialog(null,"Food Doesn't Exists");
				}
			}
		});
		btnAdd.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnAdd.setBounds(439, 375, 92, 34);
		btnAdd.setOpaque(false);
		btnAdd.addMouseListener(new MouseAdapter() {
	         Color color = btnAdd.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnAdd.getForeground();
	        	 btnAdd.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnAdd.setForeground(color);
	         }
	      });
		img.add(btnAdd);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					String sql2="delete from ordered";
					PreparedStatement pst=conn.prepareStatement(sql2);
					pst.executeUpdate();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				dispose();
				restaurant1.main(null);
			}
		});
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
		btnBack.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnBack.setBounds(315, 415, 92, 34);
		img.add(btnBack);
				
		btnView = new JButton("Cart");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.main(null);
			}
		});
		btnView.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnView.setBounds(550, 375, 92, 34);
		btnView.setOpaque(false);
		btnView.addMouseListener(new MouseAdapter() {
	         Color color = btnView.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnView.getForeground();
	        	 btnView.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnView.setForeground(color);
	         }
	      });
		img.add(btnView);
		
		btnRemove = new JButton("Reset Cart");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					String sql2="delete from ordered";
					PreparedStatement pst=conn.prepareStatement(sql2);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Cart emptied");
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		btnRemove.setFont(new Font("Agency FB", Font.PLAIN, 17));
		btnRemove.setBounds(439, 415, 92, 34);
		btnRemove.setOpaque(false);
		btnRemove.addMouseListener(new MouseAdapter() {
	         Color color = btnRemove.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnRemove.getForeground();
	        	 btnRemove.setForeground(Color.red);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnRemove.setForeground(color);
	         }
	      });
		img.add(btnRemove);
		
		btnPlaceOrder = new JButton("Confirm");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				confirm.cost=0;
				confirm.ded=0;
				confirm.endcost=0;
				confirm.main(null);
			}
		});
		btnPlaceOrder.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnPlaceOrder.setBounds(550, 415, 92, 34);
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
	}
}
