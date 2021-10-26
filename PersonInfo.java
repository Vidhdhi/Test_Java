import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.File;  
import java.io.FileNotFoundException;  


public class PersonInfo {

    public static void main(String[] args) throws IOException{
        Person st1=new Person();
        st1.menu();
    }
}

class PersonData {
    short PersonID;
    String Name;
    String Address;
    String Number;
}

class Person {
    PersonData[] personArray;

    public Person() {
        personArray =new PersonData[100];
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

            personArray[count]=new PersonData();
            personArray[count].PersonID =(new Scanner( JOptionPane.showInputDialog( " Enter PersonId ?" ))).nextShort();
            personArray[count].Name =(new Scanner( JOptionPane.showInputDialog( " Enter  Name ?" ))).next();
            personArray[count].Address =(new Scanner( JOptionPane.showInputDialog( " Enter your Address ?" ))).next();
            personArray[count].Number =(new Scanner( JOptionPane.showInputDialog( " Enter Number ?" ))).next();

            cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();

            count++;
        }

        Reccount= count ;
    }


    void modiproc() {


        if(Reccount==0) {
            JOptionPane.showInputDialog("Person Database is empty! add some valuable data");
            return;
        }
        short index=-1;
        boolean cont=true;
        short prodID=0;

        while(cont) {
            prodID=(new Scanner( JOptionPane.showInputDialog( " Enter PersonId to view ?" ))).nextShort();
            index=search(prodID);
            if(index==-1) {
                JOptionPane.showInputDialog("No such PersonId "+prodID);
            }
            else {
                System.out.println(index+"  "+ personArray[index].PersonID + personArray[index].Name + personArray[index].Address + personArray[index].Number);
                String temp= personArray[index].Name +"  "+ personArray[index].Address +" "+ personArray[index].Number +
                        " Wish to Modify? true/false";
                if((new Scanner(JOptionPane.showInputDialog(temp))).nextBoolean()) {

                    personArray[index].PersonID =(new Scanner( JOptionPane.showInputDialog( personArray[index].PersonID))).nextShort();
                    personArray[index].Name =(new Scanner( JOptionPane.showInputDialog( personArray[index].Name))).next();
                    personArray[index].Address =(new Scanner( JOptionPane.showInputDialog( personArray[index].Address))).next();
                    personArray[index].Number =(new Scanner( JOptionPane.showInputDialog( personArray[index].Number))).next();
                }
            }
            cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();
        }

    }


    short search(short memberId) {
        short count=0;
        boolean found=false;
        while(!found && count<Reccount) {
            found=(personArray[count].PersonID ==memberId);
            count++;
        }
        return((short)((found)?count-1:-1));
    }



    void deleproc() {
        if(Reccount==0) {
            JOptionPane.showInputDialog("Person Database is empty! add some valuable data");
            return;
        }
        short index=-1;
        boolean cont=true;
        short prodID=0;
        while(cont) {
            prodID=(new Scanner( JOptionPane.showInputDialog( " Enter PersonId to view ?" ))).nextShort();
            index=search(prodID);
            if(index==-1) {
                JOptionPane.showInputDialog("No such PersonId "+prodID);
            }
            else {
                String temp= personArray[index].Name +"  "+ personArray[index].Address +" "+ personArray[index].Number +
                        " Wish to delete? true/false";
                if((new Scanner(JOptionPane.showInputDialog(temp))).nextBoolean()) {
                    PersonData[] myCachePerson = new PersonData[100];
                    boolean isDeleted = false;
                    short cacheCount = 0;
                    for (int i=0; i < Reccount; i++){
                        if (personArray[i].PersonID != prodID){
                            myCachePerson[cacheCount] = personArray[i];
                            cacheCount++;
                        }else {
                            isDeleted = true;
                        }
                    }
                    if (isDeleted){
                        Reccount = cacheCount;
                        personArray = myCachePerson;
                        //print out put on console
                        System.out.println("Deleted Success fully!");
                        for (int i=0; i < Reccount; i++){
                            System.out.println(personArray[i].PersonID);
                        }
                    }
                }
            }
            cont=(new Scanner( JOptionPane.showInputDialog( "true to Continue false to Terminate " ) )).nextBoolean();
        }
    }





    void listproc() {

        String listOfItems = "";
        for (PersonData iteam: personArray){
            if (iteam==null) {
                break;
            }
            listOfItems += iteam.PersonID+" "+iteam.Name+" "+iteam.Address+" "+iteam.Number+"\n";

        }

        if (listOfItems.isEmpty()){

            JOptionPane.showInputDialog("List Items are not found!");
        }

else {
            JOptionPane.showInputDialog(listOfItems);

        }

    }

}