package hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class send extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					send frame = new send(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param email 
	 */
	public send(String email) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BE5\u8BA2\u5355\u5DF2\u7F6E\u4E3A\u65E0\u6548\u8BA2\u5355\uFF0C\u53D1\u90AE\u7BB1\u544A\u77E5\u7528\u6237\uFF0C\u5E76\u6309\u539F\u8DEF\u9000\u6B3E");
		label.setForeground(SystemColor.textHighlight);
		label.setBounds(15, 0, 398, 100);
		contentPane.add(label);
		
		JButton button = new JButton("\u53D1\u90AE\u7BB1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sendMailMessage(email))
				JOptionPane.showMessageDialog(null, "邮箱已发送！", "提示框",JOptionPane.PLAIN_MESSAGE);
			}
		});
		button.setIcon(new ImageIcon(send.class.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")));
		button.setBounds(15, 105, 123, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u539F\u8DEF\u9000\u6B3E ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "已退款", "提示框",JOptionPane.PLAIN_MESSAGE);
			}
		});
		button_1.setIcon(new ImageIcon(send.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		button_1.setBounds(204, 105, 143, 29);
		contentPane.add(button_1);
	}
	public boolean sendMailMessage(String tEmail) {
        try {
            HtmlEmail email = new HtmlEmail();//���ø���
            email.setHostName("smtp.126.com");//��Ҫ�޸ģ�126����Ϊsmtp.126.com,163����Ϊ163.smtp.com��QQΪsmtp.qq.com
            email.setCharset("UTF-8");
            String emailaddress=tEmail;
            email.addTo(emailaddress);// �ռ���ַ
            email.setFrom("woailyq0329@126.com", "hotel");//�˴��������ַ���û���,�û�������������д

            email.setAuthentication("woailyq0329@126.com", "IIWPRZYFYVEVBQVH");//�˴���д�����ַ�Ϳͻ�����Ȩ��

			email.setSubject("订单异常");//此处填写邮件名，邮件名可任意填写
			email.setMsg("尊敬的用户您好,您预定的房间暂时需要修整,该订单已经取消并按原路退款,非常抱歉！");//此处填写邮件内容

            email.send();
            return true;

        } catch (EmailException e) {
			System.err.println("邮件发送失败");
			e.printStackTrace();
			return false;
        }
    }
}
