package com.aojgame.actors;

import com.aojgame.myplane.Art;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
/**
 * ��ҷɻ�������{@link Bullet}�ӵ���
 * ����������ը����
 * @author aojgame.com
 *
 */
public class Player extends Actor{
	
	private static final float PLAYER_WIDTH 	= 100;
	private static final float PLAYER_HEIGHT 	= 120;
	//��ײ����ֵ�������������������ײ
	private static final float CRASH_DELTA	= 30;
	private Animation 	animation;
	private Bullet		bullet;
	//��ײ��������
	private boolean isDead = false;
	//��ը������Ͼ�gameover
	private boolean gameOver = false;
	private float	stateTime;
	private int 	bombs;
	private int 	score;
	
	public Player(){
		animation 	= Art.animation_player;
		bombs		= 1;
		stateTime 	= 0;
		score		= 0;
		
		setName("Player");
		setHeight(PLAYER_HEIGHT);
		setWidth(PLAYER_WIDTH);
		setPosition((Gdx.graphics.getWidth()-getWidth()) / 2, 0);
		
		bullet		= new Bullet(this);
	}
	
	public void act (float delta) {
		
		stateTime += delta;
		//�����󲥷ű�ը����
		if ( isDead && animation.isAnimationFinished(stateTime)){
			setVisible(false);
			gameOver = true;
		}
		if (isDead )return;
		
		bullet.act(delta);
		
		checkCrash();
		if (isDead){
			animation = Art.animation_player_down;
			stateTime = 0;
		}
	}

	public void draw (SpriteBatch batch, float parentAlpha) {
		//��ը������ѭ��
		if (isDead)
			batch.draw(animation.getKeyFrame(stateTime, false), getX(), getY() );
		else batch.draw(animation.getKeyFrame(stateTime, true), getX(), getY() );
		
		//�����󲻷����ӵ�
		if (!isDead)bullet.draw(batch, parentAlpha);
		
		if ( bombs >= 0 ){
			batch.draw(Art.bomb, 0,0 );
			Art.font.draw(batch, "X" + bombs, 70, 40);
		}
		//FPS
		//Art.font.draw(batch, Gdx.graphics.getFramesPerSecond()+"", 100, 300);
		Art.font.draw(batch, score>0?score+"000":"0", Art.gamePause.getRegionWidth() +10, Gdx.graphics.getHeight() -10 );
	}
	
	public int getScore() {
		return this.score;
	}
	/**
	 * ʹ��ը��
	 */
	public void useBomb(){
		if (bombs <= 0)return;
		
		bombs --;
		Array<Actor> actors = getStage().getActors();
		for (Actor actor : actors) {
			if ( actor.getName() != null && actor.getName().equals("Enemy")
					&& actor.isVisible()){
				Enemy enemy = (Enemy)actor;
				enemy.beShooted(9999);
			}
		}
	}
	public boolean isOver(){
		return gameOver;
	}
	/**
	 * ��ײ��⣬�жϻ���ߴ磬������width,height
	 * @param actor
	 * @return
	 */
	private boolean crash (Actor actor) {
		return !(getX() + CRASH_DELTA > actor.getX() + actor.getWidth() || getX() + CRASH_DELTA + getWidth() - 2 * CRASH_DELTA < actor.getX() || 
				getY() > actor.getY() + actor.getHeight() || getY() + getHeight() < actor.getY());
	}
	private void checkCrash(){
		Array<Actor> actors = getStage().getActors();
		for (Actor actor : actors) {
			if (actor.getName() == null || !actor.isVisible())
				continue;
			if ( actor.getName().equals("Enemy")){
				Enemy enemy = (Enemy)actor;
				if (crash(enemy)){
					isDead = true;
					break;
				}
				//���ел����л���Ѫ����ըʱ�ӷ�
				if ( enemy.getHP() > 0 && bullet.Hit(enemy) && enemy.getHP() <= 0){
					if (enemy.getType() == Enemy.TYPE_SMALL )score += 1;
					else if (enemy.getType() == Enemy.TYPE_MIDDLE)score += 6;
					else score += 30;
				}
			}
			//���ը��
			else if (actor.getName().equals("Bomb") && crash(actor)){
				bombs++;
				if (bombs > 3)bombs = 3;
				actor.setVisible(false);
			}
			//���˫���ӵ�
			else if (actor.getName().equals("Bullet") && crash(actor)){
				bullet.upgrade();
				actor.setVisible(false);
			}
		}
	}
	
}
