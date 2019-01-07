//This program scans the file given (custlist.txt) and sorts the information into the appropriate variables/data types.
//each line is a customer for the office supply company and is sorted into 2 child classes, Tax Exempt and non-Tax Exempt.
//the Program then sorts each entry in the file by the customer ID and formats the information into a list format, prints the
//information regarding bill balance and if the entrant is not tax exempt, it also prints the amount owed from the tax percent given
//in the file.

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
public class conzentj_program6 {

	public static void main(String[] args) throws IOException {
		
		File f1 = new File("custlist.txt");
		Scanner reader = new Scanner(f1);
		PrintWriter pw = new PrintWriter("final.txt");
		
	
		int size = reader.nextInt();
		conzentj_Customer [] list = new conzentj_Customer[size];
	
		fillArray(list, reader);
		Arrays.sort(list);
		printArray(list, pw);
		
	}
	
		public static void fillArray(conzentj_Customer [] list, Scanner reader) throws FileNotFoundException
		{
			
			String name = " ";
			int custID = 0;
			double billBal = 0.0;
			String email = " ";
			String taxLia = " ";
			
			String percent = " ";
			double Percent = 0.0;
			int count = 0;
			double amount = 0;
			
			while(reader.hasNext())
			{
				name = reader.next();
				custID = reader.nextInt();
				billBal = reader.nextDouble();
				email = reader.next();
				
				if(reader.hasNextDouble())
				{
					taxLia = "Tax Liable";
					Percent = reader.nextDouble();
					amount = billBal*Percent;
					
					list[count] = new conzentj_NonTaxExempt(name, custID, billBal, email, taxLia, amount);
				}
				else
				{
					taxLia = reader.next();
					list[count] = new conzentj_TaxExempt(name, custID, billBal, email, taxLia, percent);
				}
				count++;
			}
			reader.close();
		}
		
		public static void printArray(conzentj_Customer [] list , PrintWriter pw )
		{
			int pageNum = 1;
		
			
			
			for(int i = 0; i < list.length; i++)
			{
				
				if(i % 40 == 0)
				{
					System.out.println();
					System.out.format("%70s %31s%n"," Dunder Mifflin Inc. Customer Report", "Page: " + pageNum);
					System.out.format("%70s%n","===================================");
					System.out.format("%-20s %-5s %-36s %-13s %-13s %10s", "Customer", "ID", "Email Address", "Balance", "Tax Type", "Tax Amount"+ "\n");
					System.out.format("%-20s %-5s %-36s %-13s %-13s %10s", "========", "==", "=============", "=======","========","==========" );
					System.out.println();
					
					
							pw.println();
							pw.format("%70s %31s%n"," Dunder Mifflin Inc. Customer Report", "Page: " + pageNum);
							pw.format("%70s%n", "===================================");
							pw.println();
							pw.format("%-20s %-5s %-33s %-15s %-15s %10s", "Customer", "ID", "Email Address", "Balance", "Tax Type", "Tax Amount"+ "\n");
							pw.format("%-20s %-5s %-33s %-15s %-15s %10s", "========", "==", "=============", "=======","========","==========" + "\n" );
							pw.println();
				pageNum++;
				
				}
				
				System.out.println(list[i].toString());
				pw.println(list[i].toString());
			}
			pw.close();
		}
			
			
		

}
class conzentj_Customer implements Comparable <conzentj_Customer> {
	
	private String name;
	private int custID;
	private double billBal;
	private String email;
	private String taxLia;
	
	
	public conzentj_Customer()
	{
		this.name = " ";
		this.custID = 0;
		this.billBal = 0.0;
		this.email = " ";
		this.taxLia = " ";
	}
	public conzentj_Customer(String name, int custID, double billBal, String email, String taxLia)
	{
		this.name = name;
		this.custID = custID;
		this.billBal = billBal;
		this.email = email;
		this.taxLia = taxLia;
	}
	public int getID()
	{
		return custID;
	}
	public String getTaxLia()
	{
		return taxLia;
	}
	public String getName()
	{
		return name;
	}
	public String getEmail()
	{
		return email;
	}
	public double getBillBal()
	{
		return billBal;
	}
	public String toString()
	{
		return String.format("%-20s %-5s %-33s %,10.2f %14s", name, custID, email, billBal, taxLia);	
	}	
	public int compareTo(conzentj_Customer other)
	{
		if(this.custID == other.custID)
		{
			return 0;
		}
		else if (this.custID < other.custID)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

}
class conzentj_TaxExempt extends conzentj_Customer {

	private String percent;
	
	public conzentj_TaxExempt()
	{
		percent = " ";
	}
	public conzentj_TaxExempt(String name, int custID, double billBal, String email, String taxLia, String percent)
	{
		super(name, custID, billBal, email, taxLia);
		this.percent = percent;
		
	}
	public String getPercent()
	{
		return percent;
	}
	public String toString()
	{
		return super.toString() + String.format("%25s", percent);
	}
	
}
class conzentj_NonTaxExempt extends conzentj_Customer {
	
	
	
	private double amount;
	
	public conzentj_NonTaxExempt()
	{
		
		this.amount = 0.0;
	}
	
	public conzentj_NonTaxExempt(String name, int custID, double billBal, String email, String taxLia, double amount)
	{
		super(name, custID, billBal, email, taxLia);
		
		this.amount = amount;
		
	}
	public String toString()
	{
		
		
		return super.toString() + String.format("%,16.2f", amount);
	}
	
	
}

		


