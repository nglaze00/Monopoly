/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author nglaz
 */
class MonopolyGame {

    private ArrayList<Player> players;
    private HashMap<String, Space> spaces;
    private ArrayList<Space> spaceSpots;
    private static Scanner io = new Scanner(System.in);
    private static Scanner scan = new Scanner(System.in);

    public static ArrayList<Integer> monopolyNumbers = new ArrayList<>();

    public MonopolyGame() {
        players = new ArrayList<>();
        spaces = new HashMap<>();
        spaceSpots = new ArrayList<>();
        
        addMonopolyNumbers();
        addPlayers();
        try {
            addSpaces("assets/spaces.txt");
            System.out.println("Game set up successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("setup file not found; quitting...");
            System.exit(0);
        }

        rollForTurnOrder();
    }

    public void play() {
        int whoseTurn = 0;
        while (true) {
            turn(whoseTurn);
            if (whoseTurn / players.size() > 35) {                //turn limit
                break;
            }
            whoseTurn++;
        }

    }

    public void turn(int whoseTurn) {
        Player p = players.get(whoseTurn % players.size());

        System.out.println("\n" + p.getInfo());
        p.advance(p.roll(scan));
        Space space = spaceSpots.get(p.getSpot());
        System.out.println(p + " rolled a " + p.lastRoll() + " and landed on " + space.getName());

        space.landOn(p, scan);

        while (true) {
            System.out.println("\n" + p.getInfo());
            System.out.println("Do what?\n"
                    + "1. Trade\n"
                    + "2. Mortgage menu\n"
                    + "4. Buy houses\n"
                    + "5. Sell houses\n"
                    + "6. End turn");
            try {
                switch (Integer.parseInt(scan.nextLine())) {
                    case 1:
                        //implement trading
                        break;
                    case 2:
                        Mortgage.menu(p);
                        break;
                    case 4:
                        p.buyHouses();//implement
                        break;
                    case 5:
                        p.sellHouses();//implement
                        break;
                    case 6:
                        return;

                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
            }
        }

    }

    public void goBankrupt(Player p) {
        //add stuff
    }

    public void addPlayers() {                  //add bank player
        System.out.print("How many players? (max 4)");
        int num = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < num; i++) {
            addPlayer(scan);
            if (i == 3) {
                break;
            }
        }
    }

    private void addPlayer(Scanner scan) {
        System.out.print("Player name: ");
        String name = scan.nextLine();
        players.add(new Player(name));
    }

    public Space getSpace(String name) {
        return spaces.get(name);
    }

    public Space getSpace(int spot) {
        return spaceSpots.get(spot);
    }

    void addSpaces(String fileName) throws FileNotFoundException {      //all the rents and board setup nonsense i don't really want to do
        File space = new File(fileName);
        int spot = 0;
        io = new Scanner(space);
        while (io.hasNextLine()) {

            String[] line = io.nextLine().split(",");
            switch (line[1]) {
                case "s":
                    int[] rents = Arrays.stream(line[3].split(" ")).mapToInt(Integer::parseInt).toArray();
                    this.spaces.put(line[0], new Street(line[0], spot, Integer.parseInt(line[2]), rents, Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6])));
                    spaceSpots.add(this.spaces.get(line[0]));
                    monopolyNumbers.set(Integer.parseInt(line[6]), monopolyNumbers.get(Integer.parseInt(line[6])) + 1);
                    break;
                case "cc":
                case "c":
                    this.spaces.put(line[0], new CardSpace(line[0], spot, line[1]));
                    spaceSpots.add(this.spaces.get(line[0]));
                    break;
                case "r":
                    this.spaces.put(line[0], new Railroad(line[0], spot, Integer.parseInt(line[2]), new int[]{50, 100, 150, 200}, 100));
                    spaceSpots.add(this.spaces.get(line[0]));
                    break;
                case "u":
                    Utility u = new Utility(line[0], spot, Integer.parseInt(line[2]), new int[]{4, 10}, 75);
                    this.spaces.put(line[0], u);
                    spaceSpots.add(u);
                    break;
                case "g":
                case "gj":
                    this.spaces.put(line[0], new Jail(line[0], spot));
                    spaceSpots.add(this.spaces.get(line[0]));
                    break;
                case "j":
                    this.spaces.put("Jail", new Jail("Jail", spot));
                    spaceSpots.add(this.spaces.get("Jail"));
                    break;
                case "i":
                case "l":
                    this.spaces.put(line[0], new TaxSpace(line[0], spot, line[1]));
                    spaceSpots.add(this.spaces.get(line[0]));
                    break;
                case "f":
                    this.spaces.put(line[0], new FreeParking(line[0], spot));
                    spaceSpots.add(this.spaces.get(line[0]));
                    break;
            }
            spot++;
        }
        io.close();
        System.out.println(spaceSpots);
    }

    public void rollForTurnOrder() {
        
        ArrayList<Player> tied = (ArrayList<Player>) players.clone();
        int max;
        while (tied.size() > 1) {
            System.out.println("Players " + tied + " rolling for turn order:");
            max = 0;
            for (Player p : tied) {
                p.roll(scan);
                System.out.println("Player " + p + " rolled a " + p.lastRoll());
                if (p.lastRoll() > max) {
                    max = p.lastRoll();
                }
            }
            for (int i = 0; i < tied.size(); i++) {
                if (tied.get(i).lastRoll() < max) {
                    tied.remove(tied.get(i));
                    i--;
                }
            }
        }
        int firstPlayer = players.indexOf(tied.get(0));
        for (int turn = 0; turn < players.size(); turn++) {
            players.get(firstPlayer % players.size()).setTurnOrder(turn);
            firstPlayer += 1;
        }
        Collections.sort(players);
        System.out.println("Turn order: " + players);
    }

    /*public void setTurnOrder() {
        int first = Roller.whoGoesFirst(players.size());
        int i = first;

        for (int turn = 0; turn < players.size(); turn++) {
            players.get(i % players.size()).setTurnOrder(turn);

            i += 1;

        }
        Collections.sort(players);
        System.out.println("Turn order: " + players);
    }*/
    void end() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addMonopolyNumbers() {
        for (int i = 0; i < 40; i++) {
            monopolyNumbers.add(0);
        }
    }

}
