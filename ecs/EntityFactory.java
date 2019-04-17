package ecsTest.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import ecsTest.ecs.components.CameraComponent;
import ecsTest.ecs.components.InputComponent;
import ecsTest.ecs.components.ObliqueGridComponent;
import ecsTest.ecs.components.PositionComponent;
import ecsTest.ecs.components.RenderableComponent;
import ecsTest.ecs.components.WorldComponent;

public class EntityFactory {
    
    private Engine engine;

    public EntityFactory(Engine engine){
        this.engine = engine;
    }
    
    public void createEntities(){
        addWorldEntity();
        addInitialTileEntities();
        addCameraEntity();
    }

    private void addWorldEntity() {
        engine.addEntity( new Entity().add( new WorldComponent() ));
    }

    private void addInitialTileEntities() {
        //create initial tile entities
        for(int a = 0; a<25; ++a) {
            engine.addEntity( new Entity()
                                        .add( new RenderableComponent() )
                                        .add( new PositionComponent() )
                                        .add( new IsometricGridComponent() )
            );
        }
    }

    private void addCameraEntity() {
        engine.addEntity( new Entity().add( new InputComponent() ).add( new CameraComponent() ) );
    }

}
