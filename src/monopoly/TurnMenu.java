/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nglaz
 */
public class TurnMenu {

    public static void mortgage(Player p) {
        Scanner scan = new Scanner(System.in);
        if (p.getProperties().isEmpty()) {
            System.out.println("No properties to mortgage!");
            return;
        }
        while (true) {
            System.out.println("Mortgage menu; enter a number to toggle that property's status.");
            System.out.println("You have $" + p.money());
            int count = 1;
            for (Property prop : p.getProperties()) {
                System.out.print(count + ": " + prop);
                if (prop.isMortgaged()) {
                    System.out.println(" is mortgaged. $" + Math.round(prop.getMortgagePrice() * 1.1) + " to unmortgage");
                } else if (prop instanceof Street) {
                    if (((Street) prop).houses() > 0) {
                        System.out.println(" can't be mortgaged. Sell houses first.");
                    }
                } else {
                    System.out.println(" is not mortgaged. $" + prop.getMortgagePrice() + " to mortgage");
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

    public static void houses(Player p) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Street> streets = new ArrayList<Street>();
        for (Property prop : p.getProperties()) {
            if (prop instanceof Street) {
                streets.add((Street) prop);
            }
        }
        if (streets.isEmpty()) {
            System.out.println("No streets to buy/sell houses on!");
            return;
        }
        while (true) {
            System.out.println("Select a property to buy/sell houses on.");
            int count = 1;
            for (Street street : streets) {
                System.out.print(count + ": " + street);        //UNFINISHED
                if (street.isMortgaged()) {
                    System.out.println(" is mortgaged. Can't buy houses.");
                } else {
                    System.out.println(" (current houses: " + street.houses() + ")");
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
                Street chosen = streets.get(choice - 1);
                while (true) {
                    String h;
                    if (chosen.houses() == 5) {
                        h = "a hotel";
                    } else if (chosen.houses() == 1) {
                        h = chosen.houses() + " house";
                    } else {
                        h = chosen.houses() + " houses";
                    }
                    System.out.println(chosen + " has " + h);
                    System.out.println("You have $" + p.money());
                    if (chosen.houses() != 5) {
                        System.out.println("1: buy a house for " + chosen.housePrice());
                    }
                    if (chosen.houses() != 0) {
                        System.out.println("2: sell a house for " + chosen.housePrice() / 2);
                    }
                    System.out.println("3: Go back");
                    try {
                        choice = Integer.parseInt(scan.nextLine());
                        if (choice == count) {
                            scan.close();
                            break;
                        }
                        if (choice == 1 && chosen.houses() != 5) {
                            if(!chosen.buyHouse(p)){
                                System.out.println("You can't do that!");
                            }
                        } else if(choice == 2 && chosen.houses() != 0){
                            if(!chosen.sellHouse(p)){
                                System.out.println("You can't do that!");
                            }
                        }

                    } catch (IllegalArgumentException e) {
                    }
                }

            } catch (IllegalArgumentException e) {
            }
        }
    }
}
