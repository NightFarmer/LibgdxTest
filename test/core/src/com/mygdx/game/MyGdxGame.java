package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private TextureRegion textureRegion;
	private Sprite sprite;
	private FreeTypeFontGenerator freeTypeFontGenerator;
	private BitmapFont chinaFont;
	private BitmapFont cyrillicFont;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private Animation walkAnimation;
	private float stateTime;
	private Stage stage;
	private Mario mario;
	
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 5;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		textureRegion = new TextureRegion(img);
		sprite = new Sprite(img, 128, 0, 128, 128);
		sprite.setSize(120, 120);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setRotation(50);
		sprite.setPosition(350, 310);
		sprite.setColor(1, 0, 1, 1);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fnt/hksn.ttf"));

		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 18;
		parameter.color = new Color(0.5f, 0.5f, 0.5f, 1);
		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS+"长的帅人告白才叫，丑男那性骚扰。";
		chinaFont = generator.generateFont(parameter);
		generator.dispose();

		parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("fnt/hksn.ttf"));
		cyrillicFont = generator.generateFont(parameter);
		generator.dispose();
		
		
		walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);

		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames);

//		walkAnimation.setPlayMode(PlayMode.LOOP);

		batch = new SpriteBatch();

		stateTime = 0f;
		
		stage = new Stage();
	       mario = new Mario();
	       Gdx.input.setInputProcessor(stage);
	       stage.addActor(mario);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		img.dispose();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime();

		TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

		batch.begin();
		sprite.draw(batch);
		batch.draw(img, 0, 0);
		chinaFont.draw(batch, "hello Fighting Potato: \n \n长的帅的人告白才叫告白，\n长的丑的         男人告白那叫性骚扰。", 150, 150);
		batch.draw(currentFrame, Gdx.graphics.getWidth() / 2,
	               Gdx.graphics.getHeight() / 2);
		batch.end();
		stage.act();
	       stage.draw();
	}
}
