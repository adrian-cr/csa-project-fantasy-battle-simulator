package com.cognizant;

import java.util.Random;

public abstract class Character {
  protected String name;
  protected int health = 200;
  protected int attackRangeStart;
  protected int attackRangeEnd;
  protected int healRangeStart;
  protected int healRangeEnd;
  protected int turnsTillSpecial = 0;
  
  public Character(String name, int attackRangeStart, int attackRangeEnd, int healRangeStart, int healRangeEnd) {
    this.name = name;
    this.attackRangeStart = attackRangeStart;
    this.attackRangeEnd = attackRangeEnd;
    this.healRangeStart = healRangeStart;
    this.healRangeEnd = healRangeEnd;
  }//Character - constructor
  
  /* Abstract methods: */
  public abstract void specialAbility(Character target);
  
  /* Instance methods: */
  public int getAttackPower() {
    return new Random().nextInt(attackRangeEnd-attackRangeStart) + attackRangeStart;
  }
  public int getHealPower() {
    return new Random().nextInt(healRangeEnd-healRangeStart) + healRangeStart;
  }
  public void attack(Character target, int factor) {
    if (!this.isAlive()) {
      throw new IllegalStateException(name + " is already defeated and cannot attack.");
    }//if
    int attackPower = getAttackPower();
    target.takeDamage(attackPower * factor);
    System.out.println(name + " attacks " + target.name + " for " + (factor!=1? factor + "x " + attackPower + " = " + attackPower*factor : attackPower) + " damage.");
  }//attack()
  public String getStatus() {
    return name + " - HP: " + health;
  }//getStatus()
  public void heal(int factor) {
    if (!this.isAlive()) {
      throw new IllegalStateException(name + " is already defeated and cannot attack.");
    }//if
    int healPower = getHealPower() ;
    health += healPower * factor;
    System.out.println(name + " heals by " + (factor!=1? factor + "x " + healPower + " = " + healPower*factor : healPower) + " points. Current health is: " + health + ".");
  }//heal()
  public boolean isAlive() {
    return health > 0;
  }//isAlive()
  public void reduceTurnsTillSpecial() {
    if (turnsTillSpecial > 0) {
      turnsTillSpecial--;
    }
  }//reduceTurnsTillSpecial()
  public void resetTurnsTillSpecial() {
      turnsTillSpecial = 3;
  }//resetTurnsTillSpecial()
  public void takeDamage(int damage) {
    health -= damage;
    if (health < 0) health = 0;
  }//takeDamage()
}//Character
