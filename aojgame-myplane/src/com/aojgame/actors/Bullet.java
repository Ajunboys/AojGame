package com.aojgame.actors;

import com.aojgame.myplane.Art;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * �ӵ��࣬������{@link Player}�У�������stage
 * @author aojgame.com
 *
 */
public class Bullet extends Actor{

	//˫���ӵ���ʹ��ʱ��
	private static final float	BLUE_TIME		= 15;
	private static final float	BULLET_WIDTH 	= 10;
	private static final float 	BULLET_HEIGHT 	= 10;
	private static final float	BULLET_SPEED	= 1500;
	//˫���ӵ�ʱ���ӵ���X����룬��ײ�ж�ʱ�õ�
	private static final float	DELTA_X			= 30;
	
	private boolean isDouble;
	private float	blueTime;
	private Player			player;
	
	public Bullet(Player player) {
		isDouble 	= false;
		this.player = player;
		
		setX(player.getX() + player.getWidth() / 2);
		setY(player.getY() + player.getHeight()) ;
		
		setWidth(BULLET_WIDTH);
		setHeight(BULLET_HEIGHT);

	}
	public void act (float delta) {
		setY(getY() + BULLET_SPEED * delta );
		
		//�ӵ�����Ŀ��ʱ�����·���
		if (isDouble){
			blueTime += Gdx.graphics.getDeltaTime();
			if (blueTime > BLUE_TIME){
				isDouble = false;
				reShoot();
			}
		}
		//�ӵ�������Ļʱ�����·���
		if (getY() >= Gdx.graphics.getHeight())
			reShoot();
	}
	public void draw (SpriteBatch batch, float parentAlpha) {
		if (isDouble){
			batch.draw(Art.bullet_bule, getX(), getY());
			batch.draw(Art.bullet_bule, getX() + 2*DELTA_X, getY());
		}
		else{
			batch.draw(Art.bullet_red, getX() - getWidth() / 2, getY());
		}
	}
	/**
	 * ���ел��жϣ�˫���ӵ�ʱ��Ҫ�ж������ӵ�
	 * @param enemy
	 * @return
	 */
	public boolean Hit(Enemy enemy){
		if (isDouble && 
				(crash(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())
					|| crash(enemy.getX() - 2*DELTA_X,enemy.getY(), enemy.getWidth(), enemy.getHeight()))){
				reShoot();
				enemy.beShooted(1);
				return true;
		}
		if ( !isDouble && crash(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight())){
			reShoot();
			enemy.beShooted(1);
			return true;
		}
		return false;
	}
	/**
	 * ����Ϊ˫���ӵ�
	 */
	public void upgrade(){
		blueTime = 0;
		isDouble = true;
		reShoot();
	}
	/**
	 * ���·���
	 */
	public void reShoot(){
		if (isDouble){
			setX(player.getX() + player.getWidth() / 2 - DELTA_X);
			setY(player.getY() + player.getHeight() ) ;
		}
		else {
			setX(player.getX() + player.getWidth() / 2 );
			setY(player.getY() + player.getHeight() ) ;
		}
	}
	private boolean crash (float x, float y, float width, float height) {
		return !(getX() > x + width || getX() + getWidth() < x || 
				getY() > y + height || getY() + getHeight() < y);
	}
}
