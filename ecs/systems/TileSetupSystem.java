package ecsTest.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ecsTest.ecs.components.IsometricGridComponent;
import ecsTest.ecs.components.PositionComponent;
import ecsTest.ecs.components.RenderableComponent;

public class TileSetupSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    public TileSetupSystem(){
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor( Family.all( IsometricGridComponent.class, PositionComponent.class ).get() );

        int e = 0;
        //25 iterations from the initial entity setup in EntityFactory.java
        for(int y=0; y<5; y++){
            for(int x=0; x<5; x++){
                float dx = entities.get(e).getComponent(IsometricGridComponent.class).getGrid().getMaxWidth();
                float dy = entities.get(e).getComponent(IsometricGridComponent.class).getGrid().getMaxHeight();
                entities.get(e).getComponent(PositionComponent.class).x = x*dx;
                entities.get(e).getComponent(PositionComponent.class).y = y*dy;

                int spriteWidth = entities.get(e).getComponent(IsometricGridComponent.class).getGrid().getMaxWidth();
                int spriteHeigth = entities.get(e).getComponent(IsometricGridComponent.class).getGrid().getMaxHeight();
                Sprite sprite = new Sprite( new Texture(Gdx.files.internal("tile_grass.png")));
                entities.get(e).getComponent(RenderableComponent.class).setSprite( sprite );
                e++;
            }
        }

        //no need to run constantly, only setup once, therefore
        this.setProcessing(false);
    }



    public void update(float deltaTime){

    }
}
