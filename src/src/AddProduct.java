package src;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class AddProduct implements ActionListener,Runnable
{
	GregorianCalendar gc;
	Thread datetimeThread;
//	JFrame frame;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,productidLabel,nameLabel,priceLabel,quantityLabel,categoryLabel,vendoridLabel,vendornameLabel,vendoraddressLabel, vendorcontactLabel,totalLabel,dateLabel,timeLabel,vendoremailLabel;
	JTextField productidText,nameText,priceText, quantityText,categoryText,vendornameText,vendorcontactText,totalText,dateText,timeText,vendoremailText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	JButton saveButton, resetButton;
	JComboBox vendoridCombo;
	JSeparator sp;
	public AddProduct()
	{
		//frame=new JFrame();
		
		panel=new JPanel();
		
		headingLabel=new JLabel("Add Product Details");
		dateLabel=new JLabel("Date:");
		timeLabel=new JLabel("Time:");
		productidLabel=new JLabel("Product Id: ");
		nameLabel=new JLabel("Enter Product Name : ");
		priceLabel=new JLabel("Enter Product Price : ");
		quantityLabel=new JLabel("Enter Quantity : ");
		categoryLabel=new JLabel("Enter Product Category : ");
		vendoridLabel=new JLabel("Vendor Id: ");
		vendornameLabel=new JLabel("Enter Vendor's Name : ");
		vendoraddressLabel=new JLabel("Enter Vendor's Address : ");
		vendorcontactLabel=new JLabel("Enter Vendor's Contact : ");
		vendoremailLabel=new JLabel("<html>Enter Email ID : <br>(Optional)</html>");
		totalLabel=new JLabel("Total : ");

		dateText=new JTextField(10);
		timeText=new JTextField(10);
		productidText=new JTextField(10);
		nameText=new JTextField(10);  
		priceText=new JTextField(10);
		quantityText=new JTextField(10);
		categoryText=new JTextField(10);
		vendornameText=new JTextField(10);
		vendorcontactText=new JTextField(10);
		vendoremailText=new JTextField(10);
		totalText=new JTextField(10);
		
		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
		
		addressScroll=new JScrollPane(addressTextarea);
		
		
		saveButton=new JButton("Save Details");
		saveButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		vendoridCombo=new JComboBox();
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		    
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		sp.setBounds(0,80,1500,30);
		
		
		dateLabel.setBounds(900,100,180,30);
		dateText.setBounds(950,100,100,30);
		timeLabel.setBounds(900,140,180,30);
		timeText.setBounds(950,140,100,30);
		productidLabel.setBounds(10,100,180,30);
		productidText.setBounds(190,100,180,30);
		nameLabel.setBounds(10,140,180,30);
		nameText.setBounds(190,140,180,30);
		categoryLabel.setBounds(10,180,180,30);
		categoryText.setBounds(190,180,180,30);
		priceLabel.setBounds(10,220,180,30);
		priceText.setBounds(190,220,180,30);
		quantityLabel.setBounds(10,260,180,30);
		quantityText.setBounds(190,260,180,30);
		totalLabel.setBounds(10,300,180,30);
		totalText.setBounds(190,300,180,30);
		saveButton.setBounds(350,400,120,30);
		resetButton.setBounds(500,400,120,30);
		
		vendoridLabel.setBounds(440,100,180,30);
		vendoridCombo.setBounds(630,100,180,30);
		vendornameLabel.setBounds(440,140,180,30);
		vendornameText.setBounds(630,140,180,30);
		vendoraddressLabel.setBounds(440,180,180,30);
		addressScroll.setBounds(630,180,180,70);
		vendorcontactLabel.setBounds(440,260,180,30);
		vendorcontactText.setBounds(630,260,180,30);
		vendoremailLabel.setBounds(440,300,180,30);
		vendoremailText.setBounds(630,300,180,30);
		
		
		
		saveButton.addActionListener(this);
		resetButton.addActionListener(this);
		vendoridCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					String tmp=(String) vendoridCombo.getSelectedItem();
					PreparedStatement ps=con.prepareStatement("select name,address,phone,email from VendorDetails where vendorId=?");
					ps.setString(1,tmp);
					ResultSet rs=ps.executeQuery();	//insert, update, delete

					if(rs.next())
					{
						String name=rs.getString("name");
						String vendoraddress=rs.getString("address");
						String phone=rs.getString("phone");
						String vendoremail=rs.getString("email");
						
						vendornameText.setText(name);
						addressTextarea.setText(vendoraddress.toString().trim());
						vendorcontactText.setText(phone);
						vendoremailText.setText(vendoremail.toString().trim());
						
					}
				}
				catch(Exception ee)
				{
					System.out.println(ee);
				}		

			}
		
		});

			
		panel.setLayout(null);
	
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(dateLabel);
		panel.add(dateText);
		panel.add(timeLabel);
		panel.add(timeText);
		panel.add(productidLabel);
		panel.add(productidText);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(categoryLabel);
		panel.add(categoryText);
		panel.add(priceLabel);
		panel.add(priceText);
		panel.add(quantityLabel);
		panel.add(quantityText);
		panel.add(vendoridLabel);
		panel.add(vendoridCombo);
		panel.add(vendornameLabel);
		panel.add(vendornameText);
		panel.add(vendoraddressLabel);
		panel.add(addressScroll);
		panel.add(vendorcontactLabel);
		panel.add(vendorcontactText);
		panel.add(vendoremailLabel);
		panel.add(vendoremailText);
		panel.add(totalLabel);
		panel.add(totalText);
		
		panel.add(saveButton);
		panel.add(resetButton);
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from VendorDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			while(rs.next())
			{
				
				String id=rs.getString("vendorID");
				vendoridCombo.addItem(id);
			}
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}
			
		  
 				
	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		datetimeThread=new Thread(this);
		datetimeThread.start();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
	
	}
	public JPanel getPanel()
	{
		return panel;
	}


	public static void main(String args[])
	{
		new AddProduct();
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==saveButton)
		{
			if(productidText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Product Name is required!");
				productidText.requestFocus();
			}
			else if(nameText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Product Name is required!");
				nameText.requestFocus();
			}
			else if(categoryText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Product Category is required!");
				categoryText.requestFocus();
			}
			else if(priceText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Price is required!");
				priceText.requestFocus();
			}
			else if(quantityText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Quantity is required!");
				quantityText.requestFocus();
			}

			else if(totalText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Total is required!");
				totalText.requestFocus();
			}
			else
			{
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					PreparedStatement ps=con.prepareStatement("insert into ProductDetails values(?,?,?,?,?,?,?,?,?)");
					
					
					ps.setString(1, productidText.getText());
					ps.setString(2, nameText.getText());
					ps.setString(3, vendornameText.getText());
					ps.setString(4, categoryText.getText());
					ps.setString(5, priceText.getText());
					ps.setString(6, quantityText.getText());
					ps.setString(7, totalText.getText());
					ps.setString(8, dateText.getText());
					ps.setString(9, timeText.getText());
					
					
					int i=ps.executeUpdate();//insert, update, delete
					if(i>0)
					{
						JOptionPane.showMessageDialog(panel, "Record Inserted Successfully!");
						productidText.setText("");
						nameText.setText("");
						categoryText.setText("");
						priceText.setText("");
						quantityText.setText("");
						totalText.setText("");
						
						vendornameText.setText(" ");
						addressTextarea.setText(" ");
						vendorcontactText.setText(" ");
						vendoremailText.setText(" ");
					}
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(panel, "Database Error : "+ee);
				}
			}
			}
		if(evt.getSource()==resetButton)
		{
			productidText.setText(" ");
			nameText.setText("");
			categoryText.setText(" ");
			priceText.setText(" ");
			quantityText.setText(" ");
			totalText.setText(" ");
			vendornameText.setText(" ");
			addressTextarea.setText(" ");
			vendorcontactText.setText(" ");
			vendoremailText.setText(" ");
	
		}
		
}
		public void run() {
			try {
				while(true)
				{
					gc=new GregorianCalendar();
					int DD=gc.get(Calendar.DATE);
					int MM=gc.get(Calendar.MONTH);
					int YYYY=gc.get(Calendar.YEAR);
					dateText.setText(DD+"/"+MM+"/"+YYYY);
					dateText.setCaretColor(Color.WHITE);
					int hh=gc.get(Calendar.HOUR);
					if(hh==0) 
					{
						hh=12;
					}
					int mm=gc.get(Calendar.MINUTE);
					int ampm=gc.get(Calendar.AM_PM);
					if(ampm==1)
					{	
					timeText.setText(hh+":"+mm+" PM");
					Thread.sleep(1000);
					}
					else
					{
						timeText.setText(hh+":"+mm+" AM");
						Thread.sleep(1000);
					}
					timeText.setCaretColor(Color.WHITE);

					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
		
		
}
