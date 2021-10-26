import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
public class Restaurant {

	public static void main(String[] args) throws IOException{
		Stock st1=new Stock();
		st1.menu();
	}
}

class StockData{
	short  ProductID;
	String	Productname;
	short 	Quantity;		
	float	Price;
}

class Stock{
	
	StockData[] stockArray;
	
	public  Stock() {
		stockArray=new StockData[100];
		
		try {
		      File myObj = new File("E:\\BCAS\\CSD18\\Resturant\\Resturant.txt");
		      Scanner myReader = new Scanner(myObj);
		      //Scanner 
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        Scanner in = new Scanner( data );
		        stockArray[Reccount]=new StockData();
		        
		        stockArray[Reccount].ProductID=in.nextShort();
				stockArray[Reccount].Productname=in.next();
				stockArray[Reccount].Quantity=in.nextShort();
				stockArray[Reccount].Price=in.nextFloat();
		        Reccount++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	public void menu() throws IOException{
		byte mkey=0;
		System.out.println(Reccount);
		while(mkey!=5) {
			mkey=(new Scanner( JOptionPane.showInputDialog( "Select 1-Add, 2-Modify, 3-Delete, 4-List, 5-Quit " ) )).nextByte();
			switch(mkey) {
			case 1: addproc();break;
			case 2: modiproc();break;
			case 3: deleproc();break;
			case 4: listproc();break;
			case 5: JOptionPane.showInputDialog("Quiting");break;
			default: JOptionPane.showInputDialog("Allowable keys 1-5 only"); break;
			}
		}
	}
	
	short Reccount=0;
	
	void addproc() {
		
		short count=((Reccount>0)?Reccount:0);
		boolean cont=true;
		
		
		while(cont && count<100) {

			stockArray[count]=new StockData();
			
			stockArray[count].ProductID=(new Scanner( JOptionPane.showInputDialog( " Enter ProductID ?" ))).nextShort();
			stockArray[count].Productname=(new Scanner( JOptionPane.showInputDialog( " Enter ProductName ?" ))).next();
			stockArray[count].Quantity=(new Scanner( JOptionPane.showInputDialog( " Enter Quantity ?" ))).nextShort();
			stockArray[count].Price=(new Scanner( JOptionPane.showInputDialog( " Enter Price ?" ))).nextFloat();
			
			cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();
			
			count++;
		}
		Reccount= count ;
	}
	
	
	
	void modiproc() {
		
		if(Reccount==0) {
			JOptionPane.showInputDialog("Stock Database is empty! add some valuable data");
			return;
		}
		short index=-1;
		boolean cont=true;
		short prodID=0;
		while(cont) {
			prodID=(new Scanner( JOptionPane.showInputDialog( " Enter ProductID to view ?" ))).nextShort();
			index=search(prodID);
			if(index==-1) {
				JOptionPane.showInputDialog("No such Product ID "+prodID);
			}
			else {
				System.out.println(index+"  "+stockArray[index].ProductID+stockArray[index].Productname+stockArray[index].Quantity+stockArray[index].Price);
				
				if((new Scanner(JOptionPane.showInputDialog(stockArray[index].ProductID+" "+stockArray[index].Productname+"  "+stockArray[index].Quantity+" "+stockArray[index].Price+
						" Wish to Modify? true/false"))).nextBoolean()) {
					
					stockArray[index].ProductID=(new Scanner( JOptionPane.showInputDialog( stockArray[index].ProductID))).nextShort();
					stockArray[index].Productname=(new Scanner( JOptionPane.showInputDialog( stockArray[index].Productname ))).next();
					stockArray[index].Quantity=(new Scanner( JOptionPane.showInputDialog( stockArray[index].Quantity ))).nextShort();
					stockArray[index].Price=(new Scanner( JOptionPane.showInputDialog( stockArray[index].Price ))).nextFloat();
				}
			}
			cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();
		}
		
	}
	short search(short prodID) {
		short count=0;
		boolean found=false;
		while(!found && count<Reccount) {
			found=(stockArray[count].ProductID==prodID);
			count++;
		}
		return((short)((found)?count-1:-1));
	}
	
	void deleproc() {
		JOptionPane.showInputDialog("Entered to Deleproc");
		if(Reccount==0) {
			JOptionPane.showInputDialog("Stock Database is empty! add some valuable data");
			return;
		}
		short index=-1;
		boolean cont=true;
		short prodID=0;
		while(cont) {
			prodID=(new Scanner( JOptionPane.showInputDialog( " Enter ProductID to Delete ?" ))).nextShort();
			index=search(prodID);
			if(index==-1) {
				JOptionPane.showInputDialog("No such Product ID "+prodID);
			}
			else {
				System.out.println(index+"  "+stockArray[index].ProductID+stockArray[index].Productname+stockArray[index].Quantity+stockArray[index].Price);
				
				if((new Scanner(JOptionPane.showInputDialog(stockArray[index].ProductID+" "+stockArray[index].Productname+"  "+stockArray[index].Quantity+" "+stockArray[index].Price+
						" Wish to Delete? true/false"))).nextBoolean()) {
					StockData[] TstockArray=new StockData[100];
					System.arraycopy(stockArray, 0, TstockArray, 0, index);
					System.arraycopy(stockArray, index+1, TstockArray, index, Reccount-index);
					System.arraycopy(TstockArray, 0, stockArray, 0, 100);
					
				}
			}
			cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();
		}
		}
	void listproc() {
		JOptionPane.showInputDialog("Entered to Listproc");
		if(Reccount==0) {
			JOptionPane.showInputDialog("Stock Database is empty! add some valuable data");
			return;
		}
		
		for (StockData item : stockArray) {
			if(item ==null) {JOptionPane.showInputDialog("No more product int the Stock");break;}
			JOptionPane.showInputDialog(item.ProductID+" "+item.Productname+"  "+item.Quantity+" "+item.Price);
		}
	}
}
