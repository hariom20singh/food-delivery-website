package adminpanel;
import java.awt.BorderLayout;
import java.util.*;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class adminedit extends JFrame {

	private JPanel contentPane;
	private JTextField restaurant1;
	private JTextField restaurant2;
	private JTextField food1;
	private JTextField code1;
	private JTextField price;
	private JTextField ded;
	private JTextField code2;
	private JTextField restaurant3;
	private JTextField restaurant4;
	private JTextField food2;
	private JTable table;
	private JTextField high;
	private JTextField low;
	static String hi,lo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<String> r=new ArrayList<String>();
					ArrayList<Double> a=new ArrayList<Double>();
					int i=0;
					double temp;
					String tem;
					adminedit frame = new adminedit();
					frame.setVisible(true);
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="select * from history";
					ResultSet rs=stmt.executeQuery(sql);
					frame.table.setModel(DbUtils.resultSetToTableModel(rs));
					String sql1="select restaurant,sum(amount) as amount from history group by restaurant order by amount";
					ResultSet rst=stmt.executeQuery(sql1);
					while(rst.next())
					{
						a.add(rst.getDouble(2));
						r.add(rst.getString(1));
					}
					if(!a.isEmpty())
					{
						hi=r.get(a.size()-1);
						lo=r.get(0);
						frame.high.setText(hi);
						if(hi!=lo)
							frame.low.setText(lo);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminedit() {
		String h=hi,l=lo;
		//System.out.print(lo+hi);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 394);
		setTitle("EditPanel-admin");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 46, 667, 297);
		tabbedPane.setFont(new Font("Agency FB", Font.PLAIN, 15));
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Restaurant", null, panel, null);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Agency FB", Font.PLAIN, 15));
		tabbedPane_1.setBounds(20, 20, 615, 234);
		panel.add(tabbedPane_1);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("Add", null, panel_6, null);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurant Name");
		lblNewLabel.setBounds(140, 82, 129, 30);
		panel_6.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 25));
		
		restaurant1 = new JTextField();
		restaurant1.setBounds(140, 122, 248, 34);
		panel_6.add(restaurant1);
		restaurant1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant1.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(303, 166, 85, 34);
		panel_6.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant1.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					PreparedStatement pst=conn.prepareStatement("CREATE TABLE IF NOT EXISTS "+restaurant1.getText()+"(food varchar(20) primary key,price int)");
					error=3;
					pst.executeUpdate();
					error=0;
					PreparedStatement ps=conn.prepareStatement("insert into restaurant(name) values(?)");
					ps.setString(1,restaurant1.getText());
					ps.executeUpdate();
					restaurant1.setText("");
					JOptionPane.showMessageDialog(null,"Restaurant Added Successfully");
				}
				catch(Exception e1)
				{
					if(error!=2)
						restaurant1.setText("");
					if(error==1)
						JOptionPane.showMessageDialog(null,"Fill The Form");
					else if(error==3)
						JOptionPane.showMessageDialog(null,"Remove Spaces");
					else
						JOptionPane.showMessageDialog(null,"Restaurant Name Already Exists");
				}
			}
		}
		);
		btnNewButton.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		JLabel lblDelizhAdd = new JLabel("FOODIEweb - ADD RESTAURANT");
		lblDelizhAdd.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhAdd.setBounds(140, 10, 350, 36);
		panel_6.add(lblDelizhAdd);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_1.addTab("Remove", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel label_4 = new JLabel("Restaurant Name");
		label_4.setFont(new Font("Agency FB", Font.PLAIN, 25));
		label_4.setBounds(140, 82, 129, 30);
		panel_7.add(label_4);
		
		restaurant2 = new JTextField();
		restaurant2.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant2.setColumns(10);
		restaurant2.setBounds(140, 122, 248, 34);
		panel_7.add(restaurant2);
		
		JButton button_6 = new JButton("Confirm");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant2.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql1="Select * from restaurant where name='"+restaurant2.getText()+"'";
					ResultSet rs1=stmt.executeQuery(sql1);
					if(!rs1.next())
						throw new Exception();
					String sql2="delete from restaurant where name='"+restaurant2.getText()+"'";
					PreparedStatement pst=conn.prepareStatement(sql2);
					pst.executeUpdate();
					PreparedStatement pst1=conn.prepareStatement("drop table "+restaurant2.getText()+"");
					restaurant2.setText("");
					pst1.executeUpdate();
					JOptionPane.showMessageDialog(null,"Restaurant Removed Successfully");
				}
				catch(Exception e1)
				{
					if(error!=2)
						restaurant2.setText("");
					if(error==1)
						JOptionPane.showMessageDialog(null,"Fill The Form");
					else
						JOptionPane.showMessageDialog(null,"Restaurant Doesn't Exists");
				}
			}
		});
		button_6.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_6.setBounds(303, 166, 85, 34);
		panel_7.add(button_6);
		
		JLabel lblDelizhRemove = new JLabel("FOODIEweb - REMOVE RESTAUTANT");
		lblDelizhRemove.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhRemove.setBounds(140, 10, 350, 36);
		panel_7.add(lblDelizhRemove);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Food", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setFont(new Font("Agency FB", Font.PLAIN, 15));
		tabbedPane_2.setBounds(20, 20, 615, 234);
		panel_1.add(tabbedPane_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_2.addTab("Add", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FOODIEweb - ADD FOOD");
		lblNewLabel_1.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblNewLabel_1.setBounds(140, 10, 350, 36);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblFoodName.setBounds(10, 122, 129, 30);
		panel_2.add(lblFoodName);
		
		food1 = new JTextField();
		food1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		food1.setColumns(10);
		food1.setBounds(10, 162, 248, 34);
		panel_2.add(food1);
		
		JButton button_2 = new JButton("Confirm");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant3.getText().equals("")||food1.getText().equals("")||price.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql1="Select * from restaurant where name='"+restaurant3.getText()+"'";
					ResultSet rs1=stmt.executeQuery(sql1);
					if(!rs1.next())
					{
						error=1;
						throw new Exception();
					}
					PreparedStatement ps=conn.prepareStatement("insert into "+restaurant3.getText()+" values(?,?)");
					ps.setString(1,food1.getText());
					ps.setString(2,price.getText());
					ps.executeUpdate();
					restaurant3.setText("");
					food1.setText("");
					price.setText("");
					JOptionPane.showMessageDialog(null,"Food Added");
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						restaurant3.setText("");
						food1.setText("");
						price.setText("");
					}
					if(error==2)
						JOptionPane.showMessageDialog(null,"Fill All The Forms");
					else if(error==1)
						JOptionPane.showMessageDialog(null,"Restaurant Doesn't Exists");
					else
						JOptionPane.showMessageDialog(null,"Food Already Exists");
				}
			}
		});
		button_2.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_2.setBounds(466, 166, 85, 34);
		panel_2.add(button_2);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblPrice.setBounds(303, 82, 129, 30);
		panel_2.add(lblPrice);
		
		price = new JTextField();
		price.setFont(new Font("Agency FB", Font.PLAIN, 20));
		price.setColumns(10);
		price.setBounds(303, 122, 248, 34);
		panel_2.add(price);
		
		JLabel lblRestaurantName = new JLabel("Restaurant Name");
		lblRestaurantName.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblRestaurantName.setBounds(10, 45, 129, 30);
		panel_2.add(lblRestaurantName);
		
		restaurant3 = new JTextField();
		restaurant3.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant3.setColumns(10);
		restaurant3.setBounds(10, 82, 248, 34);
		panel_2.add(restaurant3);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_2.addTab("Remove", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblDelizhRemove_1 = new JLabel("FOODIEweb - REMOVE FOOD");
		lblDelizhRemove_1.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhRemove_1.setBounds(140, 10, 350, 36);
		panel_8.add(lblDelizhRemove_1);
		
		JLabel lblRestaurantName_1 = new JLabel("Restaurant Name");
		lblRestaurantName_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblRestaurantName_1.setBounds(10, 82, 129, 30);
		panel_8.add(lblRestaurantName_1);
		
		restaurant4 = new JTextField();
		restaurant4.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant4.setColumns(10);
		restaurant4.setBounds(10, 122, 248, 34);
		panel_8.add(restaurant4);
		
		JLabel lblFoodName_1 = new JLabel("Food Name");
		lblFoodName_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblFoodName_1.setBounds(303, 82, 129, 30);
		panel_8.add(lblFoodName_1);
		
		food2 = new JTextField();
		food2.setFont(new Font("Agency FB", Font.PLAIN, 20));
		food2.setColumns(10);
		food2.setBounds(303, 122, 248, 34);
		panel_8.add(food2);
		
		JButton button_9 = new JButton("Confirm");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant4.getText().equals("")||food2.getText().equals(""))
					{
						error=2;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql1="Select * from restaurant where name='"+restaurant4.getText()+"'";
					ResultSet rs1=stmt.executeQuery(sql1);
					if(!rs1.next())
					{
						error=1;
						throw new Exception();
					}
					String sql2="Select * from "+restaurant4.getText()+" where food='"+food2.getText()+"'";
					ResultSet rs2=stmt.executeQuery(sql2);
					if(!rs2.next())
						throw new Exception();
					PreparedStatement pst=conn.prepareStatement("delete from "+restaurant4.getText()+" where food='"+food2.getText()+"'");
					pst.executeUpdate();
					restaurant4.setText("");
					food2.setText("");
					JOptionPane.showMessageDialog(null,"Food Removed Successfully");
				}
				catch(Exception e1)
				{
					if(error!=2)
					{
						restaurant4.setText("");
						food2.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null,"Restaurant Doesn't Exists");
					else
						JOptionPane.showMessageDialog(null,"Food Doesn't Exists");
				}
			}
		});
		button_9.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_9.setBounds(466, 166, 85, 34);
		panel_8.add(button_9);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Coupon", null, panel_3, null);
		panel_3.setLayout(null);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_3.setFont(new Font("Agency FB", Font.PLAIN, 15));
		tabbedPane_3.setBounds(20, 20, 615, 234);
		panel_3.add(tabbedPane_3);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_3.addTab("Add", null, panel_9, null);
		panel_9.setLayout(null);
		
		JLabel lblDelizhAdd_1 = new JLabel("FOODIEweb - ADD COUPON");
		lblDelizhAdd_1.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhAdd_1.setBounds(140, 10, 350, 36);
		panel_9.add(lblDelizhAdd_1);
		
		JLabel lblCouponCode = new JLabel("Coupon Code");
		lblCouponCode.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblCouponCode.setBounds(10, 82, 129, 30);
		panel_9.add(lblCouponCode);
		
		code1 = new JTextField();
		code1.setFont(new Font("Agency FB", Font.PLAIN, 20));
		code1.setColumns(10);
		code1.setBounds(10, 122, 248, 34);
		panel_9.add(code1);
		
		JButton button_5 = new JButton("Confirm");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(code1.getText().equals("")||ded.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					PreparedStatement pst=conn.prepareStatement("insert into coupon values(?,?)");
					pst.setString(1,code1.getText());
					pst.setString(2,ded.getText());
					code1.setText("");
					ded.setText("");
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Coupon Added Successfully");
				}
				catch(Exception e1)
				{
					if(error!=1)
					{
						code1.setText("");
						ded.setText("");
					}
					if(error==1)
						JOptionPane.showMessageDialog(null,"Fill All The Forms");
					else
						JOptionPane.showMessageDialog(null,"Coupon Code Already Exists");
				}
			}
		});
		button_5.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_5.setBounds(466, 166, 85, 34);
		panel_9.add(button_5);
		
		JLabel lblDeduction = new JLabel("Deduction");
		lblDeduction.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblDeduction.setBounds(303, 82, 129, 30);
		panel_9.add(lblDeduction);
		
		ded = new JTextField();
		ded.setFont(new Font("Agency FB", Font.PLAIN, 20));
		ded.setColumns(10);
		ded.setBounds(303, 122, 248, 34);
		panel_9.add(ded);
		
		JPanel panel_10 = new JPanel();
		tabbedPane_3.addTab("Remove", null, panel_10, null);
		panel_10.setLayout(null);
		
		JLabel label = new JLabel("FOODIEweb - REMOVE COUPON");
		label.setFont(new Font("Agency FB", Font.BOLD, 30));
		label.setBounds(140, 10, 350, 36);
		panel_10.add(label);
		
		JLabel lblCouponCode_1 = new JLabel("Coupon Code");
		lblCouponCode_1.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblCouponCode_1.setBounds(140, 82, 129, 30);
		panel_10.add(lblCouponCode_1);
		
		code2 = new JTextField();
		code2.setFont(new Font("Agency FB", Font.PLAIN, 20));
		code2.setColumns(10);
		code2.setBounds(140, 122, 248, 34);
		panel_10.add(code2);
		
		JButton button_8 = new JButton("Confirm");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(code2.getText().equals(""))
					{
						error=1;
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select * from coupon where code='"+code2.getText()+"'";
					ResultSet rs=stmt.executeQuery(sql);
					if(!rs.next())
						throw new Exception();
					PreparedStatement ps=conn.prepareStatement("delete from coupon where code='"+code2.getText()+"'");
					code2.getText();
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,"Coupon Removed Successfully");
				}
				catch(Exception e1)
				{
					if(error!=1)
						code2.setText("");
					if(error==1)
						JOptionPane.showMessageDialog(null,"Fill The Form");
					else
						JOptionPane.showMessageDialog(null,"Coupon Code Doesn't Exists");
				}
			}
		});
		button_8.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button_8.setBounds(303, 166, 85, 34);
		panel_10.add(button_8);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("History", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 381, 184);
		panel_5.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(40);
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JLabel lblDelizhHistory = new JLabel("FOODIEweb - HISTORY");
		lblDelizhHistory.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblDelizhHistory.setBounds(140, 10, 350, 36);
		panel_5.add(lblDelizhHistory);
		
		JLabel lblHighestProfitFrom = new JLabel("Highest Profit From");
		lblHighestProfitFrom.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblHighestProfitFrom.setBounds(401, 59, 152, 30);
		panel_5.add(lblHighestProfitFrom);
		
		high = new JTextField();
		//high.setText(h);
		high.setFont(new Font("Agency FB", Font.PLAIN, 20));
		high.setColumns(10);
		high.setBounds(401, 96, 248, 34);
		panel_5.add(high);
		
		JLabel lblLowestProfitFrom = new JLabel("Lowest Profit From");
		lblLowestProfitFrom.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblLowestProfitFrom.setBounds(401, 140, 152, 30);
		panel_5.add(lblLowestProfitFrom);
		
		low = new JTextField();
	//	low.setText(l);
		low.setFont(new Font("Agency FB", Font.PLAIN, 20));
		low.setColumns(10);
		low.setBounds(401, 177, 248, 34);
		panel_5.add(low);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				adminlogin.main(null);
			}
		});
		btnLogout.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnLogout.setBounds(592, 10, 85, 34);
		contentPane.add(btnLogout);
	}
}
