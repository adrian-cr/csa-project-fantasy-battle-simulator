package com.cognizant;

public class Elf extends Character {
  public Elf(String name) {
    super(name, 10, 100, 30, 50);
  }//Elf - constructor
  
  @Override
  public void specialAbility(Character target) {
    System.out.println(name + " uses Forest Bow — Attacks " + target.name + " and heals!");
    attack(target, 1);
    heal(1);
  }
}