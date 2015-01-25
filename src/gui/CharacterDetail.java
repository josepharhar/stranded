package gui;

import main.Game;

public class CharacterDetail extends SidebarItem {
    
    //The character that this view is showing
    private Character character;
    
    public CharacterDetail(StrandedApplet applet, Game game, Character character) {
        super("Character Detail", applet, game);
        this.character = character;
    }
}
