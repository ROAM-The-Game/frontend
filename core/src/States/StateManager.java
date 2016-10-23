package States;

public class StateManager {

	static State curr;
	static MapExplore mapExplore;
	static Battle battle;
	static MainMenu mainmenu;
	
	public enum Screen {
		MAINMENU,
		MAPEXPLORE,
		BATTLE
	}
	
	public StateManager() {
		mapExplore = new MapExplore();
		battle = new Battle();
		mainmenu = new MainMenu();
		curr = mainmenu;
		curr.show();
	}
	
	public static void change(Screen scrn) {
		curr.hide();
		switch(scrn) {
			case MAPEXPLORE: curr = mapExplore; break;
			case BATTLE: curr = battle; break;
			case MAINMENU: curr = mainmenu; break;
		}
		curr.show();
	}
	
	public void render(float delta) {
		curr.render(delta);
	}
}
