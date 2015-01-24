package gui;

public class SidebarItem {
    
    private String name;
    private StrandedApplet applet;
    
    public SidebarItem(String name, StrandedApplet applet) {
        this.name = name;
        this.applet = applet;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Draws the sidebar in the sidebar pane
     * @precondition The applet has translated to the top-left corner of the sidebar
     */
    public void draw() {
        
    }
}
