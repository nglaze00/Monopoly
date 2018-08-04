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
public abstract class Property extends Space {

    private Player owner;
    private int[] rents;
    private boolean mortgaged;
    private int price;
    private int mortgagePrice;

    public Property(String name, int spot, int price, int[] rents, int mortgagePrice) {
        super(name, spot);
        owner = null;
        this.rents = rents;
        mortgaged = false;
        this.price = price;
        this.mortgagePrice = mortgagePrice;
    }

    public void changeOwner(Player buyer) {
        this.owner = buyer;
    }

    public boolean isOwned() {
        return owner != null;
    }

    int getPrice() {
        return price;
    }
    public int[] getRents(){
        return rents;
    }

    Player getOwner() {
        return owner;
    }
    boolean changeMortgage(Player p) {
        if(mortgaged){
            return unmortgage(p);
        }
        
        return mortgage(p);
    }
    
    public boolean isMortgaged(){
        return mortgaged;
    }
    public int getMortgagePrice(){
        return mortgagePrice;
    }
    public boolean mortgage(Player p){
        if(this instanceof Street){
            if(((Street)this).houses() != 0){
                return false;
            }
        }
        mortgaged = true;
        p.changeMoney(mortgagePrice);
        return true;
    }
    public boolean unmortgage(Player p){
        if(p.changeMoney((int) (-mortgagePrice * 1.1))){
            mortgaged = false;
            return true;
        }
        return false;
    }
    
    public abstract int calculateRent(Player owner, Player renter);
    public boolean payRent(Player owner, Player renter, int rent){
        if(!renter.changeMoney(-rent)){
            return false;
        }
        owner.changeMoney(rent);
        System.out.println("You paid $" + rent + " to " + owner);
        return true;
    }

    public void landOn(Player p, Scanner scan) {
        if (!isOwned()) {
            System.out.println("Buy? Price: " + getPrice());
            System.out.print("y / n :  ");
            if (scan.nextLine().trim().equalsIgnoreCase("y")) {
                p.buyProperty(this);
            }
        } else {

            if (getOwner() == p) {
                return;
            } else {
                int rent = calculateRent(getOwner(), p);
                payRent(getOwner(), p, rent);
            }
        }
    }



    @Override
    public String toString(){
        return this.name;
    }

    
    
}
