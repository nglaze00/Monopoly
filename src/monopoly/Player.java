/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author nglaz
 */
public class Player implements Comparable<Player> {

	private String name; // player stuff
	private int turnOrder;
	private int money;
	private HashMap<String, Property> properties;
	private int[] streetsOwned;
	private boolean bankrupt;

	private int inJail; // board stuff
	private int spot;
	private int[] lastRoll;
	private int doubles;

	public Player(String name) {
		this.name = name;
		money = 1500;
		properties = new HashMap<>();
		streetsOwned = new int[8];
		bankrupt = false;
		;
		inJail = -1;
		spot = 0;
		doubles = 0;
	}

	public void setTurnOrder(int n) {
		turnOrder = n;
	}

	public int getTurnOrder() {
		return turnOrder;
	}

	public void buyProperty(Property p) {
		if (money < p.getPrice()) {
			System.out.println("Too expensive! Mortgage / trade to get " + (p.getPrice() - money) + " more money.");
			return;
		}
		properties.put(p.getName(), p);
		p.changeOwner(this);
		money -= p.getPrice();
		if (p instanceof Street) {
			streetsOwned()[((Street) p).getColor()]++;
		}
	}

	public int[] streetsOwned() {
		return streetsOwned;
	}

	public void move(int spot) {
		this.spot = spot;
	}

	public int[] roll(Scanner scan) {
		System.out.println(this + ", press enter to roll!");
		scan.nextLine();
		int[] roll = Roller.roll();
		lastRoll = roll;
		return roll;
	}

	public int lastRoll() {
		return lastRoll[0] + lastRoll[1];
	}

	public void advance(int spaces) {
		spot = (spaces + spot) % 40;
	}

	public int compareTo(Player compared) {
		if (this.turnOrder > compared.getTurnOrder()) {
			return 1;
		} else {
			return -1;
		}
	}

	public ArrayList<Property> getProperties() {
		ArrayList<Property> properties = new ArrayList<>();
		for (Property prop : this.properties.values()) {
			properties.add(prop);
		}
		return properties;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getInfo() {
		String s = "";
		s += name + "'s turn. Money: " + money() + "\n" + "Properties: ";

		for (Property prop : getProperties()) {
			s += (prop + ", ");
		}

		return s + "\n";
	}

	int getSpot() {
		return spot;
	}

	int money() {
		return money;
	}

	int inJail() {
		return inJail;
	}

	void turnInJail() {
		inJail++;
	}

	void leaveJail() {
		inJail = -1;
	}

	boolean changeMoney(int i) {
		if (money + i < 0) {
			return false;
		}
		money += i;
		return true;
	}

	void buyHouses() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	void sellHouses() {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	boolean ownsMonopoly(int color) {
		int owned = 0;
		for (Property p : properties.values()) {

			if (p instanceof Street) {
				if (((Street) p).color() == color) {
					owned++;
				}
			}
		}
		if (color == 0 || color == 7) {
			if (owned == 2) {
				return true;
			}
		} else if (owned == 3) {
			return true;
		}
		return false;
	}

	public void rolledDifferent() {
		doubles = 0;
	}

	public void rolledDoubles() {
		doubles += 1;
	}

	public int doubles() {
		return doubles;
	}

}
