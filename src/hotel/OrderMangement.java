package hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Tools.connect;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javafx.scene.control.TextArea;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class OrderMangement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String text="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMangement frame = new OrderMangement();
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
	public OrderMangement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("\u5168\u90E8\u8BA2\u5355");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(1,"");
			}
		});
		btnNewButton.setBounds(15, 100, 123, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u623F\u95F4\u53F7\u68C0\u7D22");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(2,text);
			}
		});
		btnNewButton_1.setBounds(15, 289, 123, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u7528\u6237\u540D\u68C0\u7D22");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(3,text);
			}
		});
		btnNewButton_2.setBounds(15, 349, 123, 29);
		contentPane.add(btnNewButton_2);

		table = new JTable(){
			public boolean isCellEditable(int row,int colum) {
				return false;
			}
		};
		updateTable(1,"");
		table.setBounds(200, 100, 700, 500);
		contentPane.add(table);

		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(200, 100, 700, 500);
		contentPane.add(scrollPane);

		JLabel label = new JLabel("\u8BA2\u5355\u4FE1\u606F\u7BA1\u7406");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setForeground(SystemColor.textHighlight);
		label.setBounds(400, 15, 437, 49);
		contentPane.add(label);

		JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根
				text=textArea.getText();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根

			}
		});
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(15, 212, 148, 37);
		contentPane.add(textArea);

		JLabel lblNewLabel = new JLabel("\u8BF7\u5728\u6B64\u5904\u8F93\u5165\u68C0\u7D22\u5173\u952E\u5B57");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(15, 168, 165, 29);
		contentPane.add(lblNewLabel);
	}
	public void  updateTable(int num,String key) {
		/*String url="jdbc:mysql://localhost:3306/ldglxt?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//链接的mysql
		Connection connection;
		java.sql.Statement con;*/
		ResultSet results=null;
		try {
			/*connection = (Connection) DriverManager.getConnection(url,"root","131400");
			con = connection.createStatement();*/
			Connection dbConn= connect.getConnection();
			Statement con = dbConn.createStatement();
			String sql="";
			if(num==1) {
				sql="SELECT * from `order`";
				results=con.executeQuery(sql);
			}
			else if(num==2) {
				sql="SELECT * from `order` where room_id='"+key+"'";
				results=con.executeQuery(sql);
			}
			else {
				sql="SELECT * from `order` where username='"+key+"'";
				results=con.executeQuery(sql);
			}
			DefaultTableModel model=new DefaultTableModel();
			model.setColumnIdentifiers(new Object[]{"定单编号","用户名","用户类型","下单时间","用户真实名","用户号码","房间编号","入住时间","入住人数","订单状态"});
			while(results.next())
			{
				String id=results.getString("id");
				String username=results.getString("username");
				String user_type=results.getString("user_type");
				String operate_time=results.getString("operate_time");
				String truename=results.getString("truename");
				String phone=results.getString("phone");
				String room_id=results.getString("room_id");
				String live_time=results.getString("live_time");
				String user_num=results.getString("user_num");
				String state=results.getString("state");
				model.addRow(new Object[]{id,username,user_type,operate_time,truename,phone,room_id,live_time,user_num,state});
			}
			table.setModel(model);
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
