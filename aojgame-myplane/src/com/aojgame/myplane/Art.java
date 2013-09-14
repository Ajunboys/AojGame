package com.aojgame.myplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * ��Դ�࣬�첽���أ�������ɺ�ֱ�ӵ���
 * �زĲ���apk������ȡ������pack�ļ���png�ļ���ԭװ���޸�
 * @author aojgame.com
 *
 */
public class Art {
	
	public static TextureAtlas 	textureAtlas_background;
	public static TextureAtlas 	textureAtlas_planes;
	
	//����
	public static TextureRegion 	backgroud;
	public static TextureRegion 	copyright;
	//��ҷɻ�
	public static TextureRegion[]	player;
	public static Animation		animation_player;
	//��ҷɻ���ը
	public static TextureRegion[]	player_down;
	public static Animation		animation_player_down;
	//�з��ɻ�
	public static TextureRegion[] enemy1;
	public static TextureRegion[] enemy2;
	public static TextureRegion[] enemy3;
	public static Animation[]		animation_enemy;
	//���ж���
	public static TextureRegion 	enemy2_hit;
	public static TextureRegion	enemy3_hit;
	public static Animation[]		animation_enemy_hit;
	//��ը����
	public static TextureRegion[] enemy1_down;
	public static TextureRegion[] enemy2_down;
	public static TextureRegion[] enemy3_down;
	public static Animation[]		animation_enemy_down;

	//UFO
	public static TextureRegion		UFO_BULLET;
	public static TextureRegion		UFO_BOMB;
	//�ӵ�
	public static TextureRegion		bullet_red;
	public static TextureRegion		bullet_bule;
	//ը��
	public static TextureRegion		bomb;
	//��ť
	public static TextureRegion 		btn_goback;
	public static TextureRegion		gamePause;
	public static TextureRegion		gamePausePressed;
	//Loading����
	public static TextureRegion[]		gameLoading;
	public static Animation 			animation_gameLoading;
	//����
	public static BitmapFont			font;
	//��Դ������
	private static AssetManager		assetManager;
	//�Ƿ�������
	public static boolean			isLoaded;
	
	/**
	 * �ⲿ����Դ��Ҫ�������룬������Դ�����첽����
	 */
	public static void init(){
		
		isLoaded 	= false;
		
		assetManager = new AssetManager();
		
		//��ȡ��Ϸ��ͼ�ز�
		textureAtlas_background = new TextureAtlas(Gdx.files.internal("data/shoot_background.pack"));
		
		//Loading����
		gameLoading		= new TextureRegion[4];
		gameLoading[0]	= new TextureRegion(textureAtlas_background.createSprite("game_loading1"));
		gameLoading[1]	= new TextureRegion(textureAtlas_background.createSprite("game_loading2"));
		gameLoading[2]	= new TextureRegion(textureAtlas_background.createSprite("game_loading3"));
		gameLoading[3]	= new TextureRegion(textureAtlas_background.createSprite("game_loading4"));
		
		animation_gameLoading	= new Animation(0.5f, gameLoading);
	
	}
	
	/**
	 * ������Դ
	 */
	public static void load(){
		
		assetManager.load("data/shoot.pack", TextureAtlas.class);
		//�����ļ���data�����У���ûд������ȥ������������
//		assetManager.load("data/sound/game_music.mp3",Music.class);
		assetManager.load("data/ui/font.fnt", BitmapFont.class);

	}
	
	/**
	 * �첽������Դ
	 */
	public static void update(){
		
		if (assetManager.update()){
			
			textureAtlas_planes  = assetManager.get("data/shoot.pack", TextureAtlas.class);
			
			//����������������ʾ
			backgroud		= textureAtlas_background.createSprite("background");
			//��Ȩ����
			copyright		= textureAtlas_background.createSprite("shoot_copyright");
			//����
			font 			= assetManager.get("data/ui/font.fnt", BitmapFont.class);
			font.setColor(Color.BLACK);
			
			//���ذ�ť
			btn_goback		= new TextureRegion(textureAtlas_background.createSprite("btn_finish"));
			
			//��ҷɻ�
			player			= new TextureRegion[2];
			player[0]		= textureAtlas_planes.createSprite("hero1");
			player[1]		= textureAtlas_planes.createSprite("hero2");
			animation_player= new Animation(0.3f, player);
			player_down		= new TextureRegion[4];
			player_down[0]	= textureAtlas_planes.createSprite("hero_blowup_n1");
			player_down[1]	= textureAtlas_planes.createSprite("hero_blowup_n2");
			player_down[2]	= textureAtlas_planes.createSprite("hero_blowup_n3");
			player_down[3]	= textureAtlas_planes.createSprite("hero_blowup_n4");
			animation_player_down = new Animation(0.3f, player_down);
			
			//�з��ɻ�
			enemy1			= new TextureRegion[1];
			enemy2			= new TextureRegion[1];
			enemy3			= new TextureRegion[2];
			enemy1[0]		= textureAtlas_planes.createSprite("enemy1");
			enemy2[0]		= textureAtlas_planes.createSprite("enemy2");
			enemy3[0]		= textureAtlas_planes.createSprite("enemy3_n1");
			enemy3[1]		= textureAtlas_planes.createSprite("enemy3_n2");
			animation_enemy = new Animation[3];
			animation_enemy[0] = new Animation(0.3f, enemy1);
			animation_enemy[1] = new Animation(0.3f, enemy2);
			animation_enemy[2] = new Animation(0.3f, enemy3);
			
			//�з��ɻ������ж���
			enemy2_hit		= textureAtlas_planes.createSprite("enemy2_hit");
			enemy3_hit		= textureAtlas_planes.createSprite("enemy3_hit");
			animation_enemy_hit 	= new Animation[3];
			animation_enemy_hit[0] 	= new Animation(0.3f, enemy1);
			animation_enemy_hit[1] 	= new Animation(0.3f, enemy2[0], enemy2_hit);
			animation_enemy_hit[2] 	= new Animation(0.3f, enemy3[0], enemy3[1], enemy3_hit);
			
			//�з��ɻ���ը����
			enemy1_down		= new TextureRegion[4];
			enemy2_down		= new TextureRegion[4];
			enemy3_down		= new TextureRegion[6];
			enemy1_down[0]	= textureAtlas_planes.createSprite("enemy1_down1");
			enemy1_down[1]	= textureAtlas_planes.createSprite("enemy1_down2");
			enemy1_down[2]	= textureAtlas_planes.createSprite("enemy1_down3");
			enemy1_down[3]	= textureAtlas_planes.createSprite("enemy1_down4");

			enemy2_down[0]	= textureAtlas_planes.createSprite("enemy2_down1");
			enemy2_down[1]	= textureAtlas_planes.createSprite("enemy2_down2");
			enemy2_down[2]	= textureAtlas_planes.createSprite("enemy2_down3");
			enemy2_down[3]	= textureAtlas_planes.createSprite("enemy2_down4");
			
			enemy3_down[0]	= textureAtlas_planes.createSprite("enemy3_down1");
			enemy3_down[1]	= textureAtlas_planes.createSprite("enemy3_down2");
			enemy3_down[2]	= textureAtlas_planes.createSprite("enemy3_down3");
			enemy3_down[3]	= textureAtlas_planes.createSprite("enemy3_down4");
			enemy3_down[4]	= textureAtlas_planes.createSprite("enemy3_down5");
			enemy3_down[5]	= textureAtlas_planes.createSprite("enemy3_down6");
			
			animation_enemy_down = new Animation[3];
			animation_enemy_down[0]	= new Animation(0.1f, enemy1_down);
			animation_enemy_down[1]	= new Animation(0.1f, enemy2_down);
			animation_enemy_down[2]	= new Animation(0.1f, enemy3_down);
			
			//��ͣ��ť
			gamePause		= textureAtlas_planes.createSprite("game_pause_nor");
			gamePausePressed= textureAtlas_planes.createSprite("game_pause_pressed");
			
			//ը��
			bomb			= textureAtlas_planes.createSprite("bomb");
			
			//�ӵ�
			bullet_red		= textureAtlas_planes.createSprite("bullet1");
			bullet_bule		= textureAtlas_planes.createSprite("bullet2");
			
			//UFO
			UFO_BULLET		= textureAtlas_planes.createSprite("ufo1");
			UFO_BOMB		= textureAtlas_planes.createSprite("ufo2");
			
			isLoaded = true;
		}
	}
}
