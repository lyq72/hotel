package hotel;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Tools.connect;

import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class UpdateRoomInfo extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateRoomInfo frame = new UpdateRoomInfo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param value 
	 * @param value 
	 */
	public UpdateRoomInfo(String value) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 400, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JRadioButton RadioButton1 = new JRadioButton("\u53EF\u4F9B");
		RadioButton1.setBounds(53, 100, 177, 29);
		contentPane.add(RadioButton1);
		
		JRadioButton RadioButton2 = new JRadioButton("\u505C\u7528");
		RadioButton2.setBounds(53, 154, 177, 29);
		contentPane.add(RadioButton2);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		buttonGroup.add(RadioButton1);
		buttonGroup.add(RadioButton2);
	
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u623F\u95F4\u72B6\u6001");
		label.setForeground(SystemColor.textHighlight);
		label.setBounds(57, 45, 148, 37);
		contentPane.add(label);
		
		JButton button = new JButton("\u786E\u5B9A\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String state=RadioButton1.isSelected()?RadioButton1.getText():RadioButton2.getText();
				/*Statement con;
				String url="jdbc:mysql://localhost:3306/ldglxt?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//���ӵ�mysql*/
			    try {
					/*Connection connection = (Connection) DriverManager.getConnection(url,"root","131400");
					con = connection.createStatement();*/
					Connection dbConn= connect.getConnection();
					Statement con = dbConn.createStatement();
					con.execute("UPDATE roominfo set state='"+state+"' where id='"+value+"'");
					JOptionPane.showMessageDialog(null, "修改成功", "提示框",JOptionPane.PLAIN_MESSAGE);
					ResultSet results=null;
					ResultSet results1=null;
					results1=con.executeQuery("SELECT count(*) from `order` where  state='预定成功' and room_id='"+value+"'");
					while(results1.next()) {
					if(results1.getInt("count(*)")!=0){
						con = dbConn.createStatement();
						results=con.executeQuery("SELECT id,username,user_type,truename,phone,room_id,live_time,state from `order` where state='预定成功' and room_id='"+value+"'");
						UpdateOrder order=new UpdateOrder(results);
						order.setVisible(true);
				    }
					}

			    }catch (SQLException e1) {

					e1.printStackTrace();
				}
			}

		});
		button.setBounds(53, 200, 105, 29);
		contentPane.add(button);
	}
}
