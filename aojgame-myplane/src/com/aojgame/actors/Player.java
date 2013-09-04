package com.aojgame.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;

public class Player extends Actor{
	
	private static final float fireX = 5;
	
	private Animation 	animation;
	private Bullet		bullet;
	
	private boolean isDead;
	private float	stateTime = 0;
	private int 	bombs;
	
	public Player(){
		animation 	= null;
		bombs		= 0;
		
		setName("Player");
		setHeight(0);
		setWidth(0);
		setPosition((Gdx.graphics.getWidth()-getWidth()) / 2, 0);
		
		addListener(new InputListener(){
			public void touchDragged (InputEvent event, float x, float y, int pointer) {
				setPosition(x, y);
			}
		});
	}
	public void draw (SpriteBatch batch, float parentAlpha) {
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animation.getKeyFrame(stateTime), getX(), getY() );
		
		if ( bombs >= 0 ){
			//draw bombs
		}
	}
	
	public void act (float delta) {
		super.act(delta);
		checkCrash();
		if (isDead){
			animation = null;
		}
	}
	
	private void checkCrash(){
		Array<Actor> actors = getStage().getActors();
		for (Actor actor : actors) {
			if (actor.getName().equals("Armay")){
				Armay armay = (Armay)actor;
				if (armay.crash(getX(), getY(), getWidth(), getHeight())){
					isDead = true;
					break;
				}
				if (armay.crash(x, y, width, height)){
					armay.beShooted();
				}
			}
			else if (actor.getName().equals("Bombs")){
				
			}
			else if (actor.getName().equals("Bullet")){
				bullet.upgrade();
			}
		}
	}
}
