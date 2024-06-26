import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Display_Page extends JFrame {

	private JPanel contentPane;
	private JTable table;
	ResultSet rs;
	Connection con;
	Statement stmt;
	private JTextField pn_txt;
	private JTextField pid_txt;
	String pn,pid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_Page frame = new Display_Page();
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
	public Display_Page() {
		setBounds(0, 0, 1550, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
					{"Name", "ID", "Price", "Quantity"},
				},
				new String[] {
					"Name", "ID", "Price", "Quantity"
				}
			);
		
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		table.setRowHeight(30);
		table.setModel(model);
		table.setBounds(783, 65, 710, 667);
		contentPane.add(table);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/class_project","username","password");
			stmt=con.createStatement();
		}catch(Exception e1) {
			System.out.println(e1);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(93, 65, 562, 667);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Glocery Management Store - Stock Table");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 20, 542, 40);
		panel.add(lblNewLabel);
		
		pn_txt = new JTextField();
		pn_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		pn_txt.setBounds(10, 288, 542, 40);
		panel.add(pn_txt);
		pn_txt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 264, 212, 24);
		panel.add(lblNewLabel_1);
		
		pid_txt = new JTextField();
		pid_txt.setFont(new Font("Arial", Font.PLAIN, 14));
		pid_txt.setBounds(10, 404, 542, 40);
		panel.add(pid_txt);
		pid_txt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 380, 212, 24);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Stock Table", "Purchase Table", "Sales Table"}));
		comboBox.setBounds(10, 154, 212, 40);
		panel.add(comboBox);
		
		JButton search_btn = new JButton("Search");
		search_btn.setBorderPainted(false);
		search_btn.setForeground(new Color(255, 255, 255));
		search_btn.setBackground(new Color(0, 128, 0));
		search_btn.setFont(new Font("Arial", Font.BOLD, 18));
		search_btn.setBounds(204, 528, 140, 40);
		panel.add(search_btn);
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					model.setRowCount(1);
					if(comboBox.getSelectedItem().equals("Stock Table"))
					{
						if(pn_txt.getText().equals("") && pid_txt.getText().equals(""))
						{
							rs=stmt.executeQuery("select * from stock");
							if(rs.next())
							{
								rs=stmt.executeQuery("select * from stock");
								while(rs.next())
								{
									String nm = rs.getString("Name");
									String id = rs.getString("ID");
									String price = rs.getString("Price");
									String qtty = rs.getString("Quantity");
									
									model.addRow(new Object[] {
											nm,id,price,qtty,
									});
								}
							}
							else
								JOptionPane.showMessageDialog(null, "Stock is empty");
						}
						else if(pid_txt.getText().equals(""))
						{
							pn=pn_txt.getText();
							rs=stmt.executeQuery("select * from stock where Name='"+pn+"'");
							if(rs.next())
							{
								rs=stmt.executeQuery("select * from stock where Name='"+pn+"'");
								while(rs.next())
								{
									String nm = rs.getString("Name");
									String id = rs.getString("ID");
									String price = rs.getString("Price");
									String qtty = rs.getString("Quantity");
									
									model.addRow(new Object[] {
											nm,id,price,qtty,
									});
								}
							}
							else
								JOptionPane.showMessageDialog(null, "No stock found with given Name");
						}
						else
						{
							pn=pid_txt.getText();
							rs=stmt.executeQuery("select * from stock where ID='"+pn+"'");
							if(rs.next())
							{
								rs=stmt.executeQuery("select * from stock where ID='"+pn+"'");
								while(rs.next())
								{
									String nm = rs.getString("Name");
									String id = rs.getString("ID");
									String price = rs.getString("Price");
									String qtty = rs.getString("Quantity");
									
									model.addRow(new Object[] {
											nm,id,price,qtty,
									});
								}
							}
							else								
								JOptionPane.showMessageDialog(null, "No stock found with given ID");
						}
					}
						if(comboBox.getSelectedItem().equals("Purchase Table"))
						{
							if(pn_txt.getText().equals("") && pid_txt.getText().equals(""))
							{
								rs=stmt.executeQuery("select * from purchase");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from purchase");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "Stock is empty");
							}
							else if(pid_txt.getText().equals(""))
							{
								pn=pn_txt.getText();
								rs=stmt.executeQuery("select * from purchase where Name='"+pn+"'");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from purchase where Name='"+pn+"'");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "No stock found with given Name");
							}
							else
							{
								pn=pid_txt.getText();
								rs=stmt.executeQuery("select * from purchase where ID='"+pn+"'");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from purchase where ID='"+pn+"'");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "No stock found with given ID");
							}
						}
						if(comboBox.getSelectedItem().equals("Sales Table"))
						{
							if(pn_txt.getText().equals("") && pid_txt.getText().equals(""))
							{
								rs=stmt.executeQuery("select * from sale");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from sale");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "Stock is empty");
							}
							else if(pid_txt.getText().equals(""))
							{
								pn=pn_txt.getText();
								rs=stmt.executeQuery("select * from sale where Name='"+pn+"'");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from sale where Name='"+pn+"'");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "No stock found with given Name");
							}
							else
							{
								pn=pid_txt.getText();
								rs=stmt.executeQuery("select * from sale where ID='"+pn+"'");
								if(rs.next())
								{
									rs=stmt.executeQuery("select * from sale where ID='"+pn+"'");
									while(rs.next())
									{
										String nm = rs.getString("Name");
										String id = rs.getString("ID");
										String price = rs.getString("Price");
										String qtty = rs.getString("Quantity");
										
										model.addRow(new Object[] {
												nm,id,price,qtty,
										});
									}
								}
								else
									JOptionPane.showMessageDialog(null, "No stock found with given ID");
							}
						}
					pn_txt.setText("");
					pid_txt.setText("");
				}
				catch(Exception e2) {
					System.out.println(e2);
				}
			}
		});
	}
}
