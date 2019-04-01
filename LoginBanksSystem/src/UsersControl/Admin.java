package UsersControl;

import excelReadWrite.DataBase;

public class Admin extends User{
    
    public Admin(DataBase db) {
        super(db);
    }
    
    public void createAdmin(String Username, String password) {
		createUser(Username, password, true);
		System.out.println("successfully created an admin account..");
    }
   
    public void changeUserToAdmin() {
		System.out.print("Enter a username to change to an admin : ");
		String userName;
		userName=scan.nextLine();
		while(!isUsername(userName=scan.nextLine())) {
			System.out.print("This isn't a user, please try again: ");
		}
		if(isAdmin(userName)) {
			System.out.println("User is already an admin");
		}
		else {
			DataB.writeInCell(getUsernameID(userName), 2, true);
			System.out.println("User is now an admin");
		}
    }
    
    public void deleteUser() {
		System.out.print("Enter the number of the user you want to delete : ");
		int num = scan.nextInt();
		while(num > DataB.getSheet().getLastRowNum() || num < 0 || num == getUsernameID(LoggedInUserName)+1) {
			if(num == getUsernameID(LoggedInUserName)+1) {
				System.out.print("You can't delete yourself, please try a different number : ");
			}
			else {
			System.out.print("Invalid user number, please try again : ");
			}
			num = scan.nextInt();
		}
		DataB.deleteRow(num);
    }
}
