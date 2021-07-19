package hotel;

import Tools.connect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class UpdateOrder extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollBar scrollBar;
	private JButton button;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateOrder frame = new UpdateOrder(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param results
	 */
	public UpdateOrder(ResultSet results) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u4FEE\u6539\u6210\u529F\uFF0C\u4F46\u4E0B\u5217\u8BA2\u5355\u53D7\u5F71\u54CD\uFF0C\u8BF7\u5904\u7406\u8BA2\u5355\uFF01");
		label.setForeground(Color.RED);
		label.setBounds(60, 30, 389, 21);
		table = new JTable() {
			public boolean isCellEditable(int row,int colum) {
				return false;
			}
		};
		updateTable(results);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				rowClicked();

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根

			}
			private void rowClicked() {
				int row=table.getSelectedRow();
				DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
				String id=(String) defaultTableModel.getValueAt(row, 0);
				String value=(String) defaultTableModel.getValueAt(row, 5);
				String username=(String) defaultTableModel.getValueAt(row, 1);
				/*String url="jdbc:mysql://localhost:3306/ldglxt?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//链接的mysql
				Connection con = null;*/
				try {
					Connection dbConn= connect.getConnection();
					Statement sta = dbConn.createStatement();
					/*con = (Connection) DriverManager.getConnection(url,"root","131400");
					Statement sta= con.createStatement();*/
					String email=null;
					ResultSet results2=null;
					results2=sta.executeQuery("SELECT email FROM userinfo where username='"+username+"'");
					while(results2.next()) {
						email=results2.getString("email");
					}
					send send=new send(email);
					send.setVisible(true);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

				try {
					/*con = (Connection) DriverManager.getConnection(url,"root","131400");
					Statement state= con.createStatement();*/
					Connection dbConn= connect.getConnection();
					Statement state = dbConn.createStatement();
					state.execute("UPDATE `order` set state='无效订单' where id='"+id+"'");
					ResultSet results1=null;
					results1=state.executeQuery("SELECT id,username,user_type,truename,phone,room_id,live_time,state from `order` where state='预定成功' and room_id='"+value+"'");
					updateTable(results1);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		table.setBounds(60, 60, 600,300);
		contentPane.add(table);


		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(66, 83, 600,300);
		contentPane.add(scrollPane);


		label_1 = new JLabel("\u4FEE\u6539\u6210\u529F\uFF0C\u4F46\u4E0B\u5217\u8BA2\u5355\u53D7\u5230\u5F71\u54CD\uFF0C\u8BF7\u5904\u7406\u8BA2\u5355\uFF01");
		label_1.setForeground(Color.RED);
		label_1.setBounds(66, 34, 390, 21);
		contentPane.add(label_1);
	}
	public void  updateTable(ResultSet results) {
		/*String url="jdbc:mysql://localhost:3306/ldglxt?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//链接的mysql
		Connection connection;*/
		try {
			Connection dbConn= connect.getConnection();
			Statement sta = dbConn.createStatement();
			//connection = (Connection) DriverManager.getConnection(url,"root","131400");
			DefaultTableModel model=new DefaultTableModel();
			//创建表头

			model.setColumnIdentifiers(new Object[]{"定单编号","用户名","用户类型","用户真实名","用户号码","房间编号","入住时间","订单状态"});
			while(results.next())
			{
				String id=results.getString("id");
				String username=results.getString("username");
				String user_type=results.getString("user_type");
				String truename=results.getString("truename");
				String phone=results.getString("phone");
				String room_id=results.getString("room_id");
				String live_time=results.getString("live_time");
				String state=results.getString("state");
				model.addRow(new Object[]{id,username,user_type,truename,phone,room_id,live_time,state});
			}
			table.setModel(model);
		}catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
