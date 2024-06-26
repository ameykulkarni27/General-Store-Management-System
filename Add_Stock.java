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
import javax.swing.ImageIcon;
import java.awt.Color;

public class Add_Stock extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField pn_txt;
	private JTextField pid_txt;
	private JTextField price_txt;
	private JTextField qtty_txt;
	ResultSet rs;
	Connection con;
	Statement stmt;
	String pn;
	int pid,price,qtty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Stock frame = new Add_Stock();
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
	public Add_Stock() {
		setBounds(0, 0, 1550, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Glocery Store Management - Stock Entry");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(865, 120, 671, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(912, 204, 296, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(912, 304, 296, 22);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(912, 407, 296, 22);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(912, 504, 296, 22);
		contentPane.add(lblNewLabel_4);
		
		pn_txt = new JTextField();
		pn_txt.setBounds(912, 226, 596, 40);
		contentPane.add(pn_txt);
		pn_txt.setColumns(10);
		
		pid_txt = new JTextField();
		pid_txt.setBounds(912, 326, 596, 40);
		contentPane.add(pid_txt);
		pid_txt.setColumns(10);
		
		price_txt = new JTextField();
		price_txt.setBounds(912, 429, 596, 40);
		contentPane.add(price_txt);
		price_txt.setColumns(10);
		
		qtty_txt = new JTextField();
		qtty_txt.setBounds(912, 526, 596, 40);
		contentPane.add(qtty_txt);
		qtty_txt.setColumns(10);
		
		JButton add = new JButton("Add");
		add.setBorderPainted(false);
		add.setBackground(new Color(0, 128, 0));
		add.setForeground(new Color(255, 255, 255));
		add.setFont(new Font("Arial", Font.BOLD, 19));
		add.setBounds(912, 631, 248, 50);
		add.addActionListener(this);
		contentPane.add(add);
		
		JButton updt = new JButton("Update");
		updt.setBackground(new Color(0, 128, 0));
		updt.setForeground(new Color(255, 255, 255));
		updt.setBorderPainted(false);
		updt.setFont(new Font("Arial", Font.BOLD, 18));
		updt.setBounds(1260, 631, 248, 50);
		updt.addActionListener(this);
		contentPane.add(updt);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("GB 1 (1).jpg"));
		lblNewLabel_5.setBounds(0, 0, 1536, 793);
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
						JOptionPane.showMessageDialog(null, "ID already present in stock");
						cleardata();
					}
					else
					{
						rs=stmt.executeQuery("select * from stock where Name='"+pn+"'");
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "Name already present in stock");
							cleardata();
						}
						else
						{
							stmt.executeUpdate("insert into stock(Name,ID,Price,Quantity)values('"+pn+"',"+pid+","+price+","+qtty+")");
							JOptionPane.showMessageDialog(null, "Record Inserted");
							cleardata();
						}
					}
				}
			}
			if(cmd.equals("Update"))
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
						stmt.executeUpdate("update stock set Name='"+pn+"' ,Price="+price+", Quantity="+qtty+" where ID="+pid+"");
						JOptionPane.showMessageDialog(null, "Updated Successfully");
						cleardata();
					}
					else
					{
						cleardata();
						JOptionPane.showMessageDialog(null, "Record not found");
					}
				}
				pn=pn_txt.getText();
				pid=Integer.parseInt(pid_txt.getText());
				price=Integer.parseInt(price_txt.getText());
				qtty=Integer.parseInt(qtty_txt.getText());
				
				rs=stmt.executeQuery("select * from stock where ID="+pid+"");
				if(rs.next())
				{
					stmt.executeUpdate("update stock set Name='"+pn+"' ,Price="+price+", Quantity="+qtty+" where ID="+pid+"");
					JOptionPane.showMessageDialog(null, "Updated Successfully");
					cleardata();
				}
				else
				{
					cleardata();
					JOptionPane.showMessageDialog(null, "Record not found");
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
