package mainPackage;

import excelReadWrite.*;
import UsersControl.*;

import static java.lang.System.exit;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public DataBase Users_db = new DataBase("Efile.xlsx", "sheet1");
        public DataBase Banks_Accounts = new DataBase("Efile2.xlsx", "Accounts");
	public User usr = new User(Users_db);
	private final Scanner scan = new Scanner(System.in);
	
	public void mainMenu() {
		int choice;
                int temp;
		while(true) {
		try {
		System.out.println("1 : Login");
		System.out.println("2 : Register");
                System.out.println("3 : Exit");
		choice = scan.nextInt();
                scan.nextLine();
		switch(choice) {
			case 1:
                            System.out.print("Username : ");
                            String userName;
                            userName = scan.nextLine();
                            System.out.print("Password : ");
                            String passWord;
                            passWord = scan.nextLine();
                            temp = usr.Login(userName, passWord);
                switch (temp) {
                    case 1:
                        usr = new Admin(Users_db);
                        Admin ad = Admin.class.cast(usr);
                        ad.loggedIn = true;
                        
                        while(ad.loggedIn) {
                            System.out.println("1 : create an admin");
                            System.out.println("2 : delete a user");
                            System.out.println("3 : change a user to an admin");
                            System.out.println("4 : change my username");
                            System.out.println("5 : change my password");
                            System.out.println("6 : print all users data");
                            System.out.println("7 : Log out");
                            int choice2 = scan.nextInt();
                            scan.nextLine();
                            switch(choice2) {
                                case 1:
                                    System.out.print("Username : ");
                                    String UserName;
                                    UserName = scan.nextLine();
                                    while(ad.isUsername(UserName = scan.nextLine())) {
                                    System.out.println("Username is already taken, please try again.");
                                    }
                                    System.out.print("password : ");
                                    String password = scan.nextLine();
                                    ad.createAdmin(UserName, password);
                                    break;
				
                                case 2:
                                    ad.deleteUser();
                                    break;
                                    
                                case 3:
                                    ad.changeUserToAdmin();
                                    break;
			
                                case 4:
                                    ad.changeUsername();
                                    break;
				
                                case 5:
                                    ad.changePassword();
                                    break;
				
                                case 6:
                                    Users_db.printAllData();
                                    break;
                                    
                                case 7:
                                    ad.LogOut();
                                    break;
				
                                default:
                                    System.out.println("Invalid Input, please try again.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        usr = new Employee(Users_db, Banks_Accounts);
                        Employee em = Employee.class.cast(usr);
                        em.loggedIn = true;
                        int choice2, choicex;
                        while(em.loggedIn) {
                            System.out.println("1 : Edit my data");
                            System.out.println("2 : Add client");
                            System.out.println("3 : Add money to a client's account");
                            System.out.println("4 : Print all clients data");
                            System.out.println("5 : Logout");
                            choice2 = scan.nextInt();
                            scan.nextLine();
                            switch(choice2) {
                                case 1:
                                    while(true) {
                                    System.out.println("1 : change my username");
                                    System.out.println("2 : change my password");
                                    System.out.println("3 : Back");
                                    choicex = scan.nextInt();
                                    scan.nextLine();
                                    switch(choicex) {
                                        case 1 : 
                                            em.changeUsername();
                                            break;
                                        case 2 :
                                            em.changePassword();
                                            break;
                                    }
                                    if(choicex == 3) break;
                                    }
                                    break;
                                    
                                case 2:
                                    em.addClient();
                                    break;
                                    
                                case 3:
                                    em.addMoney();
                                    break;
                                 
                                case 4:
                                    Banks_Accounts.printAllData();
                                    break;
                                 
                                case 5:
                                    em.LogOut();
                                    break;
                            }
                        }
                        break;
                    default:
                        System.out.println("Wrong username or password");
                        break;
                }
				break;
			case 2:
                            System.out.print("Username : ");
                            String UserName;
                            while(usr.isUsername(UserName = scan.nextLine())) {
                                System.out.print("Username is already taken, please try again :");
                            }
                            System.out.print("password : ");
                            String password = scan.nextLine();
                            usr.createUser(UserName, password, false);
                            System.out.println("successfully registered..");
			break;
			
                        case 3:
                            exit(0);
                            break;
                        
			/*default:
                            System.out.println("Invalid Input");
                            break;*/
			}
		//clearConsole();
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid Input");
                    }
		
		}
		
	}
	
	/*public void clearConsole()
	{
		String clearScreenCommand = null;
		
		if( System.getProperty( "os.name" ).startsWith( "Window" ) )
		
		    clearScreenCommand = "cls";
		
		else
		
		    clearScreenCommand = "clear";
		
		 
		
		try {
			Runtime.getRuntime().exec( clearScreenCommand );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public static void main(String[] args){
		new Main().mainMenu();
	}
}
