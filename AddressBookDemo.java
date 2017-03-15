package addressbook;
/**
 *
 * @author Salman
 */

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddressBookDemo implements ActionListener
{
	ArrayList personsList;
   	PersonDAO pDAO;

   	JFrame appFrame;

   	JLabel jlbName, jlbAddress, jlbPhone, jlbEmail,jlbComent;
   	JTextField jtfName, jtfAddress, jtfPhone, jtfEmail;
   	JButton jbbSave, jbnDelete, jbnClear, jbnUpdate, jbnSearch,
   	jbnForward, jbnBack, jbnExit;
         
   	String name, address, email;
   	int phone;
   	int recordNumber;	 
   	Container cPane;
   public static void main(String args[]){
      new AddressBookDemo(); 
   }

   public AddressBookDemo()
   { 	
	    name    = "";
	    address = "";
	    email  = "";
	    phone   = -1 ;		//Stores 0 to indicate no Phone Number
	    recordNumber = -1;
	
	    createGUI();
	
	    personsList = new ArrayList();
	
	    // creating PersonDAO object
	    pDAO = new PersonDAO();	

   }

   	public void createGUI(){

   		//Create a frame and get its contentpane and set layout
   		appFrame = new JFrame("Address Book");

   		cPane = appFrame.getContentPane();
   		cPane.setLayout(new GridBagLayout());
   		
   		//Arrange components on contentPane and set Action Listeners to each JButton
   		arrangeComponents();
   		
   		appFrame.setSize(600,600);
   		appFrame.setResizable(false);
   		appFrame.setVisible(true);
   		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   	}
   	
   	public void arrangeComponents()
        {
                jlbComent=new JLabel("(Upper case Only)");
   		jlbName = new JLabel("Name");
   		jlbAddress = new JLabel("Address");
   		jlbPhone = new JLabel("Phone");
   		jlbEmail = new JLabel("Email");

   		jtfName    = new JTextField(20);
   		jtfAddress = new JTextField(20);
   		jtfPhone   = new JTextField(20);
   		jtfEmail   = new JTextField(20);

   		jbbSave   = new JButton("Save");
   		jbnDelete = new JButton("Delete");
   		jbnUpdate = new JButton("Update");
   		jbnSearch = new JButton("Search");

   	
   		/*add all initialized components to the container and position*/
        GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
        gridBagConstraintsx01.gridx = 5;
        gridBagConstraintsx01.gridy = 5;
        cPane.add(jlbComent, gridBagConstraintsx01);
        
         GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
        gridBagConstraintsx02.gridx = 0;
        gridBagConstraintsx02.gridy = 0;
        cPane.add(jlbName, gridBagConstraintsx02);
        
        GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
        gridBagConstraintsx03.gridx = 1; 
        gridBagConstraintsx03.gridy = 0;
        gridBagConstraintsx03.gridwidth = 2;
        gridBagConstraintsx03.fill = GridBagConstraints.BOTH;
        cPane.add(jtfName, gridBagConstraintsx03);
        
        GridBagConstraints gridBagConstraintsx04= new GridBagConstraints();
        gridBagConstraintsx04.gridx = 0;
        gridBagConstraintsx04.gridy = 1;
        cPane.add(jlbAddress, gridBagConstraintsx04);
        
        GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
        gridBagConstraintsx05.gridx = 1;
        gridBagConstraintsx05.gridy = 1;
        gridBagConstraintsx05.gridwidth = 2;
        gridBagConstraintsx05.fill = GridBagConstraints.BOTH;
        cPane.add(jtfAddress, gridBagConstraintsx05);
        
        GridBagConstraints gridBagConstraintsx06= new GridBagConstraints();
        gridBagConstraintsx06.gridx = 0;
        gridBagConstraintsx06.gridy = 2;
        cPane.add(jlbPhone, gridBagConstraintsx06);
        
        GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
        gridBagConstraintsx07.gridx = 1;
        gridBagConstraintsx07.gridy = 2;
        gridBagConstraintsx07.gridwidth = 2;
        gridBagConstraintsx07.fill = GridBagConstraints.BOTH;
        cPane.add(jtfPhone, gridBagConstraintsx07);
        
        GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
        gridBagConstraintsx08.gridx = 0;
        gridBagConstraintsx08.gridy = 3;
        cPane.add(jlbEmail, gridBagConstraintsx08);
        
        GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
        gridBagConstraintsx09.gridx = 1;
        gridBagConstraintsx09.gridy = 3;
        gridBagConstraintsx09.gridwidth = 2;
        gridBagConstraintsx09.fill = GridBagConstraints.BOTH;
        cPane.add(jtfEmail, gridBagConstraintsx09);
        
        GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
        gridBagConstraintsx10.gridx = 0;
        gridBagConstraintsx10.gridy = 4;
        cPane.add(jbbSave, gridBagConstraintsx10);
        
        GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
        gridBagConstraintsx11.gridx = 1;
        gridBagConstraintsx11.gridy = 4;
        cPane.add(jbnDelete, gridBagConstraintsx11);
        
        GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
        gridBagConstraintsx12.gridx = 2;
        gridBagConstraintsx12.gridy = 4;
        cPane.add(jbnUpdate, gridBagConstraintsx12);
        
    
        GridBagConstraints gridBagConstraintsx14 = new GridBagConstraints();
        gridBagConstraintsx14.gridx = 1;
        gridBagConstraintsx14.gridy = 5; 
        cPane.add(jbnSearch, gridBagConstraintsx14);
        
  
   		jbbSave.addActionListener(this);
   		jbnDelete.addActionListener(this);
   		jbnUpdate.addActionListener(this);
   		jbnSearch.addActionListener(this);
   		
        }
     
   	public void actionPerformed (ActionEvent e)
        {
   		
   		if (e.getSource () == jbbSave){
             savePerson();
            
        }

   		else if (e.getSource() == jbnDelete){
             deletePerson();
     
        }

   		else if (e.getSource() == jbnUpdate){
             updatePerson();
            
        }

   		else if (e.getSource() == jbnSearch){
             searchPerson();
        } 
        }
     // Save the  into the Address Book 
     public void savePerson(){  
	   	name    = jtfName.getText();
	   	name = name.toUpperCase();	//Save all names in Uppercase
	   	address = jtfAddress.getText();
	   	try{
	   		phone = Integer.parseInt(""+jtfPhone.getText());
	   	}catch(Exception e){
	   		System.out.print("Input is a string");
	   		JOptionPane.showMessageDialog(null, "Please enter Phone Number");
	   	}
	   	
	   	email   = jtfEmail.getText();
	
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

	   	name = jtfName.getText();
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
   	   name    = jtfName.getText();
   	   address = jtfAddress.getText();
   	   phone   = Integer.parseInt(jtfPhone.getText());
       email   = jtfEmail.getText();

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
    
	   	name = jtfName.getText();
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
	   		
	   		}
	   		else
	   		{
	   			/*downcast the object from array list to PersonInfo*/
	   			PersonInfo person = (PersonInfo) personsList.get(recordNumber);
	
	             // displaying search record in text fields 
	   			jtfName.setText(person.getName());
	   			jtfAddress.setText(person.getAddress());
	   			jtfPhone.setText(""+person.getPhone());
	   			jtfEmail.setText(person.getEmail());
	   		}
	   	}

     }



   }