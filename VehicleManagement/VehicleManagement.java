package vehiclemanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class VehicleManagement {

    public static void main(String[] args) {

        ArrayList<Vehicle> vList = new ArrayList<>();
        Scanner kazu = new Scanner (System.in);
        
            int choice;
            
        do {
            
            System.out.println("Menu: ");
            System.out.println("1. Add a vehicle");
            System.out.println("2. Display a list of vehicle details");
            System.out.println("3. Delete a vehicle");
            System.out.println("4. Sort vehicle list by age");
            System.out.println("5. Quit");
            
            System.out.print("Enter you Choice: ");
                choice = kazu.nextInt();
            kazu.nextLine();
            
                switch (choice) {
                
                    case 1:
                        CaseMethod.addVehicle(vList, kazu);
                        break;
                    case 2:
                        CaseMethod.displayVehicleDetails(vList);
                        break;
                    case 3:
                        CaseMethod.deleteVehicle(vList, kazu);
                        break;
                    case 4:
                        CaseMethod.sortVehicleListByAge(vList, kazu);
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
        }
        
        while (choice != 5);
        
            kazu.close();
    }
    
}
