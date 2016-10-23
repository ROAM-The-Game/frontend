package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by kerlinmichel on 10/23/16.
 */
public class Death extends State {

    Image bg;
    Image gravestone;

    public Death() {
        stage = new Stage();
        bg = new Image(new Texture("gui/loginbg.png"));
        gravestone = new Image(new Texture("gui/gravestone.png"));
        bg.setWidth(Gdx.graphics.getWidth());
        bg.setHeight(Gdx.graphics.getHeight());
        gravestone.setWidth(Gdx.graphics.getWidth());
        gravestone.setHeight(Gdx.graphics.getHeight());
    }

    public void show() {
        stage.addActor(bg);
        stage.addActor(gravestone);
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }
}
