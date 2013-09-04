package com.aojgame.myplane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	
	public static TextureAtlas 	textureAtlas_background;
	public static TextureAtlas 	textureAtlas_planes;
	
	//����
	public static TextureRegion 	backgroud1;
	public static TextureRegion 	backgroud2;
	public static TextureRegion 	copyright;
	//���ذ�ť
	public static TextureRegion 	btn_goback;
	//Loading����
	public static TextureRegion[]	gameLoading;
	public static Animation 		animation_gameLoading;
	//����
	public static BitmapFont		font;
	
	private static AssetManager	assetManager;
	
	public static boolean			isLoaded;
	//�ⲿ����Դ��Ҫ�������룬������Դ�����첽����
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
	
	//�첽������Դ
	public static void load(){
		
		assetManager.load("data/shoot.pack", TextureAtlas.class);
		assetManager.load("data/sound/game_music.mp3",Music.class);
		assetManager.load("data/ui/font.fnt", BitmapFont.class);

	}
	
	public static void update(){
		
		if (assetManager.update()){
			
			textureAtlas_planes  = assetManager.get("data/shoot.pack", TextureAtlas.class);
			
			//����������������ʾ
			backgroud1		= textureAtlas_background.createSprite("background");
			backgroud2		= textureAtlas_background.createSprite("background");
			//��Ȩ����
			copyright		= textureAtlas_background.createSprite("shoot_copyright");
			//����
			font 			= assetManager.get("data/ui/font.fnt", BitmapFont.class);
			//���ذ�ť
			btn_goback		= new TextureRegion(textureAtlas_background.createSprite("btn_finish"));
			
			isLoaded = true;
		}
	}
}
