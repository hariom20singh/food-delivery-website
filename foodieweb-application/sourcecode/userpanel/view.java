import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class view extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
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
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 422, 304);
		ImageIcon myimg=new ImageIcon("D:\\blr1.png");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 38, 380, 180);
		img.add(scrollPane);
		
		table = new JTable();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
			PreparedStatement pst=conn.prepareStatement(" select * from ordered;");
			ResultSet rst=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		table.setRowHeight(40);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setFont(new Font("Agency FB", Font.PLAIN, 20));
		button.setBounds(304, 223, 92, 34);
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
		img.add(button);
	}

}
