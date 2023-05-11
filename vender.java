import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

class Vendor implements ActionListener
{
	JLabel l1,l2,l3,l4;
	JTextField t1,t2,t3;
	JButton b1,b2,b3,b4,b5,b6;
	java.awt.List ll;
	JFrame f;
	Vendor()
	{
		JFrame f=new JFrame();
		f.setLayout(null);
		f.setSize(600,450);
		f.getContentPane().setBackground(Color.RED);
		t1=new JTextField();
		t2=new JTextField();
		t3=new JTextField();
		
		l1=new JLabel("Vendor Entry Form");
		l2=new JLabel("Vendor Name :");
		l3=new JLabel("Contact No :");
		l4=new JLabel("Address :");
		
		b1=new JButton("Insert");
		b2=new JButton("Delete");
		b3=new JButton("Clear");
		b4=new JButton("Update");
		b5=new JButton("Back");
		b6=new JButton("Exit");
		
		ll=new java.awt.List();
		l1.setBounds(200,50,300,50);
		Font myfont = new Font("Times-Roman",Font.BOLD,25);
		l1.setFont(myfont);
		l1.setForeground(Color.cyan);		
		l2.setBounds(50,100,150,50);
		t1.setBounds(190,110,150,30);
		l3.setBounds(50,160,150,50);
		t2.setBounds(190,170,150,30);
		l4.setBounds(50,210,150,50);
		t3.setBounds(190,220,150,30);
		b1.setBounds(50,270,100,30);
		b2.setBounds(150,270,100,30);
		b3.setBounds(250,270,100,30);
		b4.setBounds(50,310,100,30);
		b5.setBounds(150,310,100,30);
		b6.setBounds(250,310,100,30);
		ll.setBounds(380,110,200,280);
		
		
		f.add(l1);	f.add(l2);	f.add(ll);
		f.add(l3);	f.add(l4);
		f.add(t1);	f.add(t2);	f.add(t3);
		
		f.add(b1);	f.add(b2);	f.add(b3);
		f.add(b4);	f.add(b5);	f.add(b6);
		
		//b1.setMnemonic('I');
		// b1.setMnemonic(KeyEvent.VK_I);

		b1.setToolTipText("this is save button");
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent e)
	{
	
	}
	public static void main(String args[])
	{
		new Vendor();
	}
}
