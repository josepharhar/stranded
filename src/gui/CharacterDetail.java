package gui;

import java.util.Map;

import main.Game;
import main.Resource;
import characters.Character;
import characters.Skill;

public class CharacterDetail extends SidebarItem {
    
    //The character that this view is showing
    private Character character;
    
    public CharacterDetail(StrandedApplet applet, Game game, Character character) {
        super("Character Detail", applet, game);
        this.character = character;
    }
    
    public void draw() {
        super.draw();
        
        int x = 20;
        int y = 40;

        //Name
        applet.fill(0, 128, 0);
        applet.text("Name: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + character.getName(), x, y);
        y += 30;

        //Health
        applet.fill(0, 128, 0);
        applet.text("Health: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + character.getHealth(), x, y);
        y += 30;
        
        //Occupation
        applet.fill(0, 128, 0);
        applet.text("Occupation: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + character.getOccupation(), x, y);
        y += 30;

        //Skills
        applet.fill(0, 128, 0);
        applet.text("Skills: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        for (Map.Entry<Skill, Double> skill : character.getSkills().entrySet()) {
            applet.text(" " + skill.getKey() + ": " + skill.getValue(), x, y);
            y += 30;
        }
        
        //Luck
        applet.fill(0, 128, 0);
        applet.text("Luck: ", x, y);
        y += 30;
        applet.fill(0, 255, 0);
        applet.text(" " + character.getLuck(), x, y);
        y += 30;
    }
}
