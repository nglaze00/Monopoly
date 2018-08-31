/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

/**
 *
 * @author nglaz
 */

import java.util.ArrayList;
public class Trade {
	private Player proposer;
	private Player recipient;
	private int[] money;
	private ArrayList<Property> properties;
	
	public Trade(Player proposer, Player recipient) {
		this.proposer = recipient;
		this.recipient = recipient;
		this.money = new int[2];
		this.properties = new ArrayList<Property>();
	}
	
	public boolean proposePaidCash(int money) {
		if(money > proposer.money()) {
			System.out.println("Not enough money");
			return false;
		}
		this.money[0] = money;
		return true;
	}
	public boolean proposeRecievedCash(int money) {
		if(money > recipient.money()) {
			System.out.println("Not enough money");
			return false;
		}
		this.money[1] = money;
		return true;
	}
	public void proposeProperty
}
