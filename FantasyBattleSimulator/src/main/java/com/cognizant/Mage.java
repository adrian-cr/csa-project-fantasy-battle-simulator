package com.cognizant;

public class Mage extends Character {
  public Mage(String name) {
    super(name, 10, 40, 20, 80);
  }
  
  @Override
  public void specialAbility(Character target) {
    System.out.println(name + " uses Sorcery — Heals twice!");
    heal(1);
    heal(1);
  }
}
