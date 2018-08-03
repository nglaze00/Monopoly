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
public class CardSpace extends Space{

    String type;
    public CardSpace(String name, int spot, String type) {
        super(name, spot);
        this.type = type;
    }

    public void drawCard(Player p){         //unsupported
        switch(type){
            case "c":
                System.out.println("You do nothing");
                break;
            case "cc":
                System.out.println("You do nothing");
                break;
        }
    }

    @Override
    public void landOn(Player p, Scanner scan) {
        drawCard(p);
    }
    

    
}
