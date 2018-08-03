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
public abstract class Space implements Landable {
    
    String name;
    int spot;
    public Space(String name, int spot){
        this.name = name;
        this.spot = spot;
    }
    public String getName() {
        return name;
    }

    public int getSpot() {
        return spot;
    }
    public String toString(){
        return this.name;
    }    
}
