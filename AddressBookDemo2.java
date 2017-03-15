/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

/**
 *
 * @author Salman
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class AddressBookDemo2 implements ActionListener
{
    ArrayList personList;
    String name,address,email;
    int phone;
    int recordNumber;
 public static void main (String args[])
 {
     new AddressBookDemo2();
 }
} 
public AddressBookDemo2()
{
name="";
address="";
email="";
phone=-1;
recordNumber=-1;

MyFrame();
personList=new ArrayList();
pDAO=new PersonDAO;
}
    class WindowSensor extends WindowAdapter
    {
        public void windowCloasing(WindowEvent we)
        {
            System.out.println("window is Closing");
            System.exit(0);
        }
    }
    

class MyFrame extends Frame
{
    public String msg;
    public TextField tf;
    public TextField tf2;
    public TextField tf3;
    public TextField tf4;
    public MyFrame()
    {
        super("AddressBook");
        msg="Window ";
        Button b=new Button("Name");
        Button b2=new Button("Address");
        Button b3=new Button("Phone");
        Button b4=new Button("Email");
        Button b5=new Button("Save");
        Button b6=new Button("Delete");
        Button b7=new Button("Uptade");
        Button b8=new Button("Search");
        
        add(b);
        add(tf);
        add(b2);
        add(tf2);
        add(b3);
        add(tf3);
        add(b4);
        add(tf4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        ButtonSensor bs=new ButtonSensor(this);
		b.addActionListener(bs);
                b1.addActionListener(bs);
                b2.addActionListener(bs);
                b3.addActionListener(bs);
                b4.addActionListener(bs);
                b5.addActionListener(bs);
                b6.addActionListener(bs);
                b7.addActionListener(bs);
                b8.addActionListener(bs);
                setSize(600,400);
		setLayout(new FlowLayout());
    }
    public void paint(Graphics ge)
    {
        ge.drawString(msg,20,70);
    }
    
}
class ButtonSensor implements ActionListener{
	MyFrame mf;
	public ButtonSensor(MyFrame f)
        {
		mf=f;
	}
  public void actionPerformed (ActionEvent e){
   		
   		if (f.e.getSource () == b5){
             savePerson();
             clear(); 
        }

   		else if (f.e.getSource() == b6){
             deletePerson();
             clear();
        }

   		else if (f.e.getSource() == b7){
             updatePerson();
             clear(); 
        }

   		else if (f.e.getSource() == b8){
             searchPerson();
        } 

   	       else if (f.e.getSource() == jbnClear){
                clear();
        }

   		else if (f.e.getSource() == jbnExit){			
   			System.exit(0);
   		}

   	}
}
 public void savePerson(){  
	   	name    = b.getText();
	   	name = name.toUpperCase();	//Save all names in Uppercase
	   	address = b2.getText();
	   	try{
	   		phone = Integer.parseInt(""+b3.getText());
	   	}catch(Exception e){
	   		/*System.out.print("Input is a string");
	   		JOptionPane.showMessageDialog(null, "Please enter Phone Number");*/
	   	}
	   	
	   	email   = b4.getText();
	
	   	if(name.equals("")){
	   		JOptionPane.showMessageDialog(null, "Please enter person name.");
	   	}else{
		   	  //create a PersonInfo object and pass it to PersonDAO to save it
		   	  PersonInfo person = new PersonInfo(name, address, phone, email);
		   	  pDAO.savePerson(person);
		   	  JOptionPane.showMessageDialog(null, "Person Saved");
	      }
     }

     public void deletePerson(){

	   	name = b.getText();
	   	name = name.toUpperCase();
	   	if(name.equals("")){
	   		JOptionPane.showMessageDialog(null,"Please enter person name to delete.");
	   	}
	   	else{
	   		//remove Person of the given name from the Address Book database
	   		int numberOfDeleted = pDAO.removePerson(name);
	   		JOptionPane.showMessageDialog(null, numberOfDeleted + " Record(s) deleted.");
	   	}
     }

    public void updatePerson(){
         if (recordNumber >= 0 && recordNumber < personsList.size())
         {
            PersonInfo person = (PersonInfo)personsList.get(recordNumber);

            int id = person.getId();

   	   /*get values from text fields*/            
   	   name    = bName.getText();
   	   address = b2Address.getText();
   	   phone   = Integer.parseInt(b3.getText());
           email= b4.getText();

   	   /*update data of the given person name*/
   	    person = new PersonInfo(id, name, address, phone, email);
            pDAO.updatePerson(person);

   	   JOptionPane.showMessageDialog(null, "Person info record updated successfully.");         
         }
         else
         {   
              JOptionPane.showMessageDialog(null, "No record to Update");  
         }
    } 
    
    //Perform a Case-Insensitive Search to find the Person

      public void searchPerson() {
    
	   	name = b.getText();
	   	name = name.toUpperCase();
	   	/*clear contents of arraylist if there are any from previous search*/
	   	personsList.clear();

	   	recordNumber = 0;
	
	   	if(name.equals("")){
	   		JOptionPane.showMessageDialog(null,"Please enter person name to search.");
	   	}
	   	else{
	   		/*get an array list of searched persons using PersonDAO*/
	   		personsList = pDAO.searchPerson(name);
	
	   		if(personsList.size() == 0){
	   			JOptionPane.showMessageDialog(null, "No records found.");
	   			//Perform a clear if no records are found.
	   			clear();
	   		}
	   		else
	   		{
	   			/*downcast the object from array list to PersonInfo*/
	   			PersonInfo person = (PersonInfo) personsList.get(recordNumber);
	
	             // displaying search record in text fields 
	   			b.setText(person.getName());
	   			b2.setText(person.getAddress());
	   			b3.setText(""+person.getPhone());
	   			b4.setText(person.getEmail());
	   		}
	   	}

     }