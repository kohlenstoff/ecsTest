package ecsTest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

 /**
 * Cam controlling for ANDROID
 */
public class GameGestureListener implements GestureDetector.GestureListener {

    final OrthographicCamera camera;
    final Plane xyPlane = new Plane(new Vector3(0, 0, 1), 0);
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    public GameGestureListener(OrthographicCamera cam){
        this.camera = cam;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        System.out.println("touch down");
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        System.out.println("touch down");
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        System.out.println("fling "+velocityX+" "+velocityY+" "+button);

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        System.out.println("pan");
        Ray pickRay = camera.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xyPlane, curr);

        if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = camera.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xyPlane, delta);
            delta.sub(curr);
            camera.position.add(delta.x, delta.y, delta.z);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        System.out.println("pan stop");
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        System.out.println("zoom init: "+initialDistance+" dist: "+distance);
        //set zooming state on distance
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        System.out.println("pinch");
        return false;
    }

    @Override
    public void pinchStop() {
        System.out.println("pinch stop");
    }
}
