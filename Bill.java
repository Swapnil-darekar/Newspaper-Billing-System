import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import java.text.*;

class Bill extends JFrame implements ActionListener
{
	Connection cn;
	Statement stm;
	ResultSet rs,rs1,rs2;
	PreparedStatement prstm;
	String sql;
    int flg=0; 
	double total_amt=0.0,total=0.0;
	DateFormat df;
	java.util.Date d1,d2;
	String ds1,ds2;
	DateButton db1,db2;
	JTable tab;
	JScrollPane jsp;    int i=0;
 
		JLabel l1,l2,l3,l4,l5,l6,l7;
		Font fnt;
		JButton b1,b3,b4,b5;
		JComboBox cb1,cb2,cb3;
		int r_cnt=0;
	Bill()
	{
		super("Bill");
		setSize(700,500);
		setLayout(null);
		setLocation(200,10);
		
		
		cb1=new JComboBox();
		cb2 = new JComboBox();
		cb3 = new JComboBox();

			cb1.addItem("<Select Vendor>");
			cb2.addItem("<Select Newspaper>");
			cb3.addItem("<Select Section>");

		getContentPane().setBackground(Color.pink);
				df=new SimpleDateFormat("yyyy-MM-dd");
		d1=new java.util.Date();
		d2=new java.util.Date();
		ds1=new String(df.format(d1));
		d2.setMonth(d2.getMonth()-1);
		d2.setDate(d2.getDate()+1);
		ds2=new String(df.format(d2));	

		
		
		try{
			cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
		stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		//	disp();
			}
			catch(Exception e)
			{
			
				e.printStackTrace();
				
			}
			
		l1=new JLabel("Start Date :");
		l3=new JLabel("End Date :");
		l4=new JLabel("Total :");
		l5=new JLabel("Select Vendor :");
		l6=new JLabel("Select Newspaper :");
		l7=new JLabel("Select Section:");
		
		
		db1 = new DateButton();
		db2 = new DateButton();
	
		l2=new JLabel("Generate Bill Monthly");
		add(l2);
	//	fnt = new Font(("Lucida Calligraphy",Font.ITALIC,30));
		
		l2.setFont(new Font("Lucida Calligraphy",Font.ITALIC,30));
	
		
		//ImageIcon ii = new Im0ageIcon("Screenshot (21).PNG");
		//l7=new JLabel(ii);
		
		b1=new JButton("ALL");
		b3=new JButton("Back");
		b4=new JButton("Exit");
		b5=new JButton("Clear");
		
		l2.setBounds(180,10,900,70);
	
		l1.setBounds(100,95,80,30);
		db1.setBounds(190,100,120,20);
	
		l3.setBounds(330,95,100,30);
		db2.setBounds(410,100,120,20);
		b1.setBounds(560,100,100,20);
	
			
		l5.setBounds(100,140,90,30);
		cb1.setBounds(225,140,190,20);

		l6.setBounds(100,175,120,30);
		cb2.setBounds(230,175,150,20);

		l7.setBounds(100,210,120,30);
		cb3.setBounds(230,210,140,20);

		b3.setBounds(110,250,80,20);
		b4.setBounds(205,250,80,20);
		b5.setBounds(300,250,80,20);
		
		add(l1);	add(l3);	
		add(l6);	add(l7);	
		add(l5);	add(l2);
		add(b1);		add(b3); add(b5);
		add(b4);
		add(cb1);		
		add(cb2);		
		add(cb3);		
		add(db1);		
		add(db2);

				vendrr();
				newss();
				sectionn();
		
		
		b1.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		cb1.addActionListener(this);
		cb2.addActionListener(this);
		cb3.addActionListener(this);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	public void vendrr()
	{
		try{
		cb1.removeAllItems();
		rs = stm.executeQuery("select vname from vendor");
			while(rs.next())
			{
				String vname  = rs.getString("vname");
				cb1.addItem(vname);
			}
		}catch(Exception ee)
		{
		
		}
	}
	public void newss()
	{
		try{
		cb2.removeAllItems();
		rs = stm.executeQuery("select newspaper_name from News_paper");
			while(rs.next())
			{
				String newspaper_name  = rs.getString("newspaper_name");
				cb2.addItem(newspaper_name);
			}
		}catch(Exception e10)
		{
			
		}
		
	}
	
	public void sectionn()
	{
		try{
		cb3.removeAllItems();
		rs = stm.executeQuery("select sname from section");
			while(rs.next())
			{
				String sname1  = rs.getString("sname");
				cb3.addItem(sname1);
			}
		}catch(Exception e10)
		{
			
		}
		
	}
	
	public void actionPerformed(ActionEvent e)
	{	try{
			if(e.getSource()==b1)
			{
			try{	
					if(db1.getText().length()==0 || db2.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null,"**** Please Select Correct Date !!!!! ");
					}
					else if(db1.getText().equals(db2.getText()))
					{
						JOptionPane.showMessageDialog(null,"**** Please Select Correct Date !!!!! ");
					}
					else
					{
					try{
						rs=stm.executeQuery("select sum(total_amt) from purches_master where bill_date between '"+db1.getText()+"' and '"+db2.getText()+"'");
						rs.next();
						total_amt=rs.getDouble(1);
						updateTableAll("select count(*) from purches_master where bill_date between '"+db1.getText()+"' and '"+db2.getText()+"'","select purches_master.bill_no,purches_master.vid,vendor.vname,purches_master.total_amt,purches_master.bill_date from purches_master inner join vendor on purches_master.vid where purches_master.vid=vendor.vid and purches_master.bill_date between '"+db1.getText()+"' and '"+db2.getText()+"'");
						}
						
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null,"error "+exp);
				}
					}
				}
				catch(Exception ee)
				{
				}
			}
			if(e.getSource()==cb1)
			{
				int v_id=1;
				try{
					if(cb1.getSelectedItem()!=null)
					{
						
						rs=stm.executeQuery("select vid from vendor where vname='"+cb1.getSelectedItem()+"'");
						rs.next();
						v_id=rs.getInt(1);
						
						rs=stm.executeQuery("select sum(total_amt) from purches_master where bill_date between '"+ds2+"' and '"+ds1+"' and vid="+v_id);
						rs.next();
						total_amt=rs.getDouble(1);
						updateTableAll("select count(*) from purches_master where vid="+v_id+" and bill_date between '"+ds2+"' and '"+ds1+"'","select purches_master.bill_no,purches_master.vid,vendor.vname,purches_master.total_amt,purches_master.bill_date from purches_master inner join vendor on purches_master.vid where purches_master.vid=vendor.vid and purches_master.vid="+v_id+" and purches_master.bill_date between '"+ds2+"' and '"+ds1+"'");
						
					}
				}
				catch(Exception exp)
				{
					//JOptionPane.showMessageDialog(null,"error "+exp);
				}
				
			}
			
			if(e.getSource()==cb2)
			{
				int n_id=1;
				try{
					if(cb3.getSelectedItem()!=null)
					{
						
						rs=stm.executeQuery("select newspaper_id from News_paper where newspaper_name='"+cb2.getSelectedItem()+"'");
						rs.next();
						n_id=rs.getInt(1);
						
						rs=stm.executeQuery("select sum(total) from transaction inner join purches_master on transaction.bill_no = purches_master.bill_no where purches_master.bill_date between '"+ds2+"' and '"+ds1+"' and newspaper_id="+n_id);
						rs.next();
						total=rs.getDouble(1);
		
						updateTableAll2("select count(*) from transaction inner join purches_master on transaction.bill_no = purches_master.bill_no where newspaper_id="+n_id+" and bill_date between '"+ds2+"' and '"+ds1+"'","select transaction.bill_no,transaction.newspaper_id,News_paper.newspaper_name,transaction.total,purches_master.bill_date from ((transaction inner join News_paper on transaction.newspaper_id=News_paper.newspaper_id) inner join purches_master on transaction.bill_no=purches_master.bill_no) where transaction.newspaper_id= "+n_id+" and purches_master.bill_date between '"+ds2+"' and '"+ds1+"'");
						
					}
				}
				catch(Exception exp)
				{
					//JOptionPane.showMessageDialog(null,"error "+exp);
				}
				
			}
			
			if(e.getSource()==cb3)
			{
				int s_id=1;
				try{
					if(cb3.getSelectedItem()!=null)
					{
						
						rs=stm.executeQuery("select sid from section where sname='"+cb3.getSelectedItem()+"'");
						rs.next();
						s_id=rs.getInt(1);
						
						rs=stm.executeQuery("select sum(total_amt) from purches_master where bill_date between '"+ds2+"' and '"+ds1+"' and sid="+s_id);
						rs.next();
						total_amt=rs.getDouble(1);
		
						updateTableAll("select count(*) from purches_master where sid="+s_id+" and bill_date between '"+ds2+"' and '"+ds1+"'","select purches_master.bill_no,purches_master.sid,section.sname,purches_master.total_amt,purches_master.bill_date from purches_master inner join section on purches_master.sid where purches_master.sid=section.sid and purches_master.sid="+s_id+" and purches_master.bill_date between '"+ds2+"' and '"+ds1+"'");
						
					}
				}
				catch(Exception exp)
				{
					//JOptionPane.showMessageDialog(null,"error "+exp);
				}
				
			}
			if(e.getSource()==b3)
			{
				setVisible(false);
			}
			
			if(e.getSource()==b4)
			{
					
			System.exit(0);
			}
			if(e.getSource()==b5)
			{
			cb1.setSelectedIndex(-1);
			cb2.setSelectedIndex(-1);
			cb3.setSelectedIndex(-1);
			}
		}
		catch(Exception ee)
		{
		}
	}
	void updateTableAll(String s1,String s2)
    {
        try
        {
            rs = stm.executeQuery(s1);
            rs.first();
            int rowcnt = rs.getInt(1);
	        String data[][] = new String[rowcnt][5];
			
			rs=stm.executeQuery(s2);
            rs.first();

			for (int i=0;i<rowcnt;i++)
            {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                rs.next();
            }
			//showreport sp=new showreport(data,rowcnt,total_amt);								
			//sp.setVisible(true);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
	void updateTableAll1(String s1,String s2)
    {
        try
        {
            rs = stm.executeQuery(s1);
            rs.first();
            int rowcnt = rs.getInt(1);
	        String data[][] = new String[rowcnt][5];
			
			rs=stm.executeQuery(s2);
            rs.first();

			for (int i=0;i<rowcnt;i++)
            {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                rs.next();
            }
			//showreport1 sp1=new showreport1(data,rowcnt,total_amt);								
			//sp1.setVisible(true);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
	
	void updateTableAll2(String s1,String s2)
    {
        try
        {
            rs = stm.executeQuery(s1);
            rs.first();
            int rowcnt = rs.getInt(1);
			
			String data[][] = new String[rowcnt][5];
			
			rs=stm.executeQuery(s2);
            rs.first();

			for (int i=0;i<rowcnt;i++)
            {
                data[i][0] = rs.getString(1);
                data[i][1] = rs.getString(2);
                data[i][2] = rs.getString(3);
                data[i][3] = rs.getString(4);
                data[i][4] = rs.getString(5);
                rs.next();
            }
			//showreport2 sp2=new showreport2(data,rowcnt,total);								
			//sp2.setVisible(true);
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
	
	public static void main(String args[])
	{
		new Bill();
	}
}