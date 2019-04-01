package UsersControl;

import java.util.Random;
import java.util.Scanner;

import excelReadWrite.*;


public class User {
	
	protected Scanner scan = new Scanner(System.in);
	
	protected DataBase DataB;
	
	public boolean loggedIn = false;
	
	public static String LoggedInUserName;
	public static String LoggedInPassword;
	public static boolean LoggedInIsAdmin;
	
	public User(DataBase db) {
		DataB = db;
                
	}
        
	public int Login(String userName, String password) {
		
		if(isUsername(userName) && isPassword(password, userName)) {
			if(isAdmin(userName)) {
				LoggedInUserName = userName;
				LoggedInPassword = password;
				LoggedInIsAdmin = true;
				loggedIn = true;
                                
                                return 1;
			}
			else {
				LoggedInUserName = userName;
				LoggedInPassword = password;
				LoggedInIsAdmin = false;
				loggedIn = true;
                                
                                return 2;
			}
		}
		else {
                        return -1;
		}
	}
	
	public void createUser(String username, String password, boolean admin) {
		Object[] temp = {username, password, admin};
		DataB.writeInNewRow(temp);
	}
	
	public boolean isUsername(String username) {
		for(int i = 0; i <= DataB.getRowCount(); i++ ) {
			if(DataB.getSheet().getRow(i).getCell(0).getStringCellValue().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public int getUsernameID(String username) {
		for(int i = 0; i <= DataB.getRowCount(); i++ ) {
			if(DataB.getSheet().getRow(i).getCell(0).getStringCellValue().equals(username)) {
				return i;
			}
		}
		return -1;
	}
        
	public boolean isPassword(String password, String username) {
		
			if(DataB.getSheet().getRow(getUsernameID(username)).getCell(1).getStringCellValue().equals(password)) {
				return true;
			}
		
		return false;
	}
	
	public boolean isAdmin(String userName) {
		if(isUsername(userName)) {
			return DataB.getSheet().getRow(getUsernameID(userName)).getCell(2).getBooleanCellValue();
		}
		else return false;
	}
	
	public String generateCode() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
    public void changeUsername() {
		int temp = getUsernameID(LoggedInUserName);
		System.out.print("new username : ");
		String UserName = scan.nextLine();
		while(isUsername(UserName)) {
			System.out.print("Username is already taken, please try again :");
                        UserName = scan.nextLine();
		}
		
		LoggedInUserName = UserName;
		DataB.writeInCell(temp, 0, UserName);
    }
	
    public void changePassword() {
		int temp = getUsernameID(LoggedInUserName);
		System.out.print("new password : ");
		//scan.next();
		String password = scan.nextLine();
		while(password.equals(LoggedInPassword)) {
			System.out.print("New password can't be the same as the old one : ");
			password = scan.nextLine();
		}
		
		LoggedInPassword = password;
		DataB.writeInCell(temp, 1, password);
    }
	
    public void LogOut() {
		loggedIn = false;
                LoggedInUserName = null;
                LoggedInPassword = null;
		LoggedInIsAdmin = false;
                
    }
}

