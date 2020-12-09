package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class PrintBill implements ActionListener
{
	JFrame frame;
	JPanel panel,topPanel;
	Font headingFont;
	JLabel headingLabel,useridLabel;
	JSeparator sp; 
	JTextField useridText;
	JTable jt1;
	JScrollPane spane;
	BorderLayout border;
	JButton printButton, cancelButton;
	public PrintBill()
	{
		frame=new JFrame("Print Bill");
		
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 200));
		
		
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		
		headingLabel=new JLabel("Print Bill");
		
		jt1=new JTable();	
		 jt1=getdetails();
	    jt1.setBounds(100,40,1000,100); 
	    spane=new JScrollPane(jt1);        
			
		useridLabel=new JLabel("Enter Customer's Id: ");

		useridText=new JTextField(10);
		
		printButton=new JButton("Print");
		printButton.setFocusPainted(false);
		cancelButton=new JButton("Cancel");
		cancelButton.setFocusPainted(false);

		sp = new JSeparator();
		
		
		headingLabel.setBounds(10,10,600,60);
	    headingLabel.setFont(headingFont);
	    
	    sp.setBackground(Color.BLACK);
		sp.setBounds(0,80,1500,30);    
	    useridLabel.setBounds(10,100,180,30);
		useridText.setBounds(190,100,180,30);
		
		printButton.setBounds(50,150,120,30);
		cancelButton.setBounds(200,150,120,30);
		
		printButton.addActionListener(this);
		cancelButton.addActionListener(this);
		 border=new BorderLayout();
		   
		frame.add(panel);
		panel.setLayout(border);
		panel.add(topPanel, BorderLayout.NORTH);
				
		topPanel.setLayout(null);
		topPanel.add(headingLabel);
		topPanel.add(sp);
		topPanel.add(useridLabel);
		topPanel.add(useridText);
		
		topPanel.add(printButton);
		topPanel.add(cancelButton);
		
		panel.add(spane,BorderLayout.CENTER);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	public JPanel getPanel()
	{
		return panel;
	}

	public static void main(String args[])
	{
		new PrintBill();
	}
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource()==printButton)
		{
			if(useridText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "Customer Id is required!");
				useridText.requestFocus();
			}
			else
			JOptionPane.showMessageDialog(panel, "Searching");
		}
		if(evt.getSource()==cancelButton)
		{
			frame.dispose();
		}
	
	
}
	public JTable getdetails()
	{
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from PurchasedItems");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			DefaultTableModel model = new DefaultTableModel(new String[]{"Invoice No","Product Name", "Price", "Quantity","Total"}, 0);
			model.setRowCount(0);
			JTable jt =new JTable(model)
			{
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };

			while(rs.next())
			{
			Vector<String> row=new Vector<String>();
				String billno=rs.getString("billno");
				String name=rs.getString("name");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String total=rs.getString("total");
	
				//	model.addRow(new Object[]{id,name,price,quantity,discount,gst});
				row.add(billno.toString().trim());
				row.add(name.toString().trim());
				row.add(price.toString().trim());
				row.add(quantity.toString().trim());
				row.add(total.toString().trim());
				model.addRow(row);
			}
			return jt;
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}
		return jt1;

	}
	public JTable searchdetails()
	{
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from PurchasedItems where billno = ?");
			ps.setLong(1,useridText);
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			DefaultTableModel model = new DefaultTableModel(new String[]{"Invoice No","Product Name", "Price", "Quantity","Total"}, 0);
			model.setRowCount(0);
			JTable jt =new JTable(model)
			{
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };

			while(rs.next())
			{
			Vector<String> row=new Vector<String>();
				String billno=rs.getString("billno");
				String name=rs.getString("name");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String total=rs.getString("total");
	
				//	model.addRow(new Object[]{id,name,price,quantity,discount,gst});
				row.add(billno.toString().trim());
				row.add(name.toString().trim());
				row.add(price.toString().trim());
				row.add(quantity.toString().trim());
				row.add(total.toString().trim());
				model.addRow(row);
			}
			return jt;
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}
		return jt1;

	}

}
