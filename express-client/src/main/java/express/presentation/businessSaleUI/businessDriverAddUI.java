package express.presentation.businessSaleUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import express.businessLogic.infoManageBL.Driver;
import express.businessLogic.infoManageBL.Vehicle;
import express.businesslogicService.businessSaleBLService.DriverBusinessSaleblService;
import express.businesslogicService.businessSaleBLService.VehicleBusinessSaleblService;
import express.vo.DriverInfoVO;
import express.vo.VehicleInfoVO;


public class businessDriverAddUI extends JDialog{
	
	private DefaultTableModel model;
	private JTextField driverIDtf,orgIDtf,nametf,datetf,peopleIDtf,cellphonetf,ddltf;
	private JComboBox gendercb;
	private JButton ok,exit;
	
	private String driverID,orgID,name,date,peopleID,cellphone,gender,ddl;
	private boolean sex;
	private int deadline;
	
	public businessDriverAddUI(DefaultTableModel tablemodel){
		
		this.setTitle("添加司机");
		this.setLayout(null);
		this.setSize(350, 400);
		this.setLocationRelativeTo(null);
		
		int leftside1 = 10;
		int leftside2 = 100;
		Font font = new Font("楷体",Font.PLAIN,18);
		Font f = new Font("仿宋",Font.PLAIN,16);
		
		
		this.model = tablemodel;
		JListener listener=new JListener();
	     
	
		JLabel driverIDlabel = new JLabel("司机编号");
		driverIDlabel.setFont(font);
		driverIDlabel.setBounds(leftside1, 5, 80, 30);
		this.add(driverIDlabel);
		
		driverIDtf = new JTextField();
		driverIDtf.setBounds(leftside2, 5, 100, 30);
		driverIDtf.setFont(f);
		this.add(driverIDtf);
		
		JLabel orgIDlabel = new JLabel("营业厅编号");
		orgIDlabel.setFont(font);
		orgIDlabel.setBounds(leftside1, 45, 90, 30);
		this.add(orgIDlabel);
		
		orgIDtf = new JTextField();
		orgIDtf.setBounds(leftside2, 45, 100, 30);
		orgIDtf.setFont(f);
		this.add(orgIDtf);
		
		
		JLabel namelabel = new JLabel("姓名");
		namelabel.setFont(font);
		namelabel.setBounds(leftside1, 85, 80, 30);
		this.add(namelabel);
		
		nametf = new JTextField();
		nametf.setBounds(leftside2, 85, 100, 30);
		nametf.setFont(f);
		this.add(nametf);
		
		JLabel datelabel = new JLabel("出生日期");
		datelabel.setFont(font);
		datelabel.setBounds(leftside1, 125, 80, 30);
		this.add(datelabel);
		
		datetf = new JTextField();
		datetf.setBounds(leftside2, 125, 100, 30);
		datetf.setFont(f);
		this.add(datetf);

		JLabel peopleIDlabel = new JLabel("身份证号");
		peopleIDlabel.setFont(font);
		peopleIDlabel.setBounds(leftside1, 165, 90, 30);
		this.add(peopleIDlabel);
		
		peopleIDtf =new JTextField();
		peopleIDtf.setBounds(leftside2, 165, 100, 30);
		peopleIDtf.setFont(f);
		this.add(peopleIDtf);
		
		JLabel cellphoneLabel=new JLabel("联系方式");
		cellphoneLabel.setFont(font);
		cellphoneLabel.setBounds(leftside1, 205, 90, 30);
		this.add(cellphoneLabel);
		
		cellphonetf=new JTextField();
		cellphonetf.setFont(font);
		cellphonetf.setBounds(leftside2, 205, 100, 30);
		this.add(cellphonetf);
		
		
		JLabel genderlabel = new JLabel("性别");
		genderlabel.setFont(font);
		genderlabel.setBounds(leftside1, 245, 90, 30);
		this.add(genderlabel);
		
	    gendercb = new JComboBox(new String[]{"男","女"});
	    gendercb.setBounds(leftside2, 245, 100, 30);
	    gendercb.setFont(f);
		this.add(gendercb);
		
		JLabel ddllabel = new JLabel("行驶证期限");
		ddllabel.setFont(font);
		ddllabel.setBounds(leftside1, 285, 90, 30);
		this.add(ddllabel);
		
		ddltf=new JTextField();
		ddltf.setBounds(leftside2, 285, 100, 30);
		ddltf.setFont(f);
		this.add(ddltf);
		
		
		ok = new JButton("确认");
		ok.setBounds(30, 325, 100, 30);
		ok.addMouseListener(listener);
		ok.setFont(font);
		this.add(ok);
		
		
		
		
		exit = new JButton("取消");
		exit.setBounds(180, 325, 100, 30);
		exit.addMouseListener(listener);
		exit.setFont(font);
		this.add(exit);
		
    }

	private class JListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			   if (e.getSource()==ok){
				   driverID=driverIDtf.getText();
				   orgID=orgIDtf.getText();
				   name=nametf.getText();
				   date=datetf.getText();
				   peopleID=peopleIDtf.getText();
				   cellphone=cellphonetf.getText();
				   gender=gendercb.getSelectedItem().toString();
				   ddl=ddltf.getText();
				   Object[] values={false,driverID,orgID,name,date,peopleID,cellphone,gender,ddl,"<HTML><U>修改</U></HTML>"};
					
				   
				   if (gender=="男"){
					   sex=true;
				   }
				   else{
					   sex=false;
				   }
				   deadline=Integer.parseInt(ddl);
				   
				   
				   DriverInfoVO vo=new DriverInfoVO(driverID,orgID,name,date,peopleID,cellphone,sex,deadline);
				   DriverBusinessSaleblService dbs=new Driver();
					
					
					if (!dbs.isDriverIDAvailable(driverID)){//这边的检查有问题，和逻辑层不匹配
						dbs.addDriverInfo(vo);
						
						model.addRow(values);
						JOptionPane.showMessageDialog(null, "添加成功", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						dbs.endManage();
					}
					else {
						JOptionPane.showMessageDialog(null, "信息有误，添加失败", "提示",
								JOptionPane.ERROR_MESSAGE);
					}
				   
			   }
			   dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}