import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
class LoginPage implements ActionListener
{
	JFrame frame;
	JPanel panel;
	Font headingFont;
	JLabel headingLabel,rightsgLabel,userLabel,passwordLabel;
	JTextField userText;
	ImageIcon sgIcon;
	JPasswordField passwordText;
	JCheckBox showpassCheck;
	JButton loginButton,resetButton,cancelButton;
	JSeparator sp,sp2; 
	public LoginPage()
	{
		frame=new JFrame("Login Frame");
	
		panel=new JPanel(); 
		panel.setBounds(40,80,200,200);        

		headingFont=new Font("Times New Roman", Font.BOLD+Font.ITALIC, 50);
	    		
		headingLabel=new JLabel("Enter Login Details");
		
		userLabel=new JLabel("Enter User Name : ");
		passwordLabel=new JLabel("Enter Password : ");
						
		userText=new JTextField(10);
		passwordText=new JPasswordField(15);
		passwordText.setEchoChar('*');
		showpassCheck=new JCheckBox("Show Password");
		
		sgIcon=new ImageIcon("Images/sgtop.png");
		
		rightsgLabel=new JLabel(sgIcon);
		
		loginButton=new JButton("Login");
		loginButton.setFocusPainted(false);
		resetButton=new JButton("Reset");
		resetButton.setFocusPainted(false);
		cancelButton=new JButton("Cancel");
		cancelButton.setFocusPainted(false);
		headingLabel.setBounds(10,10,500,60);
		headingLabel.setFont(headingFont);
		
		sp = new JSeparator();
		sp.setBackground(Color.BLACK);
		sp2 = new JSeparator();
		sp2.setBackground(Color.BLACK);
		 	
		sp.setBounds(0,100,700,30);
		rightsgLabel.setBounds(470,120,191,140);
		userLabel.setBounds(50,120,150,30);
		userText.setBounds(190,120,200,30);
		passwordLabel.setBounds(50,160,150,30);
		passwordText.setBounds(190,160,200,30);
		showpassCheck.setBounds(185,200,180,30);
		loginButton.setBounds(50,250,100,30);
		resetButton.setBounds(170,250,100,30);
		cancelButton.setBounds(290,250,100,30);
		sp2.setBounds(0,300,700,30);	
		
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		cancelButton.addActionListener(this);
		showpassCheck.addActionListener(this);

		frame.add(panel);
		panel.setLayout(null);
		
		panel.add(headingLabel);
		panel.add(sp);
		panel.add(rightsgLabel);
		panel.add(userLabel);
		panel.add(userText);
		
		panel.add(passwordLabel);
		panel.add(passwordText);
		
		panel.add(showpassCheck);
		panel.add(loginButton);
		panel.add(resetButton);
		panel.add(cancelButton);
		panel.add(sp2);
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setLocation((d.width-686)/2,(d.height-366)/2);
	//panel.setBackground(Color.ORANGE);
	panel.setForeground(Color.BLUE);
		frame.setSize(686,386);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
			
	}
public static void main(String Arg[])
{
	new LoginPage();
	
}
public void actionPerformed(ActionEvent evt1) 
{
	String s="admin";
	String s1=userText.getText();
	String s2="sgbillings@1";
	String s3=new String(passwordText.getPassword());		
	if(evt1.getSource()==loginButton)
	{
			if(userText.getText().equals(""))
			{
				JOptionPane.showMessageDialog(panel, "User Name is required!");
				
				userText.requestFocus();
			}
			else if(s.equals(s1) && s2.equals(s3) )
			{	
				frame.dispose();
				 try 
				  {
		              UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		          }
				  catch (Exception e) {
		         }
				new Main2();									
			}
			else if(s!=s1 &&  s2.equals(s3) )
			JOptionPane.showMessageDialog(panel, "Inorrect User Name");
			else if(s.equals(s1) && s2!=s3)
			JOptionPane.showMessageDialog(panel, "Inorrect password");	
			else
			JOptionPane.showMessageDialog(null, "Inorrect username and password");	
	}
	else if(evt1.getSource()==cancelButton)
		{
		  String message = " Do You Want to Quit ? ";
          String title = "Quit";
          int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
          if (reply == JOptionPane.YES_OPTION)
          {
              System.exit(0);
          }
		}
	else if(evt1.getSource()==resetButton)
	{
		userText.setText(null);
		passwordText.setText(null);
	}
	else if(evt1.getSource()==showpassCheck)
		{
			if(showpassCheck.isSelected())
			{
				passwordText.setEchoChar((char)0);
			}
			else
			{
				passwordText.setEchoChar('*');
			}
		
		}
	}

}

