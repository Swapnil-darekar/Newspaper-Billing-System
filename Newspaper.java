import java.sql.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.text.*;

class Newspaper extends JFrame implements ActionListener,ItemListener
{
	JLabel l1,l2,l3,l5;
	JTextField t1,t3;
	JButton b1,b2,b3,b4,b5,b6;
	String colHeads[]={"N ID","N Name","Language"};
    Object data[][]={{"","","",""}};
    JScrollPane jsp;    int i=0;
	JTable tab;
	int r_cnt=0;	
	JRadioButton r1,r2,r3;
	JFrame f;
	ButtonGroup bg;
     	Connection cn;
     	PreparedStatement prstm;
     	Statement stm;
     	ResultSet rs;
     	String sql;
     	int flg=0;
	Newspaper()
	{
		super("Add Newspaper");
     	setLocation(200,200);
     	setLayout(null);
     	setSize(1000,500);
		getContentPane().setBackground(Color.PINK);
		t1=new JTextField();
	
		t3=new JTextField();
		r1=new JRadioButton("Marathi");
		r2=new JRadioButton("Hindi");
		r3=new JRadioButton("English");
		bg=new ButtonGroup();
	
		l1=new JLabel("Newspaper Entry Form");
		l2=new JLabel("Newspaper Title :");
		l3=new JLabel("Select Language :");
		l5=new JLabel("Newspaper ID :");
		
		b1=new JButton("Insert");
		b2=new JButton("Delete");
		b3=new JButton("Clear");
		b4=new JButton("Update");
		b5=new JButton("Back");
		b6=new JButton("Exit");
		
		l1.setBounds(300,30,500,50);
		Font myfont = new Font("Monotype Corsiva",Font.ITALIC,45);
		l1.setFont(myfont);
		l1.setForeground(Color.cyan);		
		
		l5.setBounds(50,100,150,50);
		t3.setBounds(50,140,100,30);
		l2.setBounds(200,100,150,50);
		t1.setBounds(200,140,130,30);
	
		l3.setBounds(50,170,150,50);
		r1.setBounds(190,200,150,30);
		r2.setBounds(190,230,150,30);
		r3.setBounds(190,260,150,30);
		
		b1.setBounds(50,310,100,30);
		b2.setBounds(150,310,100,30);
		b3.setBounds(250,310,100,30);
		b4.setBounds(50,350,100,30);
		b5.setBounds(150,350,100,30);
		b6.setBounds(250,350,100,30);
		
		
		add(l1);	add(l2);		add(l5);	
		add(l3);	bg.add(r3);	add(r3);	add(t3);
		add(t1);	bg.add(r2);	add(r2);	bg.add(r1);	add(r1);
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
			r1.addItemListener(this);
			r2.addItemListener(this);
			r3.addItemListener(this);	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		try{
	   		if(e.getSource()==b1)
     		{
				try{
				if(t1.getText().length()==0 
                   || t3.getText().length()==0 ||r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false)
             JOptionPane.showMessageDialog(null, "All Fields are Neccessary !!!");
			else{
				String s2="";
				if(r1.isSelected())
				{
					s2 +=r1.getText();
				}
				else if(r2.isSelected())	
				{		
					s2 +=r2.getText();
				}
				else if(r3.isSelected())	
				{		
					s2 +=r3.getText();
				}		
			sql = "insert into News_paper values('"+t3.getText()+"','"+t1.getText()+"','"+s2+"')";
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
				if(e.getSource()==b2)
				{
				
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Would You Like to Delete your Section ?","Warning",dialogButton);

				if (dialogButton == JOptionPane.YES_OPTION)
					JOptionPane.showMessageDialog(null, "Do You Want Delete  Newspaper  !!!");
			try{
		
				if(t3.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Please Enter Newspaper ID !!!");
				}
				else 
				{
					sql = "delete from News_paper where newspaper_id='"+t3.getText()+"'";
          			prstm=cn.prepareStatement(sql);
          			prstm.execute();
          			prstm.close();
					JOptionPane.showMessageDialog(null,"*** Delete Done ***");
          			flg=0;
					table_disp();
				}
			}
			catch(Exception ee)
			{
			JOptionPane.showMessageDialog(null,"*** Error In Delete  Records ***");
			}
			}
   		else 
		{
			
		}
		}		if(e.getSource()==b3)
     			{
          			t1.setText("");
          			t3.setText("");
					bg.clearSelection();
				}
     			if(e.getSource()==b4)
     			{
					try{
					if(t1.getText().length()==0|| t3.getText().length()==0 ||r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false)
					JOptionPane.showMessageDialog(null, "All Fields are Neccessary !!!");
				else
				{
							String s2="";
					if(r1.isSelected())
					{
						 s2 +=r1.getText();
					}

					else if(r2.isSelected())	
					{		
						 s2 +=r2.getText();
					}

					else if(r3.isSelected())	
					{		
						 s2 +=r3.getText();
					}
					sql = "update News_paper set newspaper_language='"+s2+"',newspaper_name='"+t1.getText()+"'where newspaper_id='"+t3.getText()+"'";
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
			JOptionPane.showMessageDialog(null,"*** Error In Update  Records ***");
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
	
		public void itemStateChanged(ItemEvent e)
		{
			try{
						String s2="",s3="";
					String s = t1.getText();

					if(r1.isSelected())
					{
						 s2 +=r1.getText();
					}

					else if(r2.isSelected())	
					{		
						 s2 +=r2.getText();
					}

					else if(r3.isSelected())	
					{		
						 s2 +=r3.getText();
					}		
			}
				catch(Exception ee1)
				{
          		ee1.printStackTrace();
				
				JOptionPane.showMessageDialog(null, "Please Enter Correct Information !!!");
				}
     
		}  
	public void table_disp()
	{
		try
		{
			rs=stm.executeQuery("select count(*) from News_paper");
			rs.next();
			r_cnt=rs.getInt(1);
			data=new String[r_cnt+1][3];
			rs=stm.executeQuery("select * from News_paper order by newspaper_id");
			i=0;
			while(rs.next())
			{
				data[i][0]=rs.getString(1);
				data[i][1]=rs.getString(2);
				data[i][2]=rs.getString(3);
				
				i++;
			}
				
			tab = new JTable(data, colHeads);

		// Resize the table coloumns
        DefaultTableColumnModel colModel=(DefaultTableColumnModel)tab.getColumnModel();
    	TableColumn col=colModel.getColumn(1);
    	col=colModel.getColumn(0);        col.setPreferredWidth(100);
        col=colModel.getColumn(1);        col.setPreferredWidth(100);
    	col=colModel.getColumn(2);        col.setPreferredWidth(100);
            
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;	
        jsp = new JScrollPane(tab,v,h); add(jsp);
        jsp.setBounds(500,120,480,320);
 
		}
		catch(Exception e5)
		{
			e5.printStackTrace();
		}
     
	}
	public static void main(String args[])
	{
		new Newspaper();
	}
}
