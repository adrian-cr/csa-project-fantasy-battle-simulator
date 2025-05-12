package com.cognizant;

public class Warrior extends Character {
  public Warrior(String name) {
    super(name, 40, 70, 30, 50);
  }
  
  @Override
  public void specialAbility(Character target) {
    System.out.println(name + " uses Diamond Sword — Attacks " + target.name + " twice!");
    attack(target, 1);
    attack(target, 1);
  }
}
