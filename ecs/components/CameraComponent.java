package ecsTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraComponent implements Component {
    private OrthographicCamera cam;

    public CameraComponent(){
        cam = new OrthographicCamera();
    }

    public CameraComponent( OrthographicCamera camera ){
        cam = camera;
    }

    public OrthographicCamera getCam() {
        return cam;
    }
}
