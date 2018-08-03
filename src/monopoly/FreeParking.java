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
public class FreeParking extends Space implements Landable {

    public FreeParking(String name, int spot) {
        super(name, spot);
    }
    @Override
    public void landOn(Player p, Scanner scan) {
        System.out.println("Free parking! Do nothing.");
    }



    
}
