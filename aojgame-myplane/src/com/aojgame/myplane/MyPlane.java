package com.aojgame.myplane;

import com.aojgame.screen.LoadingScreen;
import com.badlogic.gdx.Game;
/**
 * Game�ֻ࣬������Loading�������Ϸ����
 * @author aojgame.com
 *
 */
public class MyPlane extends Game {

	public void create() {
		this.setScreen(new LoadingScreen(this));
	}

}
