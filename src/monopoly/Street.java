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
public class Street extends Property implements Landable {

    private int housePrice;
    private int houses;
    private int color;

    public Street(String name, int spot, int price, int[] rents, int mortgagePrice, int housePrice, int color) {
        super(name, spot, price, rents, mortgagePrice);
        this.housePrice = housePrice;
        this.houses = 0;
        this.color = color;
    }



    public int getColor() {
        return color;
    }
    public int houses(){
        return houses;
    }
    public int housePrice(){
        return housePrice;
    }
    public int color(){
        return color;
    }

    @Override
    public int calculateRent(Player owner, Player renter) {
        if(owner.ownsMonopoly(color) && houses == 0){
            return getRents()[0] * 2;
        }
        return getRents()[houses];
    }

    public boolean buyHouse(Player p) {
        if (houses > 4 || p.money() < housePrice) {
            return false;
        }
        houses += 1;
        p.changeMoney(-housePrice);
        return true;
    }

    public boolean sellHouse(Player p) {
        if (houses < 1) {
            return false;
        }
        houses -= 1;
        p.changeMoney(housePrice / 2);
        return true;
    }

}
