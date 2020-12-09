package src;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.*;
import javax.swing.*;

public class ViewCustomerDetails 
{	//JFrame f;
	JPanel panel,topPanel;
	JLabel headingLabel;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	DefaultTableModel model;
	public ViewCustomerDetails()
	{
		    
		//f=new JFrame(); 
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 80));
		headingLabel=new JLabel("View Customer Details");
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from CustomerDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			model = new DefaultTableModel(new String[]{"Customer Id","Customer Name", "Address", "City","Phone","Email"}, 0);
			model.setRowCount(0);
			jt1 =new JTable(model)
			{
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
		                return false;               
		        };
		    };

			while(rs.next())
			{
			Vector<String> row=new Vector<String>();
				String id=rs.getString("customerID");
				String name=rs.getString("name");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				
		//	model.addRow(new Object[]{id,name,price,quantity,discount,gst});
				row.add(id);
				row.add(name);
				row.add(address);
				row.add(city);
				row.add(phone);
				row.add(email);
				model.addRow(row);
			}
		}
		catch(Exception ee)
		{
			System.out.println(ee);
		}		
	    
		jt1.setBounds(100,40,1000,300);          
	    sp=new JScrollPane(jt1);    
	    
		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		    
	    headingLabel.setBounds(10,10,500,50);
	    headingLabel.setFont(headingFont);
	    border=new BorderLayout();
	    
	    //f.add(panel);
	    panel.setLayout(border);
	    panel.add(topPanel, BorderLayout.NORTH);
	    topPanel.setLayout(null);
	    topPanel.add(headingLabel);
	    panel.add(sp,BorderLayout.CENTER);
	  //  f.setExtendedState(JFrame.MAXIMIZED_BOTH);		
	//	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setVisible(true); 
		
	
	}
	public JPanel getPanel()
	{
		return panel;
	}

public static void main(String[] args) 
	{
	new ViewCustomerDetails();	

	}
	
}