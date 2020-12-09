import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class ViewPurchasedItems 
{
//	JFrame f;
	JPanel panel,topPanel;
	JLabel headingLabel;
	JTable jt1;
	JScrollPane sp;
	BorderLayout border;
	Font headingFont;
	public ViewPurchasedItems()
	{
	//	f=new JFrame();   
		panel=new JPanel();	    
		topPanel=new JPanel();
		topPanel.setPreferredSize(new Dimension(30, 80));
		headingLabel=new JLabel("View Purchased Details");
		jt1=new JTable();	
		 jt1=getdetails();
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
	new ViewPurchasedItems();	

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
	}

