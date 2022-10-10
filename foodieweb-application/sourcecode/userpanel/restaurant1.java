import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;
import javax.swing.event.*;

import net.proteanit.sql.DbUtils;


public class restaurant1 extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField restaurant;
	static String rest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {			
					restaurant1 frame = new restaurant1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public restaurant1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		setTitle("restaurants");
		contentPane = new JPanel();
		contentPane.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent e) {
			}
		});
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel img=new JLabel();
		contentPane.add(img);
		img.setBounds(0, 0, 700, 700);
		ImageIcon myimg=new ImageIcon("D:\\rest1.jpg");
		Image im=myimg.getImage();
		Image newim=im.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iii=new ImageIcon(newim);
		img.setIcon(iii);
		
		JMenuBar mb=new JMenuBar();
		setJMenuBar(mb);
		mb.setBackground(new Color(255, 207, 130));
		mb.setPreferredSize(new Dimension(100,30));
		JMenu home=new JMenu("home");
		home.setBorder(new EmptyBorder(0,20,0,20));
		mb.add(home);
		home.addMouseListener(new MouseAdapter() {
	         Color color = home.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = home.getForeground();
	             home.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             home.setForeground(color);
	         }
	      });
		home.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
	            dispose();
	            restaurant1.main(null);
	        }
			 public void menuDeselected(MenuEvent e) {
		            

		        }
		        public void menuCanceled(MenuEvent e) {
		            

		        }
		});
		JMenu account=new JMenu("account");
		account.setBorder(new EmptyBorder(0,20,0,20));
		account.addMouseListener(new MouseAdapter() {
	         Color color = account.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = account.getForeground();
	             account.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             account.setForeground(color);
	         }
	      });
		mb.add(account);
		JMenuItem chps=new JMenuItem("change password");
		chps.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetpass.main(null);
            }
        });

		account.add(chps);
		JMenu logout=new JMenu("logout");
		account.add(logout);
		JMenuItem signout=new JMenuItem("sign out");
		signout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            	welcome.main(null);
            }
        });
		logout.add(signout);
		JMenuItem swtch=new JMenuItem("switch account");
		swtch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                login.main(null);
            }
        });
		logout.add(swtch);
		JMenuItem dlt=new JMenuItem("delete account");
		dlt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteacc.main(null);
            }
        });
		logout.add(dlt);
		JMenu about=new JMenu("about");
		about.setBorder(new EmptyBorder(0,20,0,20));
		about.addMouseListener(new MouseAdapter() {
	         Color color = about.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = about.getForeground();
	             about.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             about.setForeground(color);
	         }
	      });
		mb.add(about);
		mb.add(Box.createHorizontalGlue());
		JMenu contact=new JMenu("contact");
		contact.setBorder(new EmptyBorder(0,20,0,35));
		contact.addMouseListener(new MouseAdapter() {
	         Color color = contact.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = contact.getForeground();
	             contact.setForeground(new Color(255, 140, 0));
	         }
	         public void mouseExited(MouseEvent me) {
	             contact.setForeground(color);
	         }
	      });
		mb.add(contact);
		
		JLabel lblDelizh = new JLabel("FOODIEweb");
		lblDelizh.setForeground(new Color(255, 140, 0));
		lblDelizh.setFont(new Font("Agency FB", Font.BOLD, 35));
		lblDelizh.setBounds(290, 585, 200, 50);
		img.add(lblDelizh);
		
		JLabel plt=new JLabel();
		plt.setBounds(178, 550, 350, 150);
		ImageIcon m1=new ImageIcon("D:\\plt2.png");
		Image m2=m1.getImage();
		Image m3=m2.getScaledInstance(plt.getWidth(), plt.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon ii=new ImageIcon(m3);
		plt.setIcon(ii);
		plt.setOpaque(false);
		img.add(plt);
		
		JLabel w=new JLabel();
		w.setBounds(110, 10, 150, 50);
		ImageIcon m5=new ImageIcon("D:\\wel2.png");
		Image m6=m5.getImage();
		Image m7=m6.getScaledInstance(w.getWidth(), w.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon i1=new ImageIcon(m7);
		w.setIcon(i1);
		w.setOpaque(false);
		img.add(w);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 120, 430, 200);
		img.add(scrollPane);
		
		JTable table = new JTable();
		table.setRowHeight(30);
		table.setForeground(Color.black);
		table.setFont(new Font("Agency FB", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					if(slider.getValue()==0)
					{
						PreparedStatement pst=conn.prepareStatement("select name,cast(rating as decimal(10,1)) as rating from restaurant");
						ResultSet rst=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rst));
					}
					else
					{
						PreparedStatement ps=conn.prepareStatement("select name,rating from restaurant where rating>="+slider.getValue()+"");
						ResultSet rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		slider.setFont(new Font("Agency FB", Font.PLAIN, 20));
		slider.setMaximum(5);
		slider.setBounds(358, 340, 200, 22);
		img.add(slider);
		
		JLabel lblNewLabel = new JLabel("Sort Rating");
		//lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(new Color(255,207,130));
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblNewLabel.setBounds(280, 333, 200, 36);
		img.add(lblNewLabel);
		
		JLabel lblRestaurant = new JLabel("Restaurant");
		lblRestaurant.setForeground(new Color(255,207,130));
		lblRestaurant.setFont(new Font("Agency FB", Font.PLAIN, 25));
		lblRestaurant.setBounds(130, 465, 92, 23);
		img.add(lblRestaurant);
		
		restaurant = new JTextField();
		restaurant.setFont(new Font("Agency FB", Font.PLAIN, 20));
		restaurant.setColumns(10);
		restaurant.setBounds(230, 460, 248, 34);
		img.add(restaurant);
		
		JButton btnConfirm = new JButton("Go");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int error=-1;
				try
				{
					if(restaurant.getText().equals(""))
					{
						error=1;
						throw new Exception();
					}
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/delizh","root","246800");
					Statement stmt=conn.createStatement();
					String sql="Select * from "+restaurant.getText()+"";
					ResultSet rs=stmt.executeQuery(sql);
					rest=restaurant.getText();
					PreparedStatement ps=conn.prepareStatement("delete from ordered");
					ps.executeUpdate();
					menu.main(null);
					dispose();
				}
				catch(Exception e1)
				{
					if(error!=1)
						restaurant.setText("");
					JOptionPane.showMessageDialog(null,"Restaurant Doesn't Exists");
				}
			}
		});
		btnConfirm.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btnConfirm.setBounds(500, 460, 52, 34);
		btnConfirm.setOpaque(false);
		btnConfirm.addMouseListener(new MouseAdapter() {
	         Color color = btnConfirm.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnConfirm.getForeground();
	        	 btnConfirm.setForeground(Color.blue);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnConfirm.setForeground(color);
	         }
	      });
		
		img.add(btnConfirm);
		
		JButton btnCoupon = new JButton("Coupons");
		btnCoupon.setOpaque(false);
		btnCoupon.addMouseListener(new MouseAdapter() {
	         Color color = btnCoupon.getForeground();
	         public void mouseEntered(MouseEvent me) {
	        	 color = btnCoupon.getForeground();
	             btnCoupon.setForeground(Color.blue);
	         }
	         public void mouseExited(MouseEvent me) {
	             btnCoupon.setForeground(color);
	         }
	      });
		btnCoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coupon.main(null);
			}
		});
		btnCoupon.setBounds(130, 338, 92, 30);
		img.add(btnCoupon);
		btnCoupon.setFont(new Font("Agency FB", Font.PLAIN, 20));
	}
}
