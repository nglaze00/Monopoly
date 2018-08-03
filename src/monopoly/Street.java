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
public class Street extends Property implements Landable{

    private int housePrice;
    private int houses;
    private int color;
    public Street(String name, int spot, int price, int[] rents, int mortgagePrice, int housePrice, int color) {
        super(name, spot, price, rents, mortgagePrice);
        this.housePrice = housePrice;
        this.houses = 0;
        this.color = color;
    }
    

    public boolean partOfMonopoly(Player owner){
        if(owner.streetsOwned()[color] == MonopolyGame.monopolyNumbers.get(color)){
            return true;
        }
        return false;
    }
    public int getColor(){
        return color;
    }

    @Override
    public int calculateRent(Player owner, Player renter) {
        if(partOfMonopoly(owner)){
            
        }
        return getRents()[houses];
    }




  



}
