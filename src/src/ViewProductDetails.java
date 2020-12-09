package src;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ViewProductDetails 
{	//JFrame f;
	JPanel panel,topPanel;
	JLabel headingLabel;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	public ViewProductDetails()
	{
		    
		//f=new JFrame(); 
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 80));
			    
		headingLabel=new JLabel("View Product Details");
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from ProductDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			DefaultTableModel model = new DefaultTableModel(new String[]{"Product Id","Product Name","Vendor Name","Product Category","Price", "Quantity","Total","Date","Time"}, 0);
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
				String id=rs.getString("productID");
				String name=rs.getString("name");
				String vendorname=rs.getString("vendorname");
				String category=rs.getString("category");
				String price=rs.getString("price");
				String quantity=rs.getString("quantity");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
		//	model.addRow(new Object[]{id,name,price,quantity,discount,gst});
				row.add(id);
				row.add(name);
				row.add(vendorname);
				row.add(category);
				row.add(price);
				row.add(quantity);
				row.add(total);
				row.add(date);
				row.add(time);
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
	new ViewProductDetails();	

	}

}

