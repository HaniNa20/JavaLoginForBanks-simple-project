package UsersControl;

import excelReadWrite.DataBase;

public class Employee extends User{
    
    DataBase acc;
    
    public Employee(DataBase db, DataBase acc) {
        super(db);
        this.acc = acc;
    }
    
    public void addClient() {
        String code = generateCode();
        System.out.print("Client code : " + code);
        System.out.println();
        
        String name;
        System.out.print("Client full name : ");
        name = scan.nextLine();
        
        int num;
        System.out.print("Client Number : ");
        num = scan.nextInt();
        scan.nextLine();
        System.out.println();
        
        Object temp[] = {code, name, num, 0};
        acc.writeInNewRow(temp);
        
    }
    
    public boolean isClient(String code) {
		for(int i = 0; i <= acc.getRowCount(); i++ ) {
			if(acc.getSheet().getRow(i).getCell(0).getStringCellValue().equals(code)) {
				return true;
			}
		}
		return false;
    }
    
    public int getClientID(String code) {
		for(int i = 0; i <= acc.getRowCount(); i++ ) {
			if(acc.getSheet().getRow(i).getCell(0).getStringCellValue().equals(code)) {
				return i;
			}
		}
		return -1;
	}
    
    public void addMoney() {
        String usrCode;
        System.out.print("Enter the code of the client : ");
        usrCode = scan.nextLine();
        if(isClient(usrCode)) {
            int temp;
            int tempx = (int) acc.getCellValue(getClientID(usrCode), 3);
            System.out.print("add : ");
            temp = scan.nextInt();
            scan.nextLine();
            temp+=tempx;
            acc.writeInCell(getClientID(usrCode), 3, temp);
            System.out.println("Added successfully, the new balance is : " + temp);
        }
        else {
            System.out.println("This user does not exist");
        }
        
        
    }
    
}
