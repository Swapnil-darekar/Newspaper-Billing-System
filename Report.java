import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class Report extends JFrame implements ActionListener//,TextListener
{
 JLabel l1,l4,l5,l6,l7,ldate,lldate,l_head;
 JTextField tfrmdate,tduedate;
 Date date,d1;
 DateFormat df;
 JPanel p1,p2,p3,p4,p5;
 int i;
 DateButton db1,db2,db3; 
 JButton b1,b2,b3;
 ButtonGroup bg1;
 ButtonGroup bg2;
 Date d2,d3;
 DateFormat df1,df2;
 String ds1,ds2,ds3;
 float amt1=0;
 
 JRadioButton rb[]=new JRadioButton[4];
 JRadioButton rb1[]=new JRadioButton[2];
 
 
 Connection con=null;
 Statement stm,stm1;
 PreparedStatement pstm;
 ResultSet rs,rs1,rs2,rs3;
    	
 Report()
 {
  super("Report");
  setSize(550,600);
  setLayout(null);
  setVisible(true);
  setLocation(250,70);
  setResizable(false);
  Color cl5 = new Color(0,0,200);
		Color cl6 = new Color(255,255,170);
		
  l_head = new JLabel("REPORT",JLabel.CENTER); 
		l_head.setForeground(Color.red);
		l_head.setBackground(cl6);
		l_head.setOpaque(true);
  
		l_head.setFont(new Font("sansserif",Font.BOLD,20));
		l_head.setBorder(BorderFactory.createLineBorder(cl5,5));

		add(l_head);
		l_head.setBounds(0,0,545,40);
		
	getContentPane().setBackground(Color.pink);
  d2=new Date();
  d3=new Date();
  
  
  df=new SimpleDateFormat("yyyy-MM-dd");
  ds1=new String(df.format(d2));
  //System.out.println(""+d2.getYear());
			
  d3.setMonth(d2.getMonth()-1);
  d3.setDate(d2.getDate()+1);
  ds2=new String(df.format(d3));	

  
  
  d3.setYear(d2.getYear()-1);
  //System.out.println(""+d2.getYear());
  d3.setMonth(d2.getMonth());
  d3.setDate(d2.getDate()+1);
  ds3=new String(df.format(d3));  
  
  
  l1=new JLabel("Report");		
  l4=new JLabel("Select Type Of Report");
  l5=new JLabel("Select Date");
  l6=new JLabel("From Date:");
  l7=new JLabel("To Date:");
  
  lldate=new JLabel("Select Date");
  ldate=new JLabel("Date:");
  
  
  
  b1=new JButton("View");
   b1.setMnemonic('V');
  
  b2=new JButton("Clear");
    b2.setMnemonic('C');
  
  b3=new JButton("Back");
    b3.setMnemonic('B');
  
  
  rb[0]=new JRadioButton("Daily");
  rb[1]=new JRadioButton("Monthly");
  rb[2]=new JRadioButton("Yearly");
  rb[3]=new JRadioButton("Arbitory");
  bg1=new ButtonGroup();	
  
  rb1[0]=new JRadioButton("Farmer Bill");
  rb1[1]=new JRadioButton("Patkari Salary");
  bg2=new ButtonGroup();
  bg2.add(rb1[0]);
  bg2.add(rb1[1]);
  
  db1=new DateButton();
  db2=new DateButton();
  db3=new DateButton();
  
  tfrmdate=new JTextField(50);
  tfrmdate.setEditable(false);
  
  tduedate=new JTextField(50);
  tduedate.setEditable(false);
  
  df=new SimpleDateFormat("yyyy-MM-dd");
  
  p1=new JPanel();
  p2=new JPanel();
  p3=new JPanel();
  p4=new JPanel();
  p5=new JPanel();
  
  
  
  
  
  p1.setBorder(BorderFactory.createRaisedBevelBorder());		 
  p2.setBorder(BorderFactory.createRaisedBevelBorder());		 
  p3.setBorder(BorderFactory.createRaisedBevelBorder());		 
  p4.setBorder(BorderFactory.createRaisedBevelBorder());
  p5.setBorder(BorderFactory.createRaisedBevelBorder());
  
  add(p1);
  add(p2);
  add(p3);  
  add(p4);
  add(p5);
  
  add(l1);
  
  p1.add(rb1[0]);
  p1.add(rb1[1]);
  
  p1.setBackground(Color.LIGHT_GRAY);
  p2.setBackground(Color.LIGHT_GRAY);
  p3.setBackground(Color.LIGHT_GRAY);
  p4.setBackground(Color.LIGHT_GRAY);
  p5.setBackground(Color.LIGHT_GRAY);
 
  p2.add(l4);
  p2.add(rb[0]);
  p2.add(rb[1]);
  p2.add(rb[2]);
  p2.add(rb[3]);	
  
  bg1.add(rb[0]);		 
  bg1.add(rb[1]);		 
  bg1.add(rb[2]);		 
  bg1.add(rb[3]);		 
  
  
  p3.add(l5);
  p3.add(l6);
  p3.add(l7);
  p3.add(db1);
  p3.add(db2);
  p3.add(tfrmdate);
  p3.add(tduedate);
    
  p4.add(lldate);
  p4.add(ldate);
  p4.add(db3);
  
  p5.add(b1);
  //b1.setBackground(new Color(100,255,255));
  p5.add(b2);
 // b2.setBackground(new Color(100,255,255));
  p5.add(b3);
 // b3.setBackground(new Color(100,255,255));
  
  p1.setLayout(null);
  p2.setLayout(null);
  p3.setLayout(null);
  p4.setLayout(null);
  p5.setLayout(null);
  
  
  
  l1.setBounds(150,10,200,30);
  
  p1.setBounds(60,50,400,80);
  rb1[0].setBounds(70,30,100,30);
  rb1[1].setBounds(220,30,130,30);
  
  p2.setBounds(60,150,400,150);
  l4.setBounds(50,20,260,30);
  rb[0].setBounds(70,60,100,20);
  rb[1].setBounds(220,60,100,20);
  rb[2].setBounds(70,100,100,20);
  rb[3].setBounds(220,100,100,20);
  
  p3.setBounds(60,320,400,150);
  l5.setBounds(110,20,150,40);
  l6.setBounds(20,60,140,30);
  l7.setBounds(20,100,140,30);
  db1.setBounds(210,60,100,30);
  db2.setBounds(210,100,100,30);
  
  tfrmdate.setBounds(210,60,100,30);
  tfrmdate.setVisible(false);
  
  tduedate.setBounds(210,100,100,30);
  tduedate.setVisible(false);
  
  p4.setBounds(60,320,400,120);
  lldate.setBounds(120,20,150,40);
  ldate.setBounds(70,80,120,20);
  db3.setBounds(210,80,100,20);
  
  p5.setBounds(60,480,400,50);
  b1.setBounds(20,10,80,30);
  b2.setBounds(140,10,80,30);
  b3.setBounds(260,10,80,30);
  
  l1.setFont(new Font("Lucida Calligraphy",Font.BOLD,22));		 
  l4.setFont(new Font("Lucida Calligraphy",Font.BOLD,20));		 
  l5.setFont(new Font("Lucida Calligraphy",Font.BOLD,20));		 		 
  lldate.setFont(new Font("Lucida Calligraphy",Font.BOLD,20));		 		 
  l6.setFont(new Font("Lucida Calligraphy",Font.BOLD,15));		 
  l7.setFont(new Font("Lucida Calligraphy",Font.BOLD,15));		 		 
  ldate.setFont(new Font("Lucida Calligraphy",Font.BOLD,20));
  
  b1.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
  b2.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
  b3.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
		 
  p3.setVisible(false);
  p4.setVisible(false);
  
   try
     {
	  con= DriverManager.getConnection("jdbc:mysql://localhost:3307/addon2","root","");
	  stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
	 }
	  catch(Exception e1)
	  {
	   JOptionPane.showMessageDialog(null,"error :"+e1);
	  }
  
  b1.addActionListener(this);
  b2.addActionListener(this);
  b3.addActionListener(this);  
  
  rb1[0].addActionListener(this);  
  rb1[1].addActionListener(this); 
  
  rb[0].addActionListener(this);  
  rb[1].addActionListener(this);  
  rb[2].addActionListener(this);  
  rb[3].addActionListener(this);  
    
  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
 } 
 
 /*public void textValueChanged(TextEvent t)
  {
    for(int i=0;i<tmid.getText().length();i++)
               {
                   if(!Character.isDigit(tmid.getText().charAt(i)))
                   {
                       JOptionPane.showMessageDialog(null,"Only Numbers Allowed","warning",JOptionPane.ERROR_MESSAGE);
                       tmid.setText("");
                       tmid.requestFocus();
                   }
               } 
  
  }*/

 public void actionPerformed(ActionEvent ae)
   {
     if(ae.getSource()==b1)
	   if(db1.getText().length()==0 || db2.getText().length()==0 || (rb1[0].isSelected()==false && rb1[1].isSelected()==false && rb[0].isSelected()==false && rb[1].isSelected()==false && rb[2].isSelected()==false && rb[3].isSelected()==false))
	    {
           JOptionPane.showMessageDialog(null,"Please Fill Up All Information");
		}
   
     if(ae.getSource()==rb[0])
	 {
	  p3.setVisible(false);
	  p4.setVisible(true);
	 }
	 
	 if(ae.getSource()==rb[1])
	 {
	  p4.setVisible(false);
	  p3.setVisible(true);
	  
	  db1.setVisible(false);
      db2.setVisible(false);	  
	  tfrmdate.setVisible(true);
      tduedate.setVisible(true);
	  
      tfrmdate.setText(ds1);
      tduedate.setText(ds2);	  
	 }
	 
	 if(ae.getSource()==rb[2])
	 {
	  p4.setVisible(false);
	  p3.setVisible(true);
	  
	  db1.setVisible(false);
      db2.setVisible(false);	  
	  tfrmdate.setVisible(true);
      tduedate.setVisible(true);
	  
      tfrmdate.setText(ds1);
      tduedate.setText(ds3);	  
	 }
	 
	if(ae.getSource()==rb[3])
	{
	   p4.setVisible(false);
	  p3.setVisible(true);
	  
	  tfrmdate.setVisible(false);
      tduedate.setVisible(false);
	  db1.setVisible(true);
      db2.setVisible(true);	  
	}
	if(ae.getSource()==b1 && rb[0].isSelected() && rb1[0].isSelected()) 
	{
		if(db3.getText().length()==0)
		{
			JOptionPane.showMessageDialog(null,"Enter Valid date","warning",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try{
				rs1=stm.executeQuery("select sum(paid_amt) from farmer_bill where date='"+db3.getText()+"'");
				rs1.first();
				amt1 = rs1.getFloat(1);
			
				rs=stm.executeQuery("select count(*) from farmer_bill where date='"+db3.getText()+"'");
				rs.first();
				int r_cnt=rs.getInt(1);
				
				String data[][]=new String[r_cnt][6];
	rs = stm.executeQuery("select farmer_bill.bill_id,farmer_bill.fid,farmer.fname,farmer_bill.c_no,farmer_bill.paid_amt,farmer_bill.date from farmer_bill inner join farmer on farmer_bill.fid where farmer_bill.fid=farmer.fid and date='"+db3.getText()+"'");
				
				rs.first();
				for(i=0;i<r_cnt;i++)
				 {
				  data[i][0]=rs.getString(1);
				  data[i][1]=rs.getString(2);
				  data[i][2]=rs.getString(3);
				  data[i][3]=rs.getString(4);
				  data[i][4]=rs.getString(5);
				  data[i][5]=rs.getString(6);
				  rs.next(); 		
				 }
				 showreport sp=new showreport(data,r_cnt,amt1);
				 sp.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println("error :"+e);
				//JOptionPane.showMessageDialog(null,"Error :"+e,"warning",JOptionPane.ERROR_MESSAGE);
			}
			}
		}
		if(ae.getSource()==b1 && rb[1].isSelected() && rb1[0].isSelected()) 
		{
			try{
			rs1=stm.executeQuery("select sum(paid_amt) from farmer_bill where date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
			rs1.first();
			amt1 = rs1.getFloat(1);

			rs=stm.executeQuery("select count(*) from farmer_bill where date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
			rs.first();
			int r_cnt=rs.getInt(1);
			
			String data[][]=new String[r_cnt][6];
		rs = stm.executeQuery("select farmer_bill.bill_id,farmer_bill.fid,farmer.fname,farmer_bill.c_no,farmer_bill.paid_amt,farmer_bill.date from farmer_bill inner join farmer on farmer_bill.fid where farmer_bill.fid=farmer.fid and farmer_bill.date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
					
			rs.first();
			for(i=0;i<r_cnt;i++)
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				rs.next(); 		
			}
				showreport sp=new showreport(data,r_cnt,amt1);
				sp.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println("error :"+e);
					//JOptionPane.showMessageDialog(null,"Error :"+e,"warning",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(ae.getSource()==b1 && rb[2].isSelected() && rb1[0].isSelected()) 
		{
			try{
			rs1=stm.executeQuery("select sum(paid_amt) from farmer_bill where date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
			rs1.first();
			amt1 = rs1.getFloat(1);

			rs=stm.executeQuery("select count(*) from farmer_bill where date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
			rs.first();
			int r_cnt=rs.getInt(1);
			
			String data[][]=new String[r_cnt][6];
		rs = stm.executeQuery("select farmer_bill.bill_id,farmer_bill.fid,farmer.fname,farmer_bill.c_no,farmer_bill.paid_amt,farmer_bill.date from farmer_bill inner join farmer on farmer_bill.fid where farmer_bill.fid=farmer.fid and farmer_bill.date between '"+tduedate.getText()+"' and '"+tfrmdate.getText()+"'");
					
			rs.first();
			for(i=0;i<r_cnt;i++)
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				rs.next(); 		
			}
				showreport sp=new showreport(data,r_cnt,amt1);
				sp.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println("error :"+e);
					//JOptionPane.showMessageDialog(null,"Error :"+e,"warning",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(ae.getSource()==b1 && rb[3].isSelected() && rb1[0].isSelected()) 
		{
			try{
			rs1=stm.executeQuery("select sum(paid_amt) from farmer_bill where date between '"+db1.getText()+"' and '"+db2.getText()+"'");
			rs1.first();
			amt1 = rs1.getFloat(1);

			rs=stm.executeQuery("select count(*) from farmer_bill where date between '"+db1.getText()+"' and '"+db2.getText()+"'");
			rs.first();
			int r_cnt=rs.getInt(1);
			
			String data[][]=new String[r_cnt][6];
		rs = stm.executeQuery("select farmer_bill.bill_id,farmer_bill.fid,farmer.fname,farmer_bill.c_no,farmer_bill.paid_amt,farmer_bill.date from farmer_bill inner join farmer on farmer_bill.fid where farmer_bill.fid=farmer.fid and farmer_bill.date between '"+db1.getText()+"' and '"+db2.getText()+"'");
					
			rs.first();
			for(i=0;i<r_cnt;i++)
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				data[i][3]=rs.getString(4);
				data[i][4]=rs.getString(5);
				data[i][5]=rs.getString(6);
				rs.next(); 		
			}
				showreport sp=new showreport(data,r_cnt,amt1);
				sp.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println("error :"+e);
					//JOptionPane.showMessageDialog(null,"Error :"+e,"warning",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	    /*  if(ae.getSource()==b1)
		  {
			try
			{	
				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs1=stmt.executeQuery("select mid from bill");
				while(rs1.next())
				{
				 if(tmid.getText().equals(rs1.getString(1)))
				 {
				  f=1;
				  fl=0;
				  flag1=1;
				  break;
				 }
				}			  

				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs1=stmt.executeQuery("select mid from delinfo");
				while(rs1.next())
				{
				 if(tmid.getText().equals(rs1.getString(1)))
				 {
				  fl=1;
				  f=0;
				  flag1=1;
				  break;
				 }
				}
							
			  }			
			  catch(Exception e)
			  {
			   System.out.println(e);
			  }       
		 }
			
	}*/
			 
			 
/*
			  
		 int i,j,n=0;
		 
		 
	 
	 if(ae.getSource()==b1 && rb[0].isSelected())
	 {
	  if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db3.getText().length()!=0)
      {
	   if(fl==0)
	   {
	   if(f==1)
	   {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)
		 {
		  
		  stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date='"+db3.getText()+"'");
	      rs1.first();
		  int vn1=rs1.getInt(1);
	      int am1=rs1.getInt(2);
		  System.out.println(""+vn1);
		  
 		 rs=stmt.executeQuery("select * from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date='"+db3.getText()+"'");
   				 
		 
         String data[][]=new String[vn1][9];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	     }
	     
	     
	     showreport sp=new showreport(data,vn1,am1);
		 sp.setVisible(true);
		}
		else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs1=stmt.executeQuery("select count(vno),sum(pamt) from monthlypass where  mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate='"+db3.getText()+"'");
	     rs1.first();
		 int vn1=rs1.getInt(1);
	     int am1=rs1.getInt(2);
		 
 		 rs=stmt.executeQuery("select * from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate='"+db3.getText()+"'");
   		  
		
         String data[][]=new String[vn1][7];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn1,am1);
		 sp.setVisible(true);	
		
		
		}
		}
		
       	   
	    catch(Exception e)
	     {
		  System.out.println(e);
	   	 } 
	  }
	  f=0;
	  fl=0;
	   }
      }  
	 
    }
 	
	   if(ae.getSource()==b1 && rb[1].isSelected())
	   {
	    if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
        {
		if(fl==0)
	   {
	    if(f==1)
	    {
	    try
	    {
		if(jcb2.getSelectedIndex()!=3)
		{
	     stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+tduedate.getText()+"' and date<='"+tfrmdate.getText()+"'");
	      rs1.first();
		  int vn1=rs1.getInt(1);
	      int am1=rs1.getInt(2);
		  System.out.println(""+vn1);
		  
	     rs=stmt.executeQuery("select * from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+tduedate.getText()+"' and date<='"+tfrmdate.getText()+"'");
         
         String data[][]=new String[vn1][9];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	     }
	   
	     
	     showreport sp=new showreport(data,vn1,am1);
		 sp.setVisible(true);
        }
		else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs1=stmt.executeQuery("select count(vno),sum(pamt) from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+tduedate.getText()+"' and frmdate<='"+tfrmdate.getText()+"'");
	     rs1.first();
		 int vn1=rs1.getInt(1);
	     int am1=rs1.getInt(2);
		 
 		 rs=stmt.executeQuery("select * from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+tduedate.getText()+"' and frmdate<='"+tfrmdate.getText()+"'");
   		  

         String data[][]=new String[vn1][7];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn1,am1);
		 sp.setVisible(true);	
			
		}
        }		
	    catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	  }	
	  f=0;
	  fl=0;
		}
     }  
	}	
  
	 if(ae.getSource()==b1 && rb[2].isSelected())
	 {
	  if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
      {
	  if(fl==0)
	   {
	   if(f==1)
	   {
	   try
	   {
	   if(jcb2.getSelectedIndex()!=3)
	   {
	    stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+tduedate.getText()+"' and date<='"+tfrmdate.getText()+"'");
	      rs1.first();
		  int vn1=rs1.getInt(1);
	      int am1=rs1.getInt(2);
		  System.out.println(""+vn1);
		  
	    rs=stmt.executeQuery("select * from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+tduedate.getText()+"' and date<='"+tfrmdate.getText()+"'");
       
        String data[][]=new String[vn1][9];
        rs.first();
       
        for(i=0;i<vn1;i++)
        {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	    }
	   
	     
	    showreport sp=new showreport(data,vn1,am1);
		sp.setVisible(true);
       }
	    else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs1=stmt.executeQuery("select count(vno),sum(pamt) from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+tduedate.getText()+"' and frmdate<='"+tfrmdate.getText()+"'");
	     rs1.first();
		 int vn1=rs1.getInt(1);
	     int am1=rs1.getInt(2);
		 
 		 rs=stmt.executeQuery("select * from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+tduedate.getText()+"' and frmdate<='"+tfrmdate.getText()+"'");
   		  
	
         String data[][]=new String[vn1][7];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn1,am1);
		 sp.setVisible(true);	
			
		}
       }	   
	    catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	}
      f=0;
	  fl=0;
	  }
    }  
    
  }	
	  
	  if(ae.getSource()==b1 && rb[3].isSelected())
	  {
	   if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
       {
	   if(fl==0)
	   {
	   if(f==1)
	   {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)0
		 {
	      stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
	      rs1.first();
		  int vn1=rs1.getInt(1);
	      int am1=rs1.getInt(2);
		  System.out.println(""+vn1);
		  
	     rs=stmt.executeQuery("select * from bill where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
         
         String data[][]=new String[vn1][9];
         rs.first();
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	    }
	   
	     
	     showreport sp=new showreport(data,vn1,am1);
		 sp.setVisible(true);
      }
       else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs1=stmt.executeQuery("select count(vno),sum(pamt) from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
	     rs1.first();
		 int vn1=rs1.getInt(1);
	     int am1=rs1.getInt(2);
		 
 		 rs=stmt.executeQuery("select * from monthlypass where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
   		  

         String data[][]=new String[vn1][7];
         rs.first();
       
         for(i=0;i<vn1;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn1,am1);
		 sp.setVisible(true);	
			
		}
	  
	  }
	   catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	}
      f=0;
	  fl=0;
	  }	
    }  
     
  }
  
         
		  
	if(ae.getSource()==b1 && rb[0].isSelected())
	 {
	  if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db3.getText().length()!=0 )
      {
	   
	   if(f==0)
	   {
	   if(fl==1)
	   {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)
		 {
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date='"+db3.getText()+"'");
	      rs1.first();
		  int vn2=rs1.getInt(1);
	      int am2=rs1.getInt(2);
		  System.out.println(""+vn2);
		  
		 rs=stmt.executeQuery("select * from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date='"+db3.getText()+"'");
   		  
		 while(rs.next())
         {
       	  n++;
         }
         String data[][]=new String[vn2][9];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	     }
	   
	     
	     showreport sp=new showreport(data,vn2,am2);
		 sp.setVisible(true);
		}
		else
		{
		  stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs2=stmt.executeQuery("select count(vno),sum(pamt) from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate='"+db3.getText()+"'");
		  rs2.first();
		 
		 int vn2=rs2.getInt(1);
	     int am2=rs2.getInt(2);
		 rs=stmt.executeQuery("select * from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate='"+db3.getText()+"'");
   		  
         String data[][]=new String[vn2][7];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn2,am2);
		 sp.setVisible(true);
		
		}
		}
       	   
	    catch(Exception e)
	     {
		  System.out.println(e);
	   	 }
	   }
      f=0;
	  fl=0;
		} 
      }  
	  
    }
 	
	   if(ae.getSource()==b1 && rb[1].isSelected())
	   {
	    if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
        {
		if(f==0)
		{
	    if(fl==1)
	    {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)
		 {
	     stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs1=stmt.executeQuery("select count(vno),sum(amt) from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
	      rs1.first();
		  int vn2=rs1.getInt(1);
	      int am2=rs1.getInt(2);
		  System.out.println(""+vn2);
		  
	     rs=stmt.executeQuery("select * from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
         
         String data[][]=new String[vn2][9];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	     }
	   
	     
	     showreport sp=new showreport(data,vn2,am2);
		 sp.setVisible(true);
        }
         else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs2=stmt.executeQuery("select count(vno),sum(pamt) from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
		 rs2.first();
		 
		 int vn2=rs2.getInt(1);
	     int am2=rs2.getInt(2);
 		 rs=stmt.executeQuery("select * from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db1.getText()+"'");
   		  
	
         String data[][]=new String[vn2][7];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn2,am2);
		 sp.setVisible(true);	
			
		}		
		
        }		
	    catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	  }
      	f=0;
	  fl=0;
	  }
    }  
     
   }	
   
   
   
   if(ae.getSource()==b1 && rb[2].isSelected())
	   {
	    if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
        {
		if(f==0)
		{
	    if(fl==1)
	    {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)
		 {
	     stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
	      rs1.first();
		  int vn2=rs1.getInt(1);
	      int am2=rs1.getInt(2);
		  System.out.println(""+vn2);
		  
	     rs=stmt.executeQuery("select * from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
        
         String data[][]=new String[vn2][9];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	     }
	   
	     
	     showreport sp=new showreport(data,vn2,am2);
		 sp.setVisible(true);
        }
         else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs2=stmt.executeQuery("select count(vno),sum(pamt) from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
		 rs2.first();
		 
		 int vn2=rs2.getInt(1);
	     int am2=rs2.getInt(2);
 		 rs=stmt.executeQuery("select * from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db1.getText()+"'");
   		  
		
         String data[][]=new String[vn2][7];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn2,am2);
		 sp.setVisible(true);	
			
		}		
		
        }		
	    catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	  }
      	f=0;
	  fl=0;
	  }
    }  
     
   }	
  
	 
	  
	  if(ae.getSource()==b1 && rb[3].isSelected())
	  {
	   if(tmid.getText().length()!=0 && jcb1.getSelectedIndex()!=0 && jcb2.getSelectedIndex()!=0 && db1.getText().length()!=0 && db2.getText().length()!=0)
       {
	   if(f==0)
	   {
	   if(fl==1)
	   {
	    try
	    {
		 if(jcb2.getSelectedIndex()!=3)
		 {
	     stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  rs1=stmt.executeQuery("select count(vno),sum(amt) from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
	      rs1.first();
		  int vn2=rs1.getInt(1);
	      int am2=rs1.getInt(2);
		  System.out.println(""+vn2);
		  
	     rs=stmt.executeQuery("select * from delinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and ptype='"+jcb2.getSelectedItem()+"' and date>='"+db1.getText()+"' and date<='"+db2.getText()+"'");
        
         String data[][]=new String[vn2][9];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
          data[i][7]=rs.getString(8);
          data[i][8]=rs.getString(9);
          
          rs.next(); 		
	    }
	   
	     
	     showreport sp=new showreport(data,vn2,am2);
		 sp.setVisible(true);
        }
         else
		{
		 stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		 rs2=stmt.executeQuery("select count(vno),sum(pamt) from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"'  and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
		 rs2.first();
		 
		 int vn2=rs2.getInt(1);
	     int am2=rs2.getInt(2);
 		 rs=stmt.executeQuery("select * from monthlyinfo where mid="+tmid.getText()+" and vtype='"+jcb1.getSelectedItem()+"' and frmdate>='"+db1.getText()+"' and frmdate<='"+db2.getText()+"'");
   		  

         String data[][]=new String[vn2][7];
         rs.first();
       
         for(i=0;i<vn2;i++)
         {
          data[i][0]=rs.getString(1);
          data[i][1]=rs.getString(2);
          data[i][2]=rs.getString(3);
          data[i][3]=rs.getString(4);
          data[i][4]=rs.getString(5);
          data[i][5]=rs.getString(6);
          data[i][6]=rs.getString(7);
                    
          rs.next(); 		
	     }
	   
	     
	     showreportm sp=new showreportm(data,vn2,am2);
		 sp.setVisible(true);	
			
		}
 		
	   } 
	   catch(Exception e)
	    {
		 System.out.println(e);
	   	}
	}
      f=0;
	  fl=0;
	  }	
    }  
     
  }	  
    
	if(ae.getSource()==b1 && flag1==0 && tmid.getText().length()!=0)
      {
	  JOptionPane.showMessageDialog(null,"Manager Id.............. Doesn't Exists");
      }
        if(ae.getSource()==b2)
         {
          jcb1.setSelectedIndex(0);
	      jcb2.setSelectedIndex(0);
          
		  tmid.setText("");
		  flag1=0;
		  
         }
	   
      if(ae.getSource()==b3)
       {
          
          dispose();
       }
   }
*/
 public static void main(String args[])
 {
  new Report();
 }
}
