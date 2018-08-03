/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Scanner;

/**
 *
 * @author nglaz
 */
public class Mortgage {


    public static void menu(Player p) {
        Scanner scan = new Scanner(System.in);
        if (p.getProperties().isEmpty()) {
            System.out.println("No properties to mortgage!");
            return;
        }
        while (true) {
            System.out.println("Mortgage menu; enter a number to toggle that property's status.");
            System.out.println("You have $" + p.getMoney());
            int count = 1;
            for (Property prop : p.getProperties()) {
                System.out.print(count + ": " + prop);
                if (prop.isMortgaged()) {
                    System.out.println(" is mortgaged. $" + Math.round(prop.getMortgagePrice() * 1.1) + " to unmortgage");
                } else {
                    System.out.println(" not mortgaged. $" + prop.getMortgagePrice() + " to mortgage");
                }
                count++;
            }
            System.out.println(count + ": Exit");
            try {
                int choice = Integer.parseInt(scan.nextLine());
                if (choice == count) {
                    scan.close();
                    return;
                }
                Property toggled = p.getProperties().get(choice);
                if (!toggled.changeMortgage(p)) {
                    System.out.println("You're too poor for that!");
                }

            } catch (IllegalArgumentException e) {
            }
        }

    }
}
