import java.awt.*; 
import java.awt.event.*;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DeleteProduct implements ActionListener
{
	GregorianCalendar gc;
	Thread datetimeThread;
//	JFrame frame;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,idLabel,nameLabel,vendornameLabel,categoryLabel,priceLabel,quantityLabel,totalLabel,dateLabel,timeLabel;
	JTextField nameText,vendornameText,categoryText,priceText,quantityText,totalText,dateText,timeText;
	JComboBox idCombo,nameCombo;
	JButton deleteButton, resetButton;
	JSeparator sp;
	public DeleteProduct()
	{
	//	frame=new JFrame("Update Stock");
		
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    
		headingLabel=new JLabel("Delete Product Details");
		dateLabel=new JLabel("Date:");
		timeLabel=new JLabel("Time:");
		idLabel=new JLabel("Product Id: ");
		nameLabel=new JLabel("Product Name: ");
		vendornameLabel=new JLabel("Vendor Name: ");
		categoryLabel=new JLabel("Enter Product Category : ");
		priceLabel=new JLabel("Enter Product Price : ");
		quantityLabel=new JLabel("Enter Quantity : ");		
		totalLabel=new JLabel("Total : ");

		idCombo=new JComboBox();
		idCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					String tmp=(String) idCombo.getSelectedItem();
					PreparedStatement ps=con.prepareStatement("select name,vendorname,category,price,quantity,total from ProductDetails where productId=?");
					ps.setString(1,tmp);
					ResultSet rs=ps.executeQuery();	//insert, update, delete

					if(rs.next())
					{
						String name=rs.getString("name");
						String vendorname=rs.getString("vendorname");
						String category=rs.getString("category");
						String price=rs.getString("price");
						String quantity=rs.getString("quantity");
						String total=rs.getString("total");
						
						nameText.setText(name.toString().trim());
						vendornameText.setText(vendorname.toString().trim());
						categoryText.setText(category.toString().trim());
						priceText.setText(price.toString().trim());
						quantityText.setText(quantity.toString().trim());
						totalText.setText(total.toString().trim());
					}
				}
				catch(Exception ee)
				{
					System.out.println(ee);
				}		
			}
			});			
		dateText=new JTextField(10);
		timeText=new JTextField(10);
		priceText=new JTextField(10);
		nameText=new JTextField(10);
		vendornameText=new JTextField(10);
		quantityText=new JTextField(10);
		categoryText=new JTextField(10);
		totalText=new JTextField(10);
			
		deleteButton=new JButton("Delete");
		deleteButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		
		headingLabel.setBounds(10,10,500,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		dateLabel.setBounds(440,100,180,30);
		dateText.setBounds(480,100,100,30);
		timeLabel.setBounds(440,140,180,30);
		timeText.setBounds(480,140,100,30);
		idLabel.setBounds(10,100,180,30);
		idCombo.setBounds(190,100,180,30);
		nameLabel.setBounds(10,140,180,30);
		nameText.setBounds(190,140,180,30);
		vendornameLabel.setBounds(10,180,180,30);
		vendornameText.setBounds(190,180,180,30);
		categoryLabel.setBounds(10,220,180,30);
		categoryText.setBounds(190,220,180,30);
		priceLabel.setBounds(10,260,180,30);
		priceText.setBounds(190,260,180,30);
		quantityLabel.setBounds(10,300,180,30);
		quantityText.setBounds(190,300,180,30);
		totalLabel.setBounds(10,340,180,30);
		totalText.setBounds(190,340,180,30);
		
		deleteButton.setBounds(50,400,120,30);
		resetButton.setBounds(200,400,120,30);
		
		//frame.add(panel);
		panel.setLayout(null);
		
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(dateLabel);
		panel.add(dateText);
		panel.add(timeLabel);
		panel.add(timeText);
		panel.add(idLabel);
		panel.add(idCombo);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(vendornameLabel);
		panel.add(vendornameText);
		panel.add(categoryLabel);
		panel.add(categoryText);
		panel.add(priceLabel);
		panel.add(priceText);
		panel.add(quantityLabel);
		panel.add(quantityText);
		panel.add(totalLabel);
		panel.add(totalText);
		
		panel.add(deleteButton);
		panel.add(resetButton);
		
		deleteButton.addActionListener(this);
		resetButton.addActionListener(this);
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			while(rs.next())
			{
				String id=rs.getString("productID");

				idCombo.addItem(id);
			}
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setVisible(true);
	
	}
	public JPanel getPanel()
	{
		return panel;
	}
	public static void main(String args[])
	{
		new DeleteProduct();
	}
	public void actionPerformed(ActionEvent evt1) 
	{
	if(evt1.getSource()==deleteButton)
	{
			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("Delete ProductDetails WHERE productID ='"+idCombo.getSelectedItem()+"'");
														
			int i=ps.executeUpdate();//insert, update, delete
			if(i>0)
			{
				JOptionPane.showMessageDialog(panel, "Record Deleted Successfully!");
				nameText.setText("");
				vendornameText.setText("");
				priceText.setText("");
				quantityText.setText("");
				categoryText.setText("");
				totalText.setText("");
			}
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(panel, "Database Error : "+ee);
		}
	
	}
	if(evt1.getSource()==resetButton)
	{
		nameText.setText("");
		vendornameText.setText("");
		categoryText.setText(" ");
		priceText.setText(" ");
		quantityText.setText(" ");
		totalText.setText(" ");
		}

}
}
