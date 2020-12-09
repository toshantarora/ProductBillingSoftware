import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class CustomerPaymentDetails
{	//JFrame f;
	JPanel panel,topPanel;
	JLabel headingLabel;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	public CustomerPaymentDetails()
	{
		    
		//f=new JFrame(); 
		panel=new JPanel();
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 80));
			    
		headingLabel=new JLabel("Customer Payment Details");
		jt1=new JTable();	
		jt1=getdetails();
		
	    jt1.setBounds(100,40,1000,300);          
	    sp=new JScrollPane(jt1);    
	    
	   headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
		    
	    headingLabel.setBounds(10,10,600,60);
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
	new CustomerPaymentDetails();	

	}
	public JTable getdetails()
	{
		try
		{	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:GaganExpress");
			PreparedStatement ps=con.prepareStatement("select * from CustomerPaymentDetails");
			ResultSet rs=ps.executeQuery();	//insert, update, delete
			DefaultTableModel model = new DefaultTableModel(new String[]{"Invoice No","Customer Id","Customer Name","Total Quantity","Net Amount","GST","Total","Date","Time"}, 0);
			model.setRowCount(0);
			JTable jt =new JTable(model);
			while(rs.next())
			{
			Vector<String> row=new Vector<String>();
				String billno=rs.getString("billno");
				String id=rs.getString("customerID");
				String name=rs.getString("name");
				String totalquantity=rs.getString("totalquantity");
				String netamount=rs.getString("netamount");
				String gst=rs.getString("gst");
				String total=rs.getString("total");
				String date=rs.getString("date");
				String time=rs.getString("time");
		//	model.addRow(new Object[]{id,name,price,quantity,discount,gst});
				row.add(billno);
				row.add(id);
				row.add(name);
				row.add(totalquantity);
				row.add(netamount);
				row.add(gst);
				row.add(total);
				row.add(date);
				row.add(time);
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