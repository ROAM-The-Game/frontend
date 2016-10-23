package States;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.heavyhanded.roam.Game;

import Entities.Player;

public class Battle extends State {
	
	Player player;
	
	public Battle() {
		Stage stage = new Stage();
		player = Game.player;
	}
	
	@Override
	public void show() {
		stage.addActor(player);
		player.setX(0);
		player.setY(100);
	}
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

}
