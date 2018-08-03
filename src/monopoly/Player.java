/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author nglaz
 */
public class Player implements Comparable<Player> {

    private String name;                //player stuff
    private int turnOrder;
    private int money;
    private HashMap<String, Property> properties;
    private int[] streetsOwned;
    private boolean bankrupt;
    
    private boolean inJail;             //board stuff
    private int spot;
    private int lastRoll;

    public Player(String name) {
        this.name = name;
        money = 1500;
        properties = new HashMap<>();
        streetsOwned = new int[8];
        bankrupt = false;
        lastRoll = 0;
        inJail = false;
        spot = 0;
    }

    public void setTurnOrder(int n) {
        turnOrder = n;
    }

    public int getTurnOrder() {
        return turnOrder;
    }

    public void buyProperty(Property p) {
        if (money < p.getPrice()) {
            System.out.println("Too expensive! Mortgage / trade to get " + (p.getPrice() - money) + " more money.");
            return;
        }
        properties.put(p.getName(), p);
        p.changeOwner(this);
        money -= p.getPrice();
        if(p instanceof Street){
            streetsOwned()[((Street) p).getColor()]++;
        }
    }
    public int[] streetsOwned(){
        return streetsOwned;
    }
    public void move(int spot) {
        this.spot = spot;
    }
    public int roll(Scanner scan){
        System.out.println(this + ", press enter to roll!");
        scan.nextLine();
        int roll = Roller.roll();
        lastRoll = roll;
        return roll;
    }
    public int lastRoll(){
        return lastRoll;
    }
    public void advance(int spaces) {
        
        spot = (spaces + spot) % 40;
        
    }

    public int compareTo(Player compared) {
        if (this.turnOrder > compared.getTurnOrder()) {
            return 1;
        } else {
            return -1;
        }
    }

    public ArrayList<Property> getProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        for(Property prop : this.properties.values()){
            properties.add(prop);
        }
        return properties;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getInfo() {
        String s = "";
        s += name + "'s turn. Money: " + getMoney() + "\n" + "Properties: ";

        for (Property prop : getProperties()) {
            s += (prop + ", ");
        }

        return s + "\n";
    }

    int getSpot() {
        return spot;
    }

    int getMoney() {
        return money;
    }
    boolean inJail(){
        return inJail;
    }

    void changeMoney(int i) {
        money+=i;
    }

    void buyHouses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void sellHouses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
