package com.aojgame.screen;

import java.util.ArrayList;

import javax.swing.text.html.MinimalHTMLWriter;

import com.aojgame.actors.Bonus;
import com.aojgame.actors.Enemy;
import com.aojgame.actors.Player;
import com.aojgame.myplane.Art;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 * ��Ϸ���棬����һ��stage�����actor
 * �����϶���˫����input�¼�
 * @author aojgame.com
 *
 */
public class GameScreen implements Screen, InputProcessor {

	private int width	= Gdx.graphics.getWidth();
	private int height	= Gdx.graphics.getHeight();
	//�����ƶ��ٶ�
	private static final int 	BACKGROUND_MOVE_SPEED 	= 2;
	//���л�����
	private static final int 	MAX_ENEMY		= 20;
	//�л�������׼ʱ��
	private static final float 	GEN_ENEMY_TIME	= 1f;
	//�������ֻ�׼ʱ��
	private static final float	GEN_BONUS_TIME	= 10f;
	
	private Stage				stage;
	private Player 				player;
	private Bonus				bonus;
	private Image				background;
	//��ͣ��ť
	private ImageButton			btn_pause;
	private ArrayList<Enemy> 	enemies;
	
	private boolean	paused = false;
	//��ǰ�ȼ����ȼ�Խ�߳��ִ�ɻ�����Խ���ٶ�Խ��
	private	float		level;
	//�����ƶ�����ǰY����
	private int 		background_Y;
	//��һ��input��XY����
	private int		preX ;
	private int		preY ;
	//�з��ɻ����ɼ�ʱ��
	private float		countTime = 0;
	//�������ּ�ʱ��
	private float		bonusTime = 0;
	//˫����⣬�ϴε����¼��͵�ǰ�����¼�
	private float		lastInputTime	= 0;
	private	float		nowInputTime	= 0;
	
	@Override
	public void render(float delta) {

	    //Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    //��ͣ��ʱ��inputtime��Ҫ���ӣ����ж��Ƿ�˫��
	    nowInputTime += delta;
		
	    if (player.isOver())
	    	show();
	    
	    if (paused){
	    	stage.draw();
	    	return;
	    }
	    
		bonusTime += delta;
		countTime += delta;
		
		background_Y -= BACKGROUND_MOVE_SPEED;
		if (background_Y <= -Art.backgroud.getRegionHeight())
		    background_Y = 0;
		
		while (countTime  * level * MathUtils.random(0.8f, 1.2f)
				> GEN_ENEMY_TIME){
			genEnemy();
			countTime -= GEN_ENEMY_TIME;
		}
		if (bonusTime * level * MathUtils.random(0.8f, 1.2f)
				> GEN_BONUS_TIME){
			bonus.reSet();
			bonusTime -= GEN_BONUS_TIME;
		}
		//level��׼Ϊ1�����ݵ÷��ϸ�
		level = 1f + 0.001f * player.getScore();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		if (delta > 0.02f)
		System.out.println("delta " + delta );
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		level		= 1f;
		
		btn_pause	= new ImageButton(new TextureRegionDrawable(Art.gamePause), 
										new TextureRegionDrawable(Art.gamePausePressed));
		btn_pause.setSize(50, 50);
		btn_pause.setPosition(10,height - 50);
		btn_pause.addListener(new InputListener(){
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				paused = !paused;
				super.touchUp(event, x, y, pointer, button);
			}
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		enemies		= new ArrayList<Enemy>();
		
		//���ű���ͼ����һ�������ʵ�ֱ�������
		background = new Image(Art.backgroud){
			public void draw (SpriteBatch batch, float parentAlpha) {
				setPosition(0, background_Y);
				super.draw(batch, parentAlpha);
				setPosition(0, background_Y + getHeight());
				super.draw(batch, parentAlpha);
			}
		};
		
		bonus 	= new Bonus();
		bonus.setVisible(false);
		
		stage 	= new Stage();
		player 	= new Player();
		
		
		stage.addActor(background);
		stage.addActor(player);
		stage.addActor(bonus);
		stage.addActor(btn_pause);
		stage.setCamera(new OrthographicCamera(1f, 1f * height / width));
		stage.setViewport(width , height , false);
		
		Gdx.input.setInputProcessor(this);
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	/**
	 * ����һ���µĵл��������ӻ��յ���Դ������
	 * ������new����
	 */
	public void genEnemy() {
		int len = enemies.size();
		for (int i = 0; i < len ; i++){
			Enemy enemy = enemies.get(i);
			if (!enemy.isVisible()){
				enemy.reSet(level);
				return;
			}
		}
		if (len >= MAX_ENEMY)
			return;
		
		Enemy enemy = new Enemy(level);
		enemies.add(enemy);
		stage.addActor(enemy);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//�Ȱ��¼����ݸ���ͣ��ť
		stage.touchDown(screenX, screenY, pointer, button);
		
		preX = screenX;
		preY = screenY;
		//˫���ж�
		if(nowInputTime - lastInputTime < 0.5f){
			player.useBomb();
			nowInputTime = 0;
		}
		lastInputTime = nowInputTime;
		//System.out.println("touch " + (preX) + "   "+ (preY) );
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		stage.touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		//��Ļ�϶����ƣ���ҷɻ������϶�·���ƶ�
		player.setX(player.getX() + screenX - preX);
		player.setY(player.getY() -( screenY - preY ));
		
		//���ó�����Ļ�߽�
		if (player.getX() < -player.getWidth() / 2)
			player.setX(-player.getWidth() / 2);
		if (player.getX() > width - player.getWidth() / 2)
			player.setX(width - player.getWidth() / 2);
		if (player.getY() < 0)
			player.setY(0);
		if (player.getY() > height - player.getHeight())
			player.setY(height - player.getHeight());
		
		preX = screenX;
		preY = screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
