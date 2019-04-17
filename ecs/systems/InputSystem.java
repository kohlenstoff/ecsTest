package ecsTest.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ecsTest.ecs.components.CameraComponent;
import ecsTest.CamController;

public class InputSystem extends EntitySystem {

    private OrthographicCamera cam;
    private CamController camController; //needs to be a component because SYSTEMS -> ONLY BEHAVIOR

    public InputSystem(){

    }

    @Override
    public void addedToEngine(Engine engine) {
        cam = engine.getEntitiesFor( Family.one(CameraComponent.class).get() ).first().getComponent(CameraComponent.class).getCam();
        camController = new CamController( cam );
    }

    public CamController getCamController(){
        return camController;
    }

    @Override
    public void update(float deltaTime) {
        
        cam.update();
    }
}
