import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class newuser extends JFrame implements ActionListener,TextListener
{
  JButton save,clear,back;
  JLabel head,lmid,lmname,lmadds,lmcont,lusername,lpass,lques,lans;
  JTextField tmid;
  TextField tmname,tmadds,tmcont,tusername,ans;
  JPasswordField pass;
  JComboBox cb1;
  JPanel p1,p2,p3;
  
  int flag=0;
  
  Connection con;
  PreparedStatement prstm;
  Statement stmt;
  ResultSet rs,rs1;
  
  newuser()
  {
    setTitle("New User");
	setSize(400,600);
    setLocation(450,20);
	setLayout(null);
	
	lmid=new JLabel("User id");
	lmname=new JLabel("U name");
	lmadds=new JLabel("U address");
	lmcont=new JLabel("U contact");
	lusername=new JLabel("User Name");
	lpass=new JLabel("Password");
	lques=new JLabel("Question");
	lans=new JLabel("Answer");
	
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	
	p1.setLayout(null);
	p2.setLayout(null);
	p3.setLayout(null);
	
	head=new JLabel("Welcome");
	
	
	tmid=new JTextField();
	tmname=new TextField();
	tmadds=new TextField();
	tmcont=new TextField();
	tusername=new TextField();
	
	pass=new JPasswordField();
	ans=new TextField();
	
	
	
	head.setFont(new Font("Monotype Corsiva",Font.ITALIC,40));
	lmid.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lmname.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lmadds.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lmcont.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lusername.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lpass.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lans.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	lques.setFont(new Font("Monotype Corsiva",Font.ITALIC,20));
	
	
	
	cb1=new JComboBox();
	
	cb1.addItem("Select Question");
	cb1.addItem("your Favourate Movie");
	cb1.addItem("your Favourate Teacher");
	
	save=new JButton("Save");
	 save.setMnemonic('S');
	
	clear=new JButton("Clear");
	  clear.setMnemonic('C');
	
	back=new JButton("Back");
	  back.setMnemonic('k');
	  
	save.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
    clear.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));
	back.setFont(new Font("Lucida Calligraphy",Font.BOLD,13));  
	  
	add(p1);
    add(p2);
	add(p3);
	
	p1.setBorder(BorderFactory.createRaisedBevelBorder());		 
    p2.setBorder(BorderFactory.createRaisedBevelBorder());		 
	p3.setBorder(BorderFactory.createRaisedBevelBorder());		 
	
	p3.add(head);
	
	p1.add(lmid);p1.add(lmname);p1.add(lmadds);p1.add(lmcont);p1.add(lusername);p1.add(lques);p1.add(lpass);
	p1.add(lans);
	
	p1.add(tmid);p1.add(tmname);p1.add(tmadds);p1.add(tmcont);p1.add(tusername);p1.add(pass);p1.add(ans);
	p1.add(cb1); 
	
	p2.add(save);p2.add(clear);p2.add(back);
	
	
	
	p3.setBounds(20,30,340,50);
	head.setBounds(110,5,150,50);
	
	p1.setBounds(20,90,340,370);
	
	lmid.setBounds(40,30,90,20);
	tmid.setBounds(190,30,120,20);
	
	lmname.setBounds(40,80,90,20);
	tmname.setBounds(190,80,120,20);
	
	lmadds.setBounds(40,120,90,20);
	tmadds.setBounds(190,120,120,20);
	
	lmcont.setBounds(40,160,90,20);
	tmcont.setBounds(190,160,120,20);
	
	lusername.setBounds(40,200,130,20);
	tusername.setBounds(190,200,120,20);
	
	lpass.setBounds(40,240,120,20);
	pass.setBounds(190,240,120,20);
	
	lques.setBounds(40,280,90,20);
	cb1.setBounds(190,280,120,20);
	
	lans.setBounds(40,320,90,20);
	ans.setBounds(190,320,120,20);
	
	p2.setBounds(20,480,340,50);
	
	save.setBounds(10,15,80,25);
	clear.setBounds(130,15,80,25);
	back.setBounds(250,15,80,25);
	
	tmid.setEditable(false);
	try
	{
	  con= DriverManager.getConnection("jdbc:mysql://localhost:3306/newspaper","root","");
	  stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
      rs=stmt.executeQuery("select max(uid) from u_login");
	  rs.first();
      int bno=rs.getInt(1);
		tmid.setText(""+(bno+1));
	 
	}
	catch(Exception e){e.printStackTrace();}
	
	save.addActionListener(this);
	clear.addActionListener(this);
	back.addActionListener(this);
	
	
	tmname.addTextListener(this);
	tmadds.addTextListener(this);
	tmcont.addTextListener(this);
	tusername.addTextListener(this);
	
	
	setVisible(true);	
    
  }

   public void textValueChanged(TextEvent te)
    {
	    for(int i=0;i<tmname.getText().length();i++)
               {
                   if(Character.isDigit(tmname.getText().charAt(i)))
                   {
                       JOptionPane.showMessageDialog(null,"Only Characters Allowed","warning",JOptionPane.ERROR_MESSAGE);
                       tmname.setText("");
                       tmname.requestFocus();
                   }
               } 
		 
		for(int i=0;i<tmadds.getText().length();i++)
               {
                   if(Character.isDigit(tmadds.getText().charAt(i)))
                   {
                       JOptionPane.showMessageDialog(null,"Only Characters Allowed","warning",JOptionPane.ERROR_MESSAGE);
                       tmadds.setText("");
                       tmadds.requestFocus();
                   }
               } 
			   
		   
         if(tmcont.getText().length()>10)
		 {
		  JOptionPane.showMessageDialog(null,"Contact Should be 10 Digit","warning",JOptionPane.ERROR_MESSAGE);
		  tmcont.setText("");
          tmcont.requestFocus();
		 }
		 
		 
		for(int i=0;i<tmcont.getText().length();i++)
               {
                   if(!Character.isDigit(tmcont.getText().charAt(i)))
                   {
                       JOptionPane.showMessageDialog(null,"Only Numbers Allowed","warning",JOptionPane.ERROR_MESSAGE);
                       tmcont.setText("");
                       tmcont.requestFocus();
                   }
               } 		   
        
	    
		  
		  
    }
	
	
   public void actionPerformed(ActionEvent e)
   {
   
    if(e.getSource()==save)
	{
	  try
	    {
	  if(tmname.getText().length()!=0 && tmadds.getText().length()!=0 && tmcont.getText().length()!=0 && tusername.getText().length()!=0 && pass.getText().length()!=0 && ans.getText().length()!=0 && cb1.getSelectedIndex()!=0)
	  {	  
	  String sql;
	  rs=stmt.executeQuery("select user from u_login");
	  
	  while(rs.next())
	  {
	   String s1=rs.getString(1);
	   if(s1.equals(tusername.getText()))
	   {
	    flag++;
	   }	
	  }
	  if(flag!=0)
	  {
	   JOptionPane.showMessageDialog(null,"Please Enter Another UserName");
	   flag=0;
	  }
	  else
	  {
	  sql="insert into u_login values("+tmid.getText()+",'"+tmname.getText()+"','"+tmadds.getText()+"','"+tmcont.getText()+"','"+tusername.getText()+"','"+pass.getText()+"','"+cb1.getSelectedItem()+"','"+ans.getText()+"')";
      prstm=con.prepareStatement(sql);
	  prstm.execute();
	 
 sql="insert into logindb values('"+tusername.getText()+"','"+pass.getText()+"')";
      prstm=con.prepareStatement(sql);
	  prstm.execute();
	 

	 JOptionPane.showMessageDialog(null,"New User Created Successfully");
	  dispose();
	  }
	}
    else
     {
	   JOptionPane.showMessageDialog(null,"Please Fill Up All Information");
      }
   }
    catch(Exception e1)
	   {
        e1.printStackTrace();
	  }
         
}	  
    if(e.getSource()==clear)
	{
	 
	 tmname.setText("");
	 tmadds.setText("");
     tmcont.setText("");
	 tusername.setText("");
	 
	 pass.setText("");
	 ans.setText("");
	 
	 cb1.setSelectedIndex(0);
	 }
	if(e.getSource()==back)
	{
	 dispose();
	}
   
   }

   public static void main(String args[])
   {
     new newuser();
   }   
  

}