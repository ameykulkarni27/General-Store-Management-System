import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Sale_Page extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField pn_txt;
	private JTextField pid_txt;
	private JTextField price_txt;
	private JTextField qtty_txt;
	ResultSet rs;
	Connection con;
	Statement stmt;
	String pn,stk_nm;
	int pid,price,qtty,stk_qtty,stk_price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sale_Page frame = new Sale_Page();
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
	public Sale_Page() {
		setBounds(0, 0, 1550, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Glocery Store Management - Sales Entry");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 103, 729, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(30, 204, 296, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(30, 288, 296, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(30, 376, 296, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(30, 463, 296, 22);
		contentPane.add(lblNewLabel_4);
		
		pn_txt = new JTextField();
		pn_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		pn_txt.setBounds(30, 226, 679, 40);
		contentPane.add(pn_txt);
		pn_txt.setColumns(10);
		
		pid_txt = new JTextField();
		pid_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		pid_txt.setBounds(30, 310, 679, 40);
		contentPane.add(pid_txt);
		pid_txt.setColumns(10);
		
		price_txt = new JTextField();
		price_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		price_txt.setBounds(30, 398, 679, 40);
		contentPane.add(price_txt);
		price_txt.setColumns(10);
		
		qtty_txt = new JTextField();
		qtty_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		qtty_txt.setBounds(30, 485, 679, 40);
		contentPane.add(qtty_txt);
		qtty_txt.setColumns(10);
		
		JButton add = new JButton("Add");
		add.setBackground(new Color(0, 128, 0));
		add.setBorderPainted(false);
		add.setForeground(new Color(255, 255, 255));
		add.setFont(new Font("Arial", Font.BOLD, 18));
		add.setBounds(30, 589, 248, 50);
		add.addActionListener(this);
		contentPane.add(add);
		
		JButton updt = new JButton("Search");
		updt.setBorderPainted(false);
		updt.setBackground(new Color(0, 128, 0));
		updt.setForeground(new Color(255, 255, 255));
		updt.setFont(new Font("Arial", Font.BOLD, 18));
		updt.setBounds(461, 589, 248, 50);
		updt.addActionListener(this);
		contentPane.add(updt);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("delivery-grocery-shopping-healthy-food-background-vegan-vegetarian-paper-bag-vegetables-fruits-nuts-grains-white-copy-178150204 (2) (1).jpg"));
		lblNewLabel_5.setBounds(0, 0, 1536, 806);
		contentPane.add(lblNewLabel_5);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/class_project","username","password");
			stmt=con.createStatement();
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		try {
			String cmd=ae.getActionCommand();
			if(cmd.equals("Add"))
			{
				if(pn_txt.getText().equals("")||pid_txt.getText().equals("")||price_txt.getText().equals("")||qtty_txt.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill All Boxes");
				}
				else
				{
					pn=pn_txt.getText();
					pid=Integer.parseInt(pid_txt.getText());
					price=Integer.parseInt(price_txt.getText());
					qtty=Integer.parseInt(qtty_txt.getText());
					
					rs=stmt.executeQuery("select * from stock where ID="+pid+"");
					if(rs.next())
					{
						stk_nm=rs.getString("Name");
						if(pn.equals(stk_nm))
						{
							stk_price=rs.getInt("Price");
							if(price==stk_price)
							{
								stk_qtty=Integer.parseInt(rs.getString("Quantity"));
								if(stk_qtty>=qtty)
								{
									stmt.executeUpdate("update stock set Name='"+pn+"' ,Price="+price+", Quantity="+(stk_qtty-qtty)+" where ID="+pid+"");	
									stmt.executeUpdate("insert into sale(Name,ID,Price,Quantity)values('"+pn+"',"+pid+","+price+","+qtty+")");
									JOptionPane.showMessageDialog(null, "Sales Added");
									cleardata();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Available stock is less than required");
								}
								
							}
							else
								JOptionPane.showMessageDialog(null, "Mismatched Price Of Product \nCreate New Stock or correct the Price");
						}
						else
							JOptionPane.showMessageDialog(null, "Mismatched Name Of Product \nCreate New Stock or correct the Name");
					}
					else
						JOptionPane.showMessageDialog(null, "ID not Found");
				}
			}
			if(cmd.equals("Search"))
			{
				if(pid_txt.getText().equals(""))
				{
					if(pn_txt.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Fill Product ID or Product Name");
					}
					else
					{
						pn=pn_txt.getText();
						rs=stmt.executeQuery("select * from stock where Name='"+pn+"'");
						if(rs.next())
						{
							pid_txt.setText(rs.getString("ID"));
							price_txt.setText(rs.getString("Price"));
						}
						else
						{
							cleardata();
							JOptionPane.showMessageDialog(null, "Product Name not found in stock");
						}
					}
				}
				else
				{
					pid=Integer.parseInt(pid_txt.getText());
					rs=stmt.executeQuery("select * from stock where ID="+pid+"");
					if(rs.next())
					{
						pn_txt.setText(rs.getString("Name"));
						price_txt.setText(rs.getString("Price"));
					}
					else
					{
						cleardata();
						JOptionPane.showMessageDialog(null, "Product ID not found in stock");
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void cleardata()
	{
		pn_txt.setText("");
		pid_txt.setText("");
		price_txt.setText("");
		qtty_txt.setText("");
	}
	public void checkData()
	{
		
	}
}
