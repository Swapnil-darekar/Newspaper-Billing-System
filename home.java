import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;
import java.text.*;

class home extends JFrame implements ActionListener,FocusListener,KeyListener
{
	Connection cn;
	Statement stm;
	ResultSet rs;
	PreparedStatement prstm;
	String sql;
    int flg=0; 
	DateButton db1;
	JMenuBar mb;
	JMenu m1,m2,m3;
	JMenuItem mi1,mi2,mi4,mi5,mi7;
	JLabel l1,l2,l3,l4,l5,l6,l8,l7,l9,l10,l11;
	JTable tab;
    DefaultTableModel mdl;
	JScrollPane jsp;    int i=0;
 
	String colHeads[]={"Newspaper Name","Copies","Amount","Total"};
	Object data[][]={{"","","Total Bill = ",new Integer(0)}};
		       
	int r_cnt=0;
	
		Font fnt;
		JButton b1,b2,b3,b4,b5,b6,b7,b9,b10;
		JTextField t2,t3,t4;
		JComboBox cb1,cb2,cb3,cb4,cb5;
		
	home()
	{
		super("HOME");
	
		cb1=new JComboBox();
		cb2 = new JComboBox();
		cb3 = new JComboBox();
		cb4 = new JComboBox();
		cb5 = new JComboBox();

		setSize(1000,750);
		setLayout(null);
		setLocation(100,10);
		
		disp();
	try{
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
		stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			catch(Exception e)
			{
			
				e.printStackTrace();
				
			}
			
			
		t2=new JTextField();
		t3=new JTextField();
		t4=new JTextField();
		

		db1 = new DateButton();
	
		mi1=new JMenuItem("Add NewsPaper");
		mi2=new JMenuItem("Add Vendor");
		mi4=new JMenuItem("Generate bill");
		//mi5=new JMenuItem("Search All");
	//	mi6=new JMenuItem("Search By Date");
		mi7=new JMenuItem("Add Section");
		mi5=new JMenuItem("Add User");
		
		m1=new JMenu("Master Records");
		m2=new JMenu("Bill ");
		m3=new JMenu("Setting");
		mb=new JMenuBar();
		getContentPane().setBackground(Color.pink);

		fnt = new Font("Lucida Calligraphy", Font.BOLD, 28);
		//ImageIcon ii = new ImageIcon("Screenshot (21).PNG");
		l7=new JLabel("Select Bill No To Edit");
		
		l1=new JLabel("Date");
		l6=new JLabel("Select Vendor");
		l3=new JLabel("Select Newspaper");
		l4=new JLabel("Amount");
		l5=new JLabel("Copies");
		l8=new JLabel("Total");
		l10=new JLabel("Select Section:");
		l11=new JLabel("Select Tid For Edit :");
		
		b1=new JButton("Save");
		b2=new JButton("New");
		b3=new JButton("Edit");
		b4=new JButton("Update");
		b5=new JButton("Delete");
		b6=new JButton("Exit");
		b7=new JButton("ADD");
		b9=new JButton("SHOW");
		b10=new JButton("SHOW");
				
		l2=new JLabel("DAILY ENTRY FORM",JLabel.CENTER);
		l2.setForeground(Color.red);		
		
		add(l2);
		l2.setFont(fnt);
		add(cb1);
		add(cb2);
		add(cb3);
		add(cb5);

		add(l1);	add(l3);	add(l8);	
		add(l4);	add(l5);	add(l6);	add(l10);	
		add(t2);	add(t3);	add(t4);
		
		l2.setBounds(0,0,900,70);
		l1.setBounds(100,100,80,30);
		db1.setBounds(230,100,120,20);
	
		
		l6.setBounds(100,130,80,30);
		cb1.setBounds(230,130,190,20);

		l3.setBounds(100,160,120,30);
		cb2.setBounds(100,190,150,20);
		
		l10.setBounds(480,120,120,30);
		cb3.setBounds(580,130,140,20);
		
		
		l4.setBounds(280,160,80,30);
		t2.setBounds(280,190,80,20);
		l5.setBounds(370,160,80,30);
		t3.setBounds(370,190,80,20);
		l8.setBounds(460,160,80,30);
		t4.setBounds(460,190,80,20);
		b7.setBounds(550,190,80,20);
		
		
		b1.setBounds(150,570,100,30);
		b2.setBounds(280,570,100,30);
		b3.setBounds(410,570,80,30);
		b5.setBounds(150,605,100,30);
		
		b4.setBounds(280,605,100,30);
		b6.setBounds(410,605,80,30);
		add(cb4);add(l7);add(l11);

				l7.setBounds(650,160,120,30);
				cb5.setBounds(650,190,140,20);
				b10.setBounds(800,190,140,20);
				b9.setBounds(800,285,140,20);
				
				l11.setBounds(800,230,120,30);
				cb4.setBounds(800,260,140,20);
			//billno();
		
		b1.setMnemonic(KeyEvent.VK_S);
		b1.setToolTipText("this is save button");
		b2.setMnemonic(KeyEvent.VK_C);
		b2.setToolTipText("this is Clear button");
		b3.setMnemonic(KeyEvent.VK_E);
		b3.setToolTipText("this is Bill Record Edit button");
		b4.setMnemonic(KeyEvent.VK_D);
		b4.setToolTipText("this is Bill Record Delete button");
		b5.setMnemonic(KeyEvent.VK_U);
		b5.setToolTipText("this is Bill Record Update button");
		b6.setMnemonic(KeyEvent.VK_E);
		b6.setToolTipText("this is Exit button");
		b7.setMnemonic(KeyEvent.VK_A);
		b7.setToolTipText("this is Daily Newspaper Entry button");
		b10.setMnemonic(KeyEvent.VK_E);
		b10.setToolTipText("this is Bill NO Show button");
		b9.setMnemonic(KeyEvent.VK_E);
		b9.setToolTipText("this is Tansaction Number Show  button");
		
		
		//	l7.setBounds(0,0,900,550);
		add(b1);	add(b2);	add(b3); add(b4);
		add(b5);	add(b6);	add(b7);add(b10);
		add(b9);
		m1.add(mi1);	m1.add(mi2); 	m1.add(mi7);	m1.addSeparator();	
		m2.add(mi4);	
		mb.add(m1);		mb.add(m2);			m3.add(mi5); m3.addSeparator();
		mb.add(m3);		add(db1);		
		setJMenuBar(mb);
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi7.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		cb1.addFocusListener(this);
		cb2.addFocusListener(this);
		cb3.addFocusListener(this);
		cb4.addActionListener(this);
		cb5.addActionListener(this);
		t2.addKeyListener(this);
		t3.addKeyListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public void billno1()
	{
			cb5.removeAllItems();
			try{
			
				rs = stm.executeQuery("select bill_no from purches_master");
				while(rs.next())
				{
			
				String bill_no  = rs.getString("bill_no");
		
			cb5.addItem(bill_no);
				}				}
				catch(Exception e10)
				{
					JOptionPane.showMessageDialog(null,"error :"+e10);
				}
	}

	public void billno()
	{
			cb4.removeAllItems();
			try{
				rs = stm.executeQuery("select tid from transaction where bill_no='"+cb5.getSelectedItem()+"'");
				
				while(rs.next())
				{
					cb4.addItem(rs.getString(1));
					System.out.println("aaa"+rs.getString(1));
				}
				}
				catch(Exception e10)
				{
					JOptionPane.showMessageDialog(null,"error :"+e10);
				}
	}

	public void keyTyped(KeyEvent e) 
	{try{
		double amt=0;
		try{
		amt=Double.parseDouble(t2.getText());
		}catch(Exception eee)
		{
	
		}
		int copies=0;
		try{
		copies=Integer.parseInt(t3.getText());
		}
		catch(Exception eee)
		{
		
		}
		double tot=amt*copies;
		t4.setText(""+tot);
		try{
		Double tot_amt=0.0;
		tot_amt =tot+tot_amt;
		}
		catch(Exception eee){}
		}
		catch(Exception es)
		{
		
		}
	}
	public void	keyPressed(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e) 
	{
	
	}
	public void focusGained(FocusEvent fe)
	{
		try{
				if(fe.getSource()==cb1)
				{
				vendrr();
				}
				else if(fe.getSource()==cb2)
				{
				newss();
				}
				else if(fe.getSource()==cb3)
				{
				sectionn();
				}
				
			}catch(Exception ev)
			{
				
			}
	}
	public void focusLost(FocusEvent ffe)
	{
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mi1)
		{
			Newspaper f=new Newspaper();
		}
		if(e.getSource()==mi2)
		{
			Vendor1 f=new Vendor1();
		}
		
		
		if(e.getSource()==mi4)
		{
			Bill f=new Bill();
		}
		if(e.getSource()==mi7)
		{
			Section f=new Section();
		}
		if(e.getSource()==mi5)
		{
			newuser n= new newuser();
		}
		
		if(e.getSource()==b1)
		{
				if(  cb1.getSelectedIndex()==-1 ||  cb3.getSelectedIndex()==-1 )
				
             JOptionPane.showMessageDialog(null, "All Fields are Necessary !!!");
			
			else{
				try{	
					int rno = mdl.getRowCount();
					int Bill_no=0,tid=0;
					if(rno>0)
					{
						for(int i=0;i<rno;i++)
						{
						
							if(i==0)
							{
								
							int vid=0,sid=0;
							float total_amt = Float.parseFloat((mdl.getValueAt(rno-1, 3)).toString());
							try{
									rs=stm.executeQuery("select vid from vendor where vname='"+cb1.getSelectedItem()+"'");
									rs.next();
									vid=rs.getInt(1);
								}
							catch(Exception ee)
								{
								
								}
								
							try{
									rs=stm.executeQuery("select sid from section where sname='"+cb3.getSelectedItem()+"'");
									rs.next();
									sid=rs.getInt(1);
								}
								catch(Exception ee)
								{
								}
								
							String jdate=db1.getText();
							try{
									sql = "insert into purches_master(vid,sid,total_amt,bill_date) values("+vid+","+sid+","+total_amt+",'"+jdate+"')";
									prstm=cn.prepareStatement(sql);
									prstm.execute();
								}
							catch(Exception eee){
							}
							
							try{
									rs=stm.executeQuery("select max(Bill_no) from purches_master");
									rs.next();
									Bill_no=rs.getInt(1);
								}
								catch(Exception ee)
								{
								}
							}
							int newspaper_id=0;
							String nname = mdl.getValueAt(i,0).toString();
					
					

							try{
									rs=stm.executeQuery("select newspaper_id from News_paper where newspaper_name='"+nname+"'");
									rs.next();
									newspaper_id=rs.getInt(1);
								}
								catch(Exception ee)
								{
								}
								int copies = Integer.parseInt((mdl.getValueAt(i, 1)).toString());
								float amount = Float.parseFloat((mdl.getValueAt(i,2)).toString());
								float total = Float.parseFloat((mdl.getValueAt(i, 3)).toString());
							try{		
								sql = "insert into transaction(bill_no,newspaper_id,copies,amount,total) values("+Bill_no+","+newspaper_id+","+copies+","+amount+","+total+")";
								prstm=cn.
								prepareStatement(sql);
								prstm.execute();
								JOptionPane.showMessageDialog(null, "Records Save Successfully !!!");
											t2.setText("");
								t3.setText("");
								t4.setText("");
								disp();
								cb1.setSelectedIndex(-1);
								cb2.setSelectedIndex(-1);
								cb3.setSelectedIndex(-1);
								cb5.setSelectedIndex(-1);

								}
							catch(Exception ee)
								{
								JOptionPane.showMessageDialog(null, "Error"+ee);
								
								
								}
							}
				
					}
				}
				catch(Exception ee)
				{
				
				}
			}
			
		}if(e.getSource()==b2)
		{
			t2.setText("");
			t3.setText("");
			t4.setText("");
			disp();
			cb1.setSelectedIndex(-1);
			cb2.setSelectedIndex(-1);
			cb3.setSelectedIndex(-1);
			cb5.setSelectedIndex(-1);
			billno1();
			billno();
		}
			
		if(e.getSource()==b7)
		{
		try{
				if( t2.getText().length()==0 
			   || t3.getText().length()==0 || t4.getText().length()==0 || cb2.getSelectedIndex()==-1 )
             JOptionPane.showMessageDialog(null, "All Fields are Necessary !!!");
			  else
				{
			 
					int rno = mdl.getRowCount();
					float total_amt = Float.parseFloat((mdl.getValueAt(rno-1,3)).toString());
					 
					mdl.setValueAt(cb2.getSelectedItem(),rno-1,0);
					mdl.setValueAt(t2.getText(),rno-1, 1);
					mdl.setValueAt(t3.getText(),rno-1, 2);
					mdl.setValueAt(t4.getText(),rno-1, 3);

					total_amt = total_amt + Float.parseFloat(t4.getText());
					
				    t2.setText("");     t3.setText("");
					t4.setText("");    		cb2.setSelectedIndex(-1);
					mdl.addRow(colHeads);
					rno = mdl.getRowCount();
					mdl.setValueAt("",rno-1, 0);
					mdl.setValueAt("",rno-1, 1);
					mdl.setValueAt("Total Bill",rno-1, 2);
					mdl.setValueAt(total_amt,rno-1, 3);

				 }
			}
			catch(Exception ea)
			{
			}			
		}
		if(e.getSource()==b3)
		{
			try{
			billno1();
		
				}catch(Exception ee)
			{
			}
		}
		
								
			if(e.getSource()==b9)
				{
		if(cb4.getSelectedItem()!=null)
					{
			
					try{
						rs=stm.executeQuery("select tid,bill_no,newspaper_name,copies,amount,total from transaction,News_paper where transaction.newspaper_id=News_paper.newspaper_id and tid='"+cb4.getSelectedItem()+"'");
						rs.next();
						cb2.setSelectedItem(rs.getString(3));
						t2.setText(""+rs.getInt(4));
						t3.setText(""+rs.getFloat(5));
						t4.setText(""+rs.getFloat(6));
						
	
					}
						
						catch(Exception ee)
						{
							 JOptionPane.showMessageDialog(null, "Error In Save Records!!!");
			
						}
						
			}}	
		if(e.getSource()==b10)
			{
			
				int bill_no=0;
					int rno = mdl.getRowCount();
							double total_amt=0.0;
											try{
					if(cb5.getSelectedItem()!=null)
					{
						try{
						rs=stm.executeQuery(" select bill_no,vname,sname,total_amt,bill_date from purches_master,vendor,section where purches_master.vid=vendor.vid and purches_master.sid=section.sid and bill_no='"+cb5.getSelectedItem()+"'");
						rs.next();
						bill_no=rs.getInt(1);
						cb1.setSelectedItem(rs.getString(2));
						 cb3.setSelectedItem(rs.getString(3));
					 //total_amt = Double.parseDouble((mdl.setValueAt(rno-1,3)).toString());
						//double amt=rs.getString(4);
								db1.setText(rs.getString(5));
						}
						catch(Exception ee)
						{
						}
				
			cb4.removeAllItems();
			try{
			
				rs = stm.executeQuery("select tid from transaction where bill_no="+cb5.getSelectedItem());
				while(rs.next())
				{
			
				String tid  = rs.getString("tid");
		
			cb4.addItem(tid);
				}				}
				catch(Exception e10)
				{
					JOptionPane.showMessageDialog(null,"error :"+e10);
				}
			}
				}catch(Exception eee)
				{
				
				}
					}
					if(e.getSource()==b4)
   			{
				if(  cb1.getSelectedIndex()==-1 ||  cb3.getSelectedIndex()==-1 )
									
				 JOptionPane.showMessageDialog(null, "All Fields are Necessary !!!");
					else{
					try{	
						int rno = mdl.getRowCount();
						int Bill_no=0,tid=0;
					
						
						if(rno>0)
						{
							for(int i=0;i<rno;i++)
							{
							
								if(i==0)
								{
									
								int vid=0,sid=0;
								float total_amt = Float.parseFloat((mdl.getValueAt(rno-1, 3)).toString());
								
								
								try{
										rs=stm.executeQuery("select vid from vendor where vname='"+cb1.getSelectedItem()+"'");
										rs.next();
										vid=rs.getInt(1);
									}
								catch(Exception ee)
									{
						
									}
							
								try{
										rs=stm.executeQuery("select sid from section where sname='"+cb3.getSelectedItem()+"'");
										rs.next();
										sid=rs.getInt(1);
									}
						
									catch(Exception ee)
									{
							
									}
									
								String jdate=db1.getText();
							
								try
								{
									sql = "update purches_master set bill_no='"+cb5.getSelectedItem()+"',vid="+vid+",sid='"+sid+"',total_amt='"+total_amt+"',bill_date='"+jdate+"' where bill_no='"+cb5.getSelectedItem()+"'";
										prstm=cn.prepareStatement(sql);
										prstm.execute();
								
								}
								catch(Exception eee)
								{
							
								}
								
								try{
										rs=stm.executeQuery("select max(Bill_no) from purches_master");
										rs.next();
										Bill_no=rs.getInt(1);
									}
									
									catch(Exception ee)
									{
									}
								}
								
								
								int newspaper_id=0;
								String nname = mdl.getValueAt(i,0).toString();
												try{
										rs=stm.executeQuery("select newspaper_id from News_paper where newspaper_name='"+nname+"'");
										rs.next();
										newspaper_id=rs.getInt(1);
									}
									catch(Exception ee)
									{
									
									}
									
							
									int copies = Integer.parseInt((mdl.getValueAt(i, 1)).toString());
									float amount = Float.parseFloat((mdl.getValueAt(i,2)).toString());
									float total = Float.parseFloat((mdl.getValueAt(i, 3)).toString());
									
					sql = "update transaction set tid='"+cb4.getSelectedItem()+"',bill_no='"+cb5.getSelectedItem()+"',newspaper_id="+newspaper_id+",copies="+copies+",amount="+amount+",total='"+total+"' where bill_no='"+cb5.getSelectedItem()+"' and tid='"+cb4.getSelectedItem()+"'";
									prstm=cn.prepareStatement(sql);
									prstm.execute();
									JOptionPane.showMessageDialog(null,"***Update Done ***");
							}
						}
					}
				
				catch(Exception ee)
				{
				}
							

				}
			}
			
		if(e.getSource()==b5)
		{
		
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Would You Like to Delete your Section ?","Warning",dialogButton);

				if (dialogButton == JOptionPane.YES_OPTION)
			if(cb5.getSelectedIndex()==-1)
            {
				JOptionPane.showMessageDialog(null, "Please Select Bill ID !!!");
			  // if( t2.getText().length()==0|| t1.getText().length()==0 ||r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false)
			//JOptionPane.showMessageDialog(null, "Please Enter Newspaper ID !!!");
		
			}
			else 
				{
					try{
					sql = "delete from transaction where bill_no="+cb5.getSelectedItem();
          			prstm=cn.prepareStatement(sql);
          			prstm.execute();
          			prstm.close();
					JOptionPane.showMessageDialog(null,"*** Delete Done ***");
					billno();
					}
					
					catch(Exception ee)
					{
						JOptionPane.showMessageDialog(null,ee);
					}
					try{
					sql = "delete from purches_master where bill_no="+cb5.getSelectedItem();
          			prstm=cn.prepareStatement(sql);
          			prstm.execute();
          			prstm.close();
					JOptionPane.showMessageDialog(null,"*** Delete Done ***");
					billno();
					
					}
					catch(Exception ee)
					{
						JOptionPane.showMessageDialog(null,ee);
					}
	
			}	
		}
		if(e.getSource()==b6)
		{
			System.exit(0);
		}
	}
	public void disp()
	{ mdl = new DefaultTableModel(data, colHeads);
        tab = new JTable(mdl)
        {   public Class getColumnClass(int column)
            { switch (column)
                {   case 0:     return String.class;
                    case 1:     return String.class;
                    case 2:     return String.class;
                    default:    return Integer.class;
                }
            }
        };

        // Resize the table coloumns
        DefaultTableColumnModel colModel=(DefaultTableColumnModel)tab.getColumnModel();
    	TableColumn col=colModel.getColumn(1);
    	col=colModel.getColumn(0);        col.setPreferredWidth(100);
        col=colModel.getColumn(1);        col.setPreferredWidth(100);
    	col=colModel.getColumn(2);        col.setPreferredWidth(100);
    	col=colModel.getColumn(3);        col.setPreferredWidth(100);
    	   
        jsp = new JScrollPane(tab); add(jsp);
        		jsp.setBounds(100,250,650,300);
 		
	
	
	}
	
		
	public static void main(String args[])
	{
		new home();
	}
}