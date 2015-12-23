package express.presentation.transSaleUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import express.businessLogic.userBL.User;
import express.businesslogicService.signBLService.LogInBLService;
import express.presentation.mainUI.MainUIService;
import express.vo.UserInfoSignVO;

public class transSaleMenuUI extends JPanel {

	private CardLayout card = new CardLayout();
	private MainUIService main;
	private LogInBLService login;
	private String id;
	private JPanel transsalePanel;
	// 组件：
	private JLabel username, userid;
	private JButton button_arrival = new JButton("生成到达单");
	private JButton button_shipment = new JButton("生成装车单");
	private JButton button_transfer = new JButton("生成中转单");
	private JButton button_exit = new JButton("退出");

	public transSaleMenuUI(MainUIService m,String id) {

		int base = 150;
		int width = 50;
		Font font = new Font("隶书", Font.PLAIN, 20);

		this.setLayout(null);
		this.main = m;

		transsalePanel = new JPanel();
		transsalePanel.setLayout(card);
		transsalePanel.setBounds(150, 0, 850, 700);
		transsalePanel.setOpaque(false);
		this.add(transsalePanel);

		main.setcard1(card);
		main.setpane1(transsalePanel);

		JListener listener = new JListener();
		login = new User();
		this.id = id;		
		UserInfoSignVO vo = login.getUserInfo(id);
		String name = vo.getName();
		
		username = new JLabel();
		username.setBounds(50, 50, 70, 20);
		username.setText(name);
		username.setForeground(Color.BLACK);
		username.setFont(new Font("隶书",Font.PLAIN,18));
		
		userid = new JLabel();
		userid.setBounds(40, 75, 100, 20);
		userid.setText(id);
		userid.setForeground(Color.BLACK);
		userid.setFont(new Font("隶书",Font.PLAIN,18));

		button_arrival.setBounds(0, base, 150, width);
		button_arrival.setFont(font);
		button_arrival.addMouseListener(listener);// 加监听

		button_shipment.setBounds(0, base + width, 150, width);
		button_shipment.setFont(font);
		button_shipment.addMouseListener(listener);// 加监听

		button_transfer.setBounds(0, base + 2 * width, 150, width);
		button_transfer.setFont(font);
		button_transfer.addMouseListener(listener);// 加监听

		button_exit.setBounds(0, 600, 150, 50);
		button_exit.setFont(font);
		button_exit.addMouseListener(listener);

		add(button_arrival);
		add(button_shipment);
		add(button_transfer);
		add(button_exit);
		add(username);
		add(userid);

		this.setBounds(0, 0, 1000, 700);
	}

	class JListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			if (arg0.getSource() == button_arrival) {
				main.jumpTotransSaleArrivalDocUI();
				System.out.println("应该跳转到“到达单”界面的");

			} else if (arg0.getSource() == button_shipment) {
				main.jumpTotransSaleShipmentDocUI();
				System.out.println("应该跳转到“装车单”界面的");

			}

			else if (arg0.getSource() == button_transfer) {
				main.jumpTotransSaleTransterDocUI();
				System.out.println("应该跳转到“中转单”界面的");

			} else if (arg0.getSource() == button_exit) {
				login.SignOut(id);
				main.jumpToLogInUI();
				System.out.println("应该回到登陆界面的");

			}
			updateUI();
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public void paintComponent(Graphics g) {
		ImageIcon background = new ImageIcon("picture/background.png");
		g.drawImage(background.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), this);
	}

}