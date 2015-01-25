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
        int x = 20;
        int y = 10;
        
        // Loop through idle characters
        for (Character character : game.characters) {
            String text = character.getName();
            
            y += 40;

            //draw rectangle (button) under the text
            applet.fill(128);
            applet.rect(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            applet.fill(0, 255, 0);
            applet.text(text, x, y);
        }
        
        // Loop through active characters
        for (Task task : game.taskRunner.pendingTasks) {
            Character character = task.getCharacter();

            String text = character.getName();
            
            y += 40;

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
            int x = 20;
            int y = 40 * i + 40;
            
            Rectangle button = new Rectangle(x - 4, y + 2, text.length() * 11 + 4, 23);
            
            if (button.contains(mousex, mousey)) {
                applet.currentSidebar = new CharacterDetail(applet, game, game.characters.get(i));
            }
        }
    }
}
