package hotel;

import Tools.connect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Manger extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manger frame = new Manger();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Manger() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Connection dbConn=connect.getConnection();
		Statement state = dbConn.createStatement();
		/*String url="jdbc:mysql://127.0.0.1:3306/hotel?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";//���ӵ�mysql
	    Connection connection = (Connection) DriverManager.getConnection(url,"root","");*/
		DefaultTableModel model=new DefaultTableModel();
		//������ͷ
		model.setColumnIdentifiers(new Object[]{"房间编号","类型编号","固定服务号","楼层","状态"});
		ResultSet results=null;
	     //Statement state= connection.createStatement();
	     results=state.executeQuery("SELECT * from roominfo");
		while(results.next())
		{
		String id=results.getString("id");
		String typeid=results.getString("type_id");
		String phone=results.getString("phone");
		String floor=results.getString("floor");
		String state1=results.getString("state");
		model.addRow(new Object[]{id,typeid,phone,floor,state1});
		}
		table = new JTable(model) {
			public boolean isCellEditable(int row,int colum) {
			    return false;
			}
		};
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
			
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				updateTable();	
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				   rowClicked();
				// TODO �Զ����ɵķ������
			}
			private void rowClicked() {
				int row=table.getSelectedRow();
				DefaultTableModel defaultTableModel=(DefaultTableModel) table.getModel();
				String value=(String) defaultTableModel.getValueAt(row, 0);
				UpdateRoomInfo update=new UpdateRoomInfo(value);
				update.setVisible(true);	
			}
		});
		table.setBounds(43, 94, 518, 465);
		contentPane.add(table);
		
		
		
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(15, 58, 704, 544);
		contentPane.add(scrollPane);
		
		JLabel label = new JLabel("\u623F\u95F4\u4FE1\u606F\u7BA1\u7406");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("宋体", Font.PLAIN, 28));
		label.setBounds(281, 11, 189, 28);
		contentPane.add(label);
		

	}	
	public void updateTable() {
		/*String url="jdbc:mysql://localhost:3306/ldglxt?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//���ӵ�mysql
	    Connection connection;*/
		try {
			//connection = (Connection) DriverManager.getConnection(url,"root","131400");
			DefaultTableModel model=new DefaultTableModel();
			//������ͷ
			model.setColumnIdentifiers(new Object[]{"房间编号","类型编号","固定服务号","楼层","状态"});
			ResultSet results=null;
			Connection dbConn=connect.getConnection();
			Statement state = dbConn.createStatement();
		     results=state.executeQuery("SELECT * from roominfo ");
			while(results.next())
			{
			String id=results.getString("id");
			String typeid=results.getString("type_id");
			String phone=results.getString("phone");
			String floor=results.getString("floor");
			String state1=results.getString("state");
			model.addRow(new Object[]{id,typeid,phone,floor,state1});
			}
			table.setModel(model);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}//�����쳣
	}
}
