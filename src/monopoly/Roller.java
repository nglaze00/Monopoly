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
    
    public static int[] roll(){
        int[] dice = {r.nextInt(5) + 1, r.nextInt(5) + 1};
    	//int[] test = {6, 4};
        //return test;
    	return dice;
    }
    public static int whoGoesFirst(int players){
        return r.nextInt(players);
    }
}
