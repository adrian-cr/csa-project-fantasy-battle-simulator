package com.cognizant;

public class Goblin extends Character {
  public Goblin(String name) {
    super(name,  30, 100, 10, 30);
  }//Goblin - constructor
  
  @Override
  public void specialAbility(Character target) {
    System.out.println(name + " uses Sequoia Club — Doubles its attack power!");
    attack(target, 2);
  }
}
