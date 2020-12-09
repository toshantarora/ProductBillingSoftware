package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
public class UpdateVendorDetails implements ActionListener
{
//	JFrame frame;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,vendoridLabel,vendornameLabel,vendoraddressLabel, vendorcontactLabel,vendoremailLabel;
	JTextField vendornameText,vendorcontactText,vendoremailText;
	JScrollPane addressScroll;
	JTextArea addressTextarea;
	JComboBox idCombo;
	JButton updateButton, resetButton;
	JSeparator sp;
	public UpdateVendorDetails()
	{
//		frame=new JFrame("Update Vendor Details");
		
		panel=new JPanel();
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		
		headingLabel=new JLabel("Update Vendor Details");
		vendoridLabel=new JLabel("Vendor Id: ");
		vendornameLabel=new JLabel("Enter Vendor's Name : ");
		vendoraddressLabel=new JLabel("Enter Vendor's Address : ");
		vendorcontactLabel=new JLabel("Enter Vendor's Contact : ");
		vendoremailLabel=new JLabel("Enter Vendor's Email : ");
		
		vendornameText=new JTextField(10);
		vendorcontactText=new JTextField(10);
		vendoremailText=new JTextField(10);
		
		addressTextarea=new JTextArea(5,10);
		addressTextarea.setLineWrap(true);
		addressTextarea.setWrapStyleWord(true);
				
		addressScroll=new JScrollPane(addressTextarea);
		
		idCombo=new JComboBox();
		idCombo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				
				try
				{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
					String tmp=(String) idCombo.getSelectedItem();
					PreparedStatement ps=con.prepareStatement("select name,address,phone,email from VendorDetails where vendorId=?");
					ps.setString(1,tmp);
					ResultSet rs=ps.executeQuery();	//insert, update, delete

					if(rs.next())
					{
						String name=rs.getString("name");
						String vendoraddress=rs.getString("address");
						String phone=rs.getString("phone");
						String vendoremail=rs.getString("email");
						
						vendornameText.setText(name.toString().trim());
						addressTextarea.setText(vendoraddress.toString().trim());
						vendorcontactText.setText(phone.toString().trim());
						vendoremailText.setText(vendoremail.toString().trim());
						
					}
				}
				catch(Exception ee)
				{
					System.out.println(ee);
				}		

			}
		
		});
		updateButton=new JButton("Update");
		updateButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		headingLabel.setBounds(10,10,700,50);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,80,1500,30);
		vendoridLabel.setBounds(10,100,190,30);
		idCombo.setBounds(190,100,190,30);
		vendornameLabel.setBounds(10,140,190,30);
		vendornameText.setBounds(190,140,190,30);
		vendoraddressLabel.setBounds(10,180,190,30);
		addressScroll.setBounds(190,180,190,60);
		vendorcontactLabel.setBounds(10,250,190,30);
		vendorcontactText.setBounds(190,250,190,30);
		vendoremailLabel.setBounds(10,290,190,30);
		vendoremailText.setBounds(190,290,190,30);
		
		updateButton.setBounds(50,340,120,30);
		resetButton.setBounds(200,340,120,30);
		
		updateButton.addActionListener(this);
		resetButton.addActionListener(this);
		
	//	frame.add(panel);
		panel.setLayout(null);
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(vendoridLabel);
		panel.add(idCombo);
		panel.add(vendornameLabel);
		panel.add(vendornameText);
		panel.add(vendoraddressLabel);
		panel.add(addressScroll);
		panel.add(vendorcontactLabel);
		panel.add(vendorcontactText);
		panel.add(vendoremailLabel);
		panel.add(vendoremailText);
		
		panel.add(updateButton);
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
				idCombo.addItem(id);
			}
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}	

	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	frame.setVisible(true);
	
	}
	public JPanel getPanel()
	{
		return panel;
	}

	public static void main(String args[])
	{
		new UpdateVendorDetails();
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==updateButton)
		{
		if(vendornameText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Name is required!");
			vendornameText.requestFocus();
		}
		else if(addressTextarea.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Address is required!");
			addressTextarea.requestFocus();
		}
		else if(vendorcontactText.getText().equals(""))
		{
			JOptionPane.showMessageDialog(panel, "Contact is required!");
			vendorcontactText.requestFocus();
		}
		else
		{
			String sPhoneNumber = vendorcontactText.getText();
			String email=vendoremailText.getText();
			Pattern pattern = Pattern.compile("\\d{10}");
			String pattern2="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern23=Pattern.compile(pattern2);
			Matcher matcher = pattern.matcher(sPhoneNumber);
			Matcher matcher2=pattern23.matcher(email);

	      if (!matcher.matches())
	      {
	    	  JOptionPane.showMessageDialog(panel,"Invalid Phone Number");
	      }
	      else if(!matcher2.matches())
	      {
	    	  JOptionPane.showMessageDialog(panel,"Invalid Email Id");
	      	} 
	      else {

				try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
				PreparedStatement ps=con.prepareStatement("UPDATE VendorDetails SET name ='"+vendornameText.getText()+"',address= '"+addressTextarea.getText()+"',phone='"+vendorcontactText.getText()+"',email='"+vendoremailText.getText()+"'WHERE vendorID ='"+idCombo.getSelectedItem()+"'");
														
			int i=ps.executeUpdate();//insert, update, delete
			if(i>0)
			{
				JOptionPane.showMessageDialog(panel, "Record Updated Successfully!");
				vendornameText.setText("");
				addressTextarea.setText(" ");
				vendorcontactText.setText("");
				vendoremailText.setText("");
			}
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(panel, "Database Error : "+ee);
		}
	
	      }
		}
	}
	if(evt.getSource()==resetButton)
	{
		vendornameText.setText("");
		addressTextarea.setText(" ");
		vendorcontactText.setText("");
		vendoremailText.setText("");
	}
	
}
		
} 