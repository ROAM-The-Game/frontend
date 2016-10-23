package States;

public class StateManager {

	static State curr;
	static MapExplore mapExplore;
	static Battle battle;
	
	public enum Screen {
		MAPEXPLORE,
		BATTLE
	}
	
	public StateManager() {
		mapExplore = new MapExplore();
		battle = new Battle();
		curr = mapExplore;
		curr.show();
	}
	
	public static void change(Screen scrn) {
		curr.hide();
		switch(scrn) {
			case MAPEXPLORE: curr = mapExplore; break;
			case BATTLE: curr = battle; break;
		}
		curr.show();
	}
	
	public void render(float delta) {
		curr.render(delta);
	}
}
