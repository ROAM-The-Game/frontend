package States;

public class StateManager {

	State curr;
	MapExplore mapExplore;
	
	public StateManager() {
		mapExplore = new MapExplore();
		curr = mapExplore;
		curr.show();
	}
	
	public void render(float delta) {
		curr.render(delta);
	}
}
