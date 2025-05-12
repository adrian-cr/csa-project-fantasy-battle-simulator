package com.cognizant;

import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Main {
  public static String getAction(Character actor) {
    int input;
    while (true) {
      try {
        Scanner sc = new Scanner(System.in);
        printActionsMenu();
        input = sc.nextInt();
        if (asList(1, 2, 3).contains(input)) {
          if (input==3 && actor.turnsTillSpecial!=0)
            throw new InvalidActionException("\nPlease wait " + actor.turnsTillSpecial + " more turns before using a special action\n");
          return input==1? "attack" : input==2? "heal" : "special";
        }//if
      } catch (Exception e) {
        System.out.println("\nInvalid input. Try again.");
        continue;
      }//try-catch
      System.out.println("\nPlease enter a valid choice (1, 2)");
    }//while
  }
  public static String getTarget(Character actor, List<Character> players) {
    while (true) {
      try {
        Scanner sc = new Scanner(System.in);
        System.out.println("Who do you want to attack?");
        for (int i = 0; i < players.size(); i++) {
          System.out.println((i + 1) + ". " + players.get(i));
        }//for
        int option = sc.nextInt() - 1;
        if (option<1 || option>players.size() || players.get(option).equals(actor)) {
          throw new IllegalArgumentException("Please enter a valid choice.");
        }//if
        return players.get(option).name;
      } catch(Exception e){
          System.out.println("\nInvalid input. Try again.");
          try {Thread.sleep(1500);}catch (Exception ex) {}
      }//try-catch
    }//while
  }//getTarget()
  public static void printActionsMenu() {
    System.out.println("What would you like to do?");
    System.out.println("1. Attack");
    System.out.println("2. Heal");
    System.out.println("3. Use special ability");
  }//printActionsMenu()
  
  public static void main(String[] args) throws InterruptedException {
    BattleManager manager = new BattleManager();
    Scanner scanner = new Scanner(System.in);
    try {
      manager.addCharacter(new Warrior("Greld"));
      manager.addCharacter(new Mage("Kliodas"));
      manager.addCharacter(new Goblin("Derwel"));
      manager.addCharacter(new Elf("Milo"));
      
      System.out.println("Battle begins!");
      Thread.sleep(2000);
      int turnIdx = 0;
      while (manager.charactersInPlay.size()!=1) {
        try {
          Character character = manager.charactersInPlay.get(turnIdx);
          System.out.println("\nIt's " + character.name + "'s turn!\n");
          Thread.sleep(2000);
          String action = getAction(character);
          String targetName;
          if (asList(1,3).contains(action))
            targetName = getTarget(character, manager.charactersInPlay);
          
//          String[] parts = input.split(" ");
//          if (parts.length != 3) {
//            if (parts.length==2 && parts[1].equals("heal")) {
//              String actor = parts[0];
          manager.performAction(character.name, null, action);
          turnIdx = turnIdx==manager.charactersInPlay.size()-1? 0 : turnIdx+1;
//            }
//            else {
//              throw new IllegalArgumentException("Invalid input format.");
//            }
//          }
//          String actor = parts[0];
//          String target = parts[1];
//          String action = parts[2];
//          manager.performAction(actor, target, action);
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        } finally {
          System.out.println();
          Thread.sleep(2000);
        }
      }
      
    } finally {
      scanner.close();
      Character winner = manager.charactersInPlay.get(0);
      System.out.println(winner.name + "wins!");
    }
  }
}