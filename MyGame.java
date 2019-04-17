package ecsTest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ecsTest.ecs.EntityFactory;
import ecsTest.ecs.components.CameraComponent;
import ecsTest.ecs.systems.InputSystem;
import ecsTest.ecs.systems.RenderingSystem;
import ecsTest.ecs.systems.TerritorySetupSystem;
import ecsTest.CamController;
import ecsTest.GameGestureListener;


public class MyGame extends ApplicationAdapter {

	Viewport viewport;
  final float screenAspectRatio = (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
	OrthographicCamera cameraGame;
	CamController gameCamController;

	InputMultiplexer inputMultiplexer;

	SpriteBatch batch;
  Stage stageHUD;

	//ECS
	Engine engine;
	EntityFactory entityFactory;

	RenderingSystem renderSystem;
	InputSystem inputSystem;
	TileSetupSystem tileSetupSystem;

	public MyGame(){
		batch = new SpriteBatch();
    stageHUD = new Stage();

		//ECS Engine
		engine = new Engine();

		//ECS Entities
		entityFactory = new EntityFactory(engine);
		entityFactory.createEntities();

		//ECS Systems
		inputSystem = new InputSystem();
		tileSetupSystem = new TileSetupSystem();
		renderSystem = new RenderingSystem( batch );

		engine.addSystem( tileSetupSystem );
		engine.addSystem( inputSystem );
		engine.addSystem( renderSystem );


		cameraGame = engine.getEntitiesFor( Family.one(CameraComponent.class).get() ).first().getComponent(CameraComponent.class).getCam();
		float worldWidht = 1800f;
    float worldHeight = 600f;
    viewport = new FitViewport( worldHeight*screenAspectRatio, worldHeight, cameraGame );
		viewport.update( Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true );

		gameCamController = inputSystem.getCamController();

		inputMultiplexer = new InputMultiplexer();
		if( Gdx.app.getType() == Application.ApplicationType.Desktop ){
			inputMultiplexer.addProcessor( gameCamController );
		}
		if( Gdx.app.getType() == Application.ApplicationType.Android ){
			inputMultiplexer.addProcessor( new GestureDetector(new GameGestureListener(cameraGame) ));
		}
    
    //add the UI/HUD
		inputMultiplexer.addProcessor( stageHUD );
    
		Gdx.input.setInputProcessor( inputMultiplexer );

	}
	
	@Override
	public void show() {
		cameraGame.update();
	}

	public void gameloop(){
		int tickRate = 20;
		while( true ){
			System.out.println();
		}
	}

	public void render(float delta) {
		Gdx.gl.glClearColor( 0.154f, 0.200f, 0.184f, 1f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		engine.update(delta);

		stageHUD.act(delta);
		stageHUD.draw();
		
		cameraGame.update();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update( width, height, true );
		stageHUD.getViewport().update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		batch.dispose();
    stage.dispose();
	}
}
