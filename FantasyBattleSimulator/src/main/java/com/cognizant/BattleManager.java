package com.cognizant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleManager {
  protected final List<Character> characters = new ArrayList<>();
  protected final List<Character> charactersInPlay = new ArrayList<>();
  
  public void addCharacter(Character character) {
    characters.add(character);
    charactersInPlay.add(character);
  }
  public Character getCharacter(String name) throws CharacterNotFoundException {
    return characters.stream()
            .filter(c -> c.name.equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new CharacterNotFoundException("Character " + name + " not found."));
  }
  
  public void performAction(String actorName, String targetName, String action)
          throws InvalidActionException, CharacterNotFoundException {
    Character target = null;
    if (targetName!=null) {
      target = getCharacter(targetName);
    }//if
    Character actor = getCharacter(actorName);
    
    if (!actor.isAlive()) throw new InvalidActionException(actorName + " is defeated.");
    if (targetName!=null && !target.isAlive() && !action.equals("special")) throw new InvalidActionException(targetName + " is already defeated.");
    
    switch (action.toLowerCase()) {
      case "attack":
        actor.attack(target, 1);
        if (!target.isAlive()) charactersInPlay.remove(target);
        actor.reduceTurnsTillSpecial();
        break;
      case "special":
        if (actor.turnsTillSpecial != 0) throw new InvalidActionException("Please wait " + actor.turnsTillSpecial + " more turns before using a special action");
        actor.specialAbility(target);
        actor.resetTurnsTillSpecial();
        break;
      case "heal":
        actor.heal(1);
        break;
      default:
        throw new InvalidActionException("Invalid action: " + action);
    }//switch
    System.out.println(actor.getStatus());
    if (target!=null) System.out.println(target.getStatus());
  }//performAction()
}//BattleManager
