import java.sql.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.text.*;

class Vendor1 extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4;
	JButton b1,b2,b3,b4,b5,b6;
	JFrame f;
	String colHeads[]={"V ID","V Name","Address","Mobile No"};
    Object data[][]={{"","","",""}};
    JScrollPane jsp;    int i=0;
	JTable tab;
	int r_cnt=0;	
	Font fnt;
	 	Connection cn;
     	PreparedStatement prstm;
     	Statement stm;
     	ResultSet rs;
     	String sql;
     	int flg=0;
	
	Vendor1()
	{
		super("Phone Book");
     	setLocation(200,200);
     	setLayout(null);
     	setSize(1000,500);
	t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextField();
		
		l1=new JLabel("Vendor Entry Form");
		l2=new JLabel("Vendor ID :");
		l3=new JLabel("Vendor Name :");
		l4=new JLabel("Address :");
		l5=new JLabel("Contact No :");
		
		b1=new JButton("Insert");
		b2=new JButton("Delete");
		b3=new JButton("Clear");
		b4=new JButton("Update");
		b5=new JButton("Back");
		b6=new JButton("Exit");
		
		l1.setBounds(200,50,300,50);
	
		getContentPane().setBackground(Color.pink);

		l1.setBounds(300,30,500,50);
		Font myfont = new Font("Monotype Corsiva",Font.ITALIC,45);
		l1.setFont(myfont);
		l1.setForeground(Color.cyan);		
				
		l2.setBounds(50,100,150,50);
		t1.setBounds(190,110,150,30);
		l3.setBounds(50,160,150,50);
		t2.setBounds(190,170,150,30);
		l4.setBounds(50,210,150,50);
		t3.setBounds(190,220,150,30);
		l5.setBounds(50,250,150,50);
		t4.setBounds(190,260,150,30);
		
		b1.setBounds(50,300,100,30);
		b2.setBounds(150,300,100,30);
		b3.setBounds(250,300,100,30);
		b4.setBounds(50,340,100,30);
		b5.setBounds(150,340,100,30);
		b6.setBounds(250,340,100,30);
		
		
		add(l1);	add(l2);	
		add(l3);	add(l4);  add(l5);
		add(t1);	add(t2);	add(t3);
		add(t4);
		add(b1);	add(b2);	add(b3);
		add(b4);	add(b5);	add(b6);
		
		b1.setMnemonic(KeyEvent.VK_I);
		b1.setToolTipText("this is save button");
		b2.setMnemonic(KeyEvent.VK_D);
		b2.setToolTipText("this is Delete button");
		b3.setMnemonic(KeyEvent.VK_C);
		b3.setToolTipText("this is Clear button");
		b4.setMnemonic(KeyEvent.VK_U);
		b4.setToolTipText("this is Edit button");
		b5.setMnemonic(KeyEvent.VK_B);
		b5.setToolTipText("this is Back button");
		b6.setMnemonic(KeyEvent.VK_E);
		b6.setToolTipText("this is Exit button");
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
  	try
      	{
			cn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
			stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			table_disp();
		}
       	catch(Exception e)
       	{
         	e.printStackTrace();
       	}
       		b1.addActionListener(this);
     		b2.addActionListener(this);
     		b3.addActionListener(this);
     		b4.addActionListener(this);
     		b5.addActionListener(this);
     		b6.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
	   		if(e.getSource()==b1)
     		{	
				try{
						if(t1.getText().length()==0 || t2.getText().length()==0
						   || t3.getText().length()==0 ||t4.getText().length()==0 )
						JOptionPane.showMessageDialog(null, "All Fields are Neccessary !!!");
						else{

						sql = "insert into vendor values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+t4.getText()+"')";
						prstm=cn.prepareStatement(sql);
						prstm.execute();
						JOptionPane.showMessageDialog(null,"*** Insert Done ***");
						table_disp();
					} 
				}
				catch(Exception ee)
				{
				JOptionPane.showMessageDialog(null,"*** Error In Insert Records ***");
				
				}
			}
			if(e.getSource()==b2)
			{	
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Would You Like to Delete your Section ?","Warning",dialogButton);

				if (dialogButton == JOptionPane.YES_OPTION)
				try{
				if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Please Enter Vendor ID !!!");
				  // if( t2.getText().length()==0|| t1.getText().length()==0 ||r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false)
				//JOptionPane.showMessageDialog(null, "Please Enter Newspaper ID !!!");
			
				}
				else 
				{
					sql = "delete from vendor where vid='"+t1.getText()+"'";
					prstm=cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
					sql = "delete from purches_master where vid='"+t1.getText()+"'";
					prstm=cn.prepareStatement(sql);
					prstm.execute();
					prstm.close();
					
					JOptionPane.showMessageDialog(null,"*** Delete Done ***");
					flg=0;
					table_disp();
					}
				}catch(Exception eee)
				{
					JOptionPane.showMessageDialog(null,"*** Error In Delete Records***");
				}
			}
			if(e.getSource()==b3)
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			if(e.getSource()==b4)
   			{
				try{
						if(t1.getText().length()==0 || t2.getText().length()==0
						|| t3.getText().length()==0 ||t4.getText().length()==0 )
					JOptionPane.showMessageDialog(null, "All Fields are Neccessary !!!");
					else
					{
						sql = "update vendor set vname='"+t2.getText()+"',vadd='"+t3.getText()+"',pno='"+t4.getText()+"'where vid='"+t1.getText()+"'";
						prstm=cn.prepareStatement(sql);
						prstm.execute();
						prstm.close();
						JOptionPane.showMessageDialog(null,"*** Update Done ***");
						flg=0;
						table_disp();
					}
				}
				catch(Exception ee)
				{
				JOptionPane.showMessageDialog(null,"*** Error In Update Records ***");
				
				}
	
				}
			if(e.getSource()==b5)
    		{
				setVisible(false);
			}
			if(e.getSource()==b6)
  			{
				System.exit(0);
			}
     	
		}	
	 
		catch(Exception ee)
       	{
          		ee.printStackTrace();
				JOptionPane.showMessageDialog(null, "Please Enter Correct Information !!!");
       	}
	}	
	public void table_disp()
	{
		try
		{
			rs=stm.executeQuery("select count(*) from vendor");
			rs.next();
			r_cnt=rs.getInt(1);
			data=new String[r_cnt+1][4];
			rs=stm.executeQuery("select * from vendor order by vid");
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				i++;
			}
				
			tab = new JTable(data, colHeads);

		// Resize the table coloumns
        DefaultTableColumnModel colModel=(DefaultTableColumnModel)tab.getColumnModel();
    	TableColumn col=colModel.getColumn(1);
    	col=colModel.getColumn(0);        col.setPreferredWidth(100);
        col=colModel.getColumn(1);        col.setPreferredWidth(100);
    	col=colModel.getColumn(2);        col.setPreferredWidth(100);
    	col=colModel.getColumn(3);        col.setPreferredWidth(100);
	        
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;	
        jsp = new JScrollPane(tab,v,h); 
		add(jsp);
        jsp.setBounds(500,140,480,320);
 
		}
		catch(Exception e5)
		{
			e5.printStackTrace();
		}
     
	}
	public static void main(String args[])
	{
		new Vendor1();
	}
}
