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
public class Railroad extends Property implements Landable{
    
    public Railroad(String name, int spot, int price, int[] rents, int mortgagePrice) {
        super(name, spot, price, rents, mortgagePrice);
    }
    public static int numberOwnedBy(Player player){
        int num = 0;
        for(Property p : player.getProperties()){
            if(p instanceof Railroad){
                num++;
            }
        }
        return num;
    }
    @Override
    public int calculateRent(Player owner, Player renter) {
        int rrOwned = 0;
        for(Property p : owner.getProperties()){
            if(p instanceof Railroad){
                rrOwned++;
            }
        }
        return getRents()[rrOwned - 1];
    }


}
