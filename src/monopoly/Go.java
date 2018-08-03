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
public class Go extends Space implements Landable {

    public Go(String name, int spot) {
        super(name, spot);
    }

    @Override
    public void landOn(Player p, Scanner scan) {
        p.changeMoney(200);
    }


 



    
}
