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
public class Utility extends Property implements Landable{

    public Utility(String name, int spot, int price, int[] rents, int mortgagePrice) {
        super(name, spot, price, rents, mortgagePrice);
    }

    
    public int calculateRent(Player owner, Player renter) {
        int utilOwned = 0;
        for(Property p : owner.getProperties()){
            if(p instanceof Utility){
                utilOwned++;
            }
        }
        return getRents()[utilOwned - 1] * renter.lastRoll();
    }





    
}
