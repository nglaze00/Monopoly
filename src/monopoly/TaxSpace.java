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
public class TaxSpace extends Space implements Landable{
    String type;
    public TaxSpace(String name, int spot, String type) {
        super(name, spot);
        this.type = type;
    }

    @Override
    public void landOn(Player p, Scanner scan) {
        if(type == "i"){
            p.changeMoney(-200);
            System.out.println("You paid $200.");
        }
        else{
            p.changeMoney(-75);
            System.out.println("You paid $75.");
        }
    }
    
}
