package gui;

public class CharacterDetail extends SidebarItem {
    
    //The character that this view is showing
    private Character character;
    
    public CharacterDetail(StrandedApplet applet, Character character) {
        super("Character Detail", applet);
        this.character = character;
    }
}
