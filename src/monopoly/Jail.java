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
public class Jail extends Space implements Landable {

	private static int jailSpot;
	private String type;

	public Jail(String name, int spot, String type) {
		super(name, spot);
		this.type = type;
	}

	public void landOn(Player p, Scanner scan) {
		
		if (type.equals("j")) {
			if (p.inJail() == -1) {
				System.out.println("Just visiting!");
			} else {
				p.turnInJail();
				if (p.inJail() < 3) {
					menu:
					while (true) {
						System.out.println(p + " is in jail. Turn in jail: " + p.inJail());
						System.out.println("1: Pay $50 to get out");
						System.out.println("2: Roll for doubles");
						try {
							switch (Integer.parseInt(scan.nextLine())) {
							case 1:
								if (p.changeMoney(-50)) {
									p.leaveJail();
									System.out.println(p + " paid $50 to get out of jail.");
									return;
								}
								System.out.println("Not enough money!");
								break;
							case 2:
								int[] dice = Roller.roll();
								if (dice[0] == dice[1]) {
									p.leaveJail();
									return;
								}
								System.out.println("You rolled a " + (dice[0] + dice[1]) + ". Stay in jail");
								break menu;
							}
						} catch (Exception e) {
						}

					}
				} else {
					System.out.println(p + " paid $50 to leave jail.");
					p.leaveJail();
					return;
				}
			}
		} else {
			System.out.println("Go directly to jail.");
			p.turnInJail();
			p.move(jailSpot);
		}
	}

	public static void setSpot(int spot) {
		System.out.println("Jail on spot " + spot);
		jailSpot = spot;
	}

	public static int spot() {
		return jailSpot;
	}

}
