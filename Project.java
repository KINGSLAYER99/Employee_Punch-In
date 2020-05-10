package package1;


import java.sql.*;
import java.util.Scanner;
//import java.io.Console;
class Operations
{
	public int employ_id;
	Scanner sc=new Scanner(System.in);
	void login()
	{
		System.out.println("************Bank Employee Database:********* \n \n \n");
		System.out.println("Please Login to continue:\n");
		String s1;
		String s2;
		System.out.println("Enter Username :"); 
		s1=sc.next();
		System.out.println("Enter Password :"); 
		s2=sc.next();
        //s2=readPassword().toString();
	    
		try{  
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("Driver found!!");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","root");    
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select login('"+s1+"','"+s2+"')");  
			if(rs.next())
			employ_id=rs.getInt(1);
			else 
				employ_id=-1;
			con.close();
			}catch(Exception e){ System.out.println(e);
			System.err.println("Could not Login!!!!");} 
		if(employ_id<=0)
		{
			System.out.println("Login failed please try again..");
			login();
		}
		else if(employ_id==1)
		{
			System.out.println("Login sucessful....");
			manager();
		}
		else 
		{
			System.out.println("Login sucessful....");
			employee();
		}
		}
	 /*public char[] readPassword() {
		Console cons=System.console();
	        return cons.readPassword("");
	    }*/
	
	public void manager()
	{
		int ch;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","root");
			do {
			System.out.println("\nWelcome, Manager!!! \n\n\n\n 1.Entry In \n 2.Entry Out \n 3.All employees data \n 4.Search an employee \n 5.View your profile \n 6.Add an employee \n 7.Remove Employee \n 8.Logout");
			System.out.println("Enter your choice :");
			ch=sc.nextInt();
			Statement stmt=con.createStatement();
			switch(ch)
			{
		case 1:
				stmt.executeUpdate("insert into entry_scan(Employ_id,EDate,EntryTime) values("+employ_id+",curdate(),curtime())");
				System.out.println("Entered data into database successfully!!");
				break;
			
		case 2:
			stmt.executeUpdate("\n update entry_scan set ExitTime=curtime() where EDate=curdate() and Employ_id="+employ_id);
			System.out.println("\nEntered data successfully!");
			break;
		case 3:
		    ResultSet rs=stmt.executeQuery("select * from Employee");
		    System.out.println("Employ ID \t Employee Name\t\t\tDate Of Birth \t Address \t Contact \t Designation \t Salary \t Hours Worked \t Transaction count \t Bonus Amount");
			while(rs.next())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDate(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5)+"\t"+rs.getString(6)+"\t"+rs.getInt(7)+"\t"+rs.getInt(8)+"\t"+rs.getInt(9)+"\t"+rs.getInt(10));
		break;
		case 4:
			int e;
			System.out.println("\nEnter Employee ID to search :");
			e=sc.nextInt();
			ResultSet rs1=stmt.executeQuery("select * from Employee where Employ_id="+e);
		    System.out.println("Employee ID \t Employee Name \t\t\t Date Of Birth \t Address \t Contact \t Designation \t Salary \t Hours Worked \t Transaction count \t Bonus Amount");
			while(rs1.next())
				System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getDate(3)+"\t"+rs1.getString(4)+"\t"+rs1.getInt(5)+"\t"+rs1.getString(6)+"\t"+rs1.getInt(7)+"\t"+rs1.getInt(8)+"\t"+rs1.getInt(9)+"\t"+rs1.getInt(10));
			break;
		case 5:
			ResultSet rs2=stmt.executeQuery("select * from Employee where Employ_id="+employ_id);
		    System.out.println("Employee ID \t Employee Name \t\t\t Date Of Birth \t Address \t Contact \t Designation \t Salary \t Hours Worked \t Transaction Count \t Bonus Amount");
			while(rs2.next())
				System.out.println(rs2.getInt(1)+"\t"+rs2.getString(2)+"\t"+rs2.getDate(3)+"\t"+rs2.getString(4)+"\t"+rs2.getInt(5)+"\t"+rs2.getString(6)+"\t"+rs2.getInt(7)+"\t"+rs2.getInt(8)+"\t"+rs2.getInt(9)+"\t"+rs2.getInt(10));
			break;
		case 6:
			String name,address,designation,username,password,dateOfBirth,contact;
			int salary;
			System.out.println("\nEnter Name:");
			name=sc.next().toString();
			System.out.println("\nEnter Date Of Birth:");
			dateOfBirth=sc.next().toString();
			System.out.println("\nEnter Address:");
			address=sc.next().toString();
			System.out.println("\nEnter Contact:");
			contact=sc.next().toString();
			System.out.println("\nEnter Designation:");
			designation=sc.next().toString();
			System.out.println("\nEnter Salary:");
			salary=sc.nextInt();
			System.out.println("\nEnter UserName:");
			username=sc.next().toString();
			System.out.println("\nEnter Password:");
			password=sc.next().toString();
			
			
			stmt.executeUpdate("Insert into Employee(Employ_name,Date_Of_Birth,Address,contact,Designation,Salary,Hours_worked,Transactions,Bonus) values('"+name+"','"+dateOfBirth+"','"+address+"','"+contact+"','"+designation+"',"+salary+",0,0,0)");
			int e1;
			ResultSet rs3=stmt.executeQuery("select Employ_id from Employee where Employ_name='"+name+"' and Date_Of_Birth='"+dateOfBirth+"'");
			if(rs3.next())
			e1=rs3.getInt(1);
			else 
				e1=-1;
			stmt.executeUpdate("insert into Verify(Employ_id,username,password) values("+e1+",'"+username+"','"+password+"')");
			break;
			case 7:
			int e2;
			System.out.println("\nEnter Employee ID of Employee to be removed :");
			e2=sc.nextInt();
			stmt.executeUpdate("Delete from Employee where Employ_id="+e2);
			break;
			case 8:
			login();
			break;
			default:
				System.out.println("\nInvalid choice");
			}
			}while(ch!=8);
			con.close();  
			}catch(Exception e){ System.out.println(e);
			manager();}  
		
	}
	public void employee()
	{
		int ch;
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/Project","root","root");
			do {
			System.out.println("\nWelcome, Employee!!! \n\n\n\n 1.Entry In \n 2.Exit Out \n 3.Update a transaction count \n 4.View your profile \n 5.Logout");
			System.out.println("Enter your choice :");
			ch=sc.nextInt();
			Statement stmt=con.createStatement();
			switch(ch)
			{
			case 1:
				stmt.executeUpdate("insert into entry_scan(Employ_id,EDate,EntryTime) values("+employ_id+",curdate(),curtime())");
				System.out.println("Entered data into database successfully!!");
				break;
			case 2:
			stmt.executeUpdate("update entry_scan set ExitTime=curtime() where EDate=curdate() and Employ_id="+employ_id);
			System.out.println("Entered data into database successfully!!");
			break;
			case 3:
			stmt.executeUpdate("update Employee set Transactions=Transactions+1 where Employ_id="+employ_id);
			System.out.println("\nTransaction count updated!!!");
			break;
			case 4:
			ResultSet rs2=stmt.executeQuery("select * from Employee where Employ_id="+employ_id);
		  	System.out.println("Employee ID \t Employee name \t\t\t Date Of Birth \t Address \t Contact \t Designation \t Salary \t Hours Worked \t Transactions \t Incentives");
			while(rs2.next())
				System.out.println(rs2.getInt(1)+"\t"+rs2.getString(2)+"\t"+rs2.getDate(3)+"\t"+rs2.getString(4)+"\t"+rs2.getInt(5)+"\t"+rs2.getString(6)+"\t"+rs2.getInt(7)+"\t"+rs2.getInt(8)+"\t"+rs2.getInt(9)+"\t"+rs2.getInt(10));
			break;
			case 5:
			login();
			break;
			default:
				System.out.println("\nInvalid choice");
			}
			}while(ch!=8);
			con.close();  
			}catch(Exception e){ System.out.println(e);
			employee();}  
	}
}
public class Project {
	public static void main(String args[])
	{
		Operations op=new Operations(); 
		op.login();
	}
}