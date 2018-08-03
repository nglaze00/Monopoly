/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.util.Random;

/**
 *
 * @author nglaz
 */
public class Roller {
    public static Random r = new Random();
    
    public static int roll(){
        return r.nextInt(5) + r.nextInt(5) + 2;
    }
    public static int whoGoesFirst(int players){
        return r.nextInt(players);
    }
}
