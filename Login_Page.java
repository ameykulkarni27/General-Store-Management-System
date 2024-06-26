import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login_Page extends JFrame {

	private JPanel contentPane;
	private JTextField un_txt;
	ResultSet rs;
	Connection con;
	Statement stmt;
	   
	String un,pw,tun,tpf;
	private JPasswordField pw_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Page frame = new Login_Page();
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
	public Login_Page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1550, 830);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(816, 128, 680, 489);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Glocery Store Management - Admin Login");
		lblNewLabel.setBounds(10, 34, 660, 47);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 24));
		
		un_txt = new JTextField();
		un_txt.setBounds(10, 179, 660, 36);
		panel.add(un_txt);
		un_txt.setFont(new Font("Arial", Font.PLAIN, 16));
		un_txt.setColumns(10);
		
		pw_txt = new JPasswordField();
		pw_txt.setBounds(10, 281, 660, 36);
		panel.add(pw_txt);
		pw_txt.setFont(new Font("Arial", Font.PLAIN, 16));
		
		JLabel un_lbl = new JLabel("Username");
		un_lbl.setBounds(10, 150, 235, 29);
		panel.add(un_lbl);
		un_lbl.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel pw_lbl = new JLabel("Password");
		pw_lbl.setBounds(10, 255, 235, 22);
		panel.add(pw_lbl);
		pw_lbl.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JButton sub = new JButton("Login");
		sub.setForeground(new Color(255, 255, 255));
		sub.setBorderPainted(false);
		sub.setBackground(new Color(0, 128, 0));
		sub.setBounds(10, 382, 227, 42);
		panel.add(sub);
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/class_project","username","password");
					stmt=con.createStatement();
					tun=un_txt.getText();
				    tpf=pw_txt.getText();
					
				rs=stmt.executeQuery("select * from login_data");
				if(rs.next())
				{
					un=rs.getString("Username");
					pw=rs.getString("Password");
				   if((un.equals(tun))&&(pw.equals(tpf)))
					{
						//JOptionPane.showMessageDialog(null, "Correct Username");
					    setVisible(false);
						Main_Page mp=new Main_Page();
						mp.show();
					}
				else
					JOptionPane.showMessageDialog(null, "Incorrect Username");
				}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		sub.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("OIP1 (1).jpg"));
		lblNewLabel_1.setBounds(28, 149, 680, 445);
		contentPane.add(lblNewLabel_1);
	}
}
