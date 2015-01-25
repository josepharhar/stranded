package gui;

import java.awt.Rectangle;

import tasks.Task;
import main.Game;
import characters.Character;

public class CharacterList extends SidebarItem {
    public CharacterList(StrandedApplet applet, Game game) {
        super("Character List", applet, game);
    }
    
    public void draw() {
        super.draw();
        
        //location for text to show up
        int x = BUTTON_X_SPACING;
        int y = BUTTON_INIT_SPACING;
        
        // Loop through idle characters
        for (Character character : game.characters) {
            String text = character.getName();
            
            y += BUTTON_SPACING;

            //draw rectangle (button) under the text
            applet.strokeWeight(2);
            applet.stroke(192);
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23, 5, 5, 5, 5);
            
            applet.fill(0, 255, 0);
            applet.text(text, x, y);
        }
        
        // Loop through active characters
        for (Task task : game.taskRunner.pendingTasks) {
            Character character = task.getCharacter();

            String text = character.getName();
            
            y += BUTTON_SPACING;

            //draw rectangle (button) under the text
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            applet.fill(255, 255, 0);
            applet.text(text, x, y);
        }
    }
    
    public void click(float mousex, float mousey) {
        super.click(mousex, mousey);
        for (int i = 0; i < game.characters.size(); i++) {
            String text = game.characters.get(i).getName();
            
            //location for text to show up
            int x = BUTTON_X_SPACING;
            int y = BUTTON_SPACING * i + BUTTON_SPACING + BUTTON_INIT_SPACING;
            
            Rectangle button = new Rectangle(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            if (button.contains(mousex, mousey)) {
                applet.currentSidebar = new CharacterDetail(applet, game, game.characters.get(i));
            }
        }
    }
}
