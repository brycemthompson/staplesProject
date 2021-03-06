package MainPk;

import Customer_Application.Cust_UI;
import DBAdmin.DBAdminController;
import POS_Application.POS_UI;
import Vendor.Vendor_UI;
import StoreManager.StoreManagerController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Staples Multi-Purpose Database Application! Are you a...\n\n" +
                           "1. Administrator?\n" +
                           "2. Cashier?\n" +
                           "3. Customer?\n" +
                           "4. Store Manager?\n" +
                           "5. Vendor?");

        Scanner sc = new Scanner(System.in);
        boolean validInputReceived = false;
        int choice = 0;

        SQLExecutor executor = new SQLExecutor();
        executor.startConnection("sa", "");

        while(!validInputReceived) {
            try {
                choice = Integer.parseInt(sc.nextLine());

                validInputReceived = true;
            } catch(Exception e) {
                System.out.print("Invalid input, try again: ");
            }
        }

        switch (choice) {
            case 1:
                DBAdminController DBC = new DBAdminController(executor);
                DBC.startUp();
                break;
            case 2:
                // Run Cashier UI
                POS_UI pu = new POS_UI(executor);
                pu.run();
                break;
            case 3:
                // Run Customer UI
                Cust_UI cu = new Cust_UI(executor);
                cu.run();
                break;
            case 4:
                // Run Store Manager UI
                StoreManagerController SMC = new StoreManagerController(executor);
                SMC.startUp();
                break;
            case 5:
                // Run Vendor UI
                Vendor_UI v = new Vendor_UI(executor);
                v.main(null);
                break;
            default:
                System.out.print("Invalid choice, try again: ");
        }
        sc.close();
        executor.closeConnection();
    }
}
