package com.aojgame.myplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * ��Դ�࣬�첽���أ�������ɺ�ֱ�ӵ���
 * @author aojgame
 *
 */
public class Art {
	
	public static TextureAtlas 	textureAtlas_background;
	public static TextureAtlas 	textureAtlas_planes;
	
	//����
	public static TextureRegion 	backgroud;
	public static TextureRegion 	copyright;
	//�ɻ�
	public static TextureRegion[]	player;
	public static Animation		animation_player;
	
	//���ذ�ť
	public static TextureRegion 	btn_goback;
	//Loading����
	public static TextureRegion[]	gameLoading;
	public static Animation 		animation_gameLoading;
	//����
	public static BitmapFont		font;
	//��Դ������
	private static AssetManager	assetManager;
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
		assetManager.load("data/sound/game_music.mp3",Music.class);
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
			//���ذ�ť
			btn_goback		= new TextureRegion(textureAtlas_background.createSprite("btn_finish"));
			
			//��ҷɻ�
			player			= new TextureRegion[2];
			player[0]		= textureAtlas_planes.createSprite("hero1");
			player[1]		= textureAtlas_planes.createSprite("hero2");
			animation_player= new Animation(0.3f, player);
			
			//�з��ɻ�
			
			isLoaded = true;
		}
	}
}
