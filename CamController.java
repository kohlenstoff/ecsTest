package ecsTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

/**
 * Cam controlling for DESKTOP
 */
public class CamController extends InputAdapter {

    public enum ZoomLevel {
        TILE, GRID, WORLD
    }
    //initial zoomlevel
    ZoomLevel zoomLevel = ZoomLevel.TILE;

    int zoomDistance = 10;
    int zoomedMeters = 0;

    final OrthographicCamera camera;
    final Plane xyPlane = new Plane(new Vector3(0, 0, 1), 0);

    boolean zoomLock = false;
    int boundaryMinX = 0;
    int boundaryMinY = 0;
    int boundaryMaxX = 0;
    int boundaryMaxY = 0;

    boolean freeCameraMovement = true;

    public CamController(OrthographicCamera camera) {
        this.camera = camera;
        //set initial zoom
        camera.zoom = 0.1f*zoomDistance;
    }

    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Ray pickRay = camera.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xyPlane, curr);

        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = camera.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xyPlane, delta);
            delta.sub(curr);
            //movement constrained?
            if (freeCameraMovement) {
                camera.position.add(delta.x, delta.y, delta.z);
            } else {
                //check collision with set bounds
                if (camera.position.x == boundaryMinX || camera.position.x == boundaryMaxX) {
                    camera.position.add(0, delta.y, delta.z);
                } else if (camera.position.y == boundaryMinY || camera.position.y == boundaryMaxY) {
                    camera.position.add(delta.x, 0, delta.z);
                } else {
                    camera.position.add(delta.x, delta.y, delta.z);
                }
            }
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {

        //zoom Lock
        if ((zoomLevel == ZoomLevel.TILE && zoomDistance == 1 && amount == -1) || (zoomLevel == ZoomLevel.WORLD && zoomDistance == 20 && amount == 1)) {
            zoomLock = true;
        }
        if ((zoomLevel == ZoomLevel.TILE && zoomDistance == 1 && amount == 1) || (zoomLevel == ZoomLevel.WORLD && zoomDistance == 20 && amount == -1)) {
            zoomLock = false;
        }
        /*
        TODO: Needs to be moved into a system!?
        */
        if (zoomLevel == ZoomLevel.GRID && zoomDistance == 1 && amount == -1) {
            //entering TILE from GRID
            System.out.println("TILE from GRID");
            zoomLevel = ZoomLevel.TILE;
            zoomDistance = 21;
            camera.zoom = 2.1f;
            
            //deactivate stuff from the previous zoomLevel
            //activate stuff _ONCE_ for this particular zoomLevel when reaching it
        }
        if (zoomLevel == ZoomLevel.TILE && zoomDistance == 20 && amount == 1) {
            //entering GRID from TILE
            System.out.println("GRID from TILE");
            zoomLevel = ZoomLevel.GRID;
            zoomDistance = 0;
            camera.zoom = 0f;
            
            //deactivate stuff from the previous zoomLevel
            //activate stuff _ONCE_ for this particular zoomLevel when reaching it
        }
        if (zoomLevel == ZoomLevel.WORLD && zoomDistance == 1 && amount == -1) {
            //entering GRID from WORLD
            System.out.println("GRID from WORLD");
            zoomLevel = ZoomLevel.GRID;
            zoomDistance = 21;
            camera.zoom = 2.1f;
            
            //deactivate stuff from the previous zoomLevel
            //activate stuff _ONCE_ for this particular zoomLevel when reaching it
        }
        if (zoomLevel == ZoomLevel.GRID && zoomDistance == 20 && amount == 1) {
            //entering WORLD from GRID
            System.out.println("WORLD from GRID");
            zoomLevel = ZoomLevel.WORLD;
            zoomDistance = 0;
            camera.zoom = 0f;
            
            //deactivate stuff from the previous zoomLevel
            //activate stuff _ONCE_ for this particular zoomLevel when reaching it
        }

        if (!zoomLock) {
            zoomDistance = zoomDistance + amount;
            camera.zoom = camera.zoom + amount * 0.1f;
        }

        return false;
    }

    public boolean isFreeCameraMovement() {
        return freeCameraMovement;
    }

    public void setFreeCameraMovement(boolean freeCameraMovement) {
        this.freeCameraMovement = freeCameraMovement;
    }

    public ZoomLevel getZoomLevel() {
        return zoomLevel;
    }
}
