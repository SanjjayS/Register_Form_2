import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class Register implements ActionListener
{

JFrame f = new JFrame("Register Form");
JLabel label = new JLabel();
JLabel l1 = new JLabel("Name : ");
JLabel l2 = new JLabel("Age : ");
JLabel l3 = new JLabel("District : ");
String dis[] = {"--Select District--","Chennai","Coimbatore","Kanchipuram","Vellore","Dindugal","Dharmapuri","Thanjavur"};
JLabel l4 = new JLabel("State : ");
JLabel l5 = new JLabel("Email : ");
JLabel l6 = new JLabel("Password : ");
JButton b1 = new JButton("Submit");
JButton b2 = new JButton("Print");
//JTextField t = new JTextField();
JTextField t1 = new JTextField();
JTextField t2 = new JTextField();
JComboBox t3 = new JComboBox(dis);
JTextField t4 = new JTextField();
JTextField t5 = new JTextField();
//JTextField t6 = new JTextField();
JPasswordField pf = new JPasswordField();
Register()
{
//label.setBounds(20,150,200,50);
l1.setBounds(20,20,80,30);
l2.setBounds(20,50,80,30);
l3.setBounds(20,80,80,30);
l4.setBounds(20,110,80,30);
l5.setBounds(20,140,80,30);
l6.setBounds(20,170,80,30);
t1.setBounds(90,20,150,25);
t2.setBounds(90,50,150,25);
t3.setBounds(90,80,150,25);
t4.setBounds(90,110,150,25);
t5.setBounds(90,140,150,25);
pf.setBounds(90,170,150,25);
b1.setBounds(120,210,80,30);
b2.setBounds(30,210,80,30);
label.setBounds(30,250,700,100);
//text.setBounds(100,20,100,30);
//f.add(t);f.add(l);
f.add(l1);f.add(l2);f.add(l3);f.add(l4);f.add(l5);f.add(l6);
f.add(t1);f.add(t2);f.add(t3);f.add(t4);f.add(t5);
f.add(pf);
f.add(label);
f.add(b1);f.add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
f.setSize(700,700);
f.setLayout(null);
f.setVisible(true);
}

public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==b2)
    {
        String data="Name:"+t1.getText();
        data+=", Age:"+t2.getText();
        data+=", District:"+t3.getItemAt(t3.getSelectedIndex());
        data+=", State:"+t4.getText();
        data+=", Email:"+t5.getText();
        data+=", Password:"+new String(pf.getPassword());
        label.setText(data);
    }
    else if(ae.getSource()==b1)
        System.exit(0);

try
{
    Connection con = DriverManager.getConnection("jdbc:derby:database_name;create=true");
    if(con!=null)
        System.out.println("Connection Established!!");
    Statement st = con.createStatement();
    try 
    {
        String createtable ="CREATE TABLE st(Name varchar(50),Age integer)";
        st.executeUpdate(createtable);  //these two lines are for table creating only!!
        System.out.println("Created!!");
    } catch (Exception e)
    {
        System.out.println(e);
    }
    try
    {
        String insertQuery = "INSERT INTO st (Name,Age) values (?,?)";
        PreparedStatement prepsta=con.prepareStatement(insertQuery);
        prepsta.setString(1,t1.getText());
        prepsta.setInt(2,Integer.parseInt(t2.getText()));
        prepsta.executeUpdate();
        System.out.println("Updated!!");
    }
    catch(Exception i)
    {
        System.out.println(i);
    }
    try
    {
        String alltable="SELECT * FROM st";
        ResultSet result=st.executeQuery(alltable);
        System.out.println("Data Stored!!");
        while(result.next())
        {
            System.out.println(String.format("Name:%s\tAge:%s\t",result.getString("Name"),result.getInt("Age")));
            System.out.println("------------------ OVER -----------------------");
        }
    }
    catch(Exception p)
    {
        System.out.println("|||Not OVER|||");
    }
}
catch(SQLException sqe)
{
    System.out.println(sqe);
}
}
public static void main (String[] args)
{
new Register();
}
}
