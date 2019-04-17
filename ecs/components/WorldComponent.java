package ecsTest.ecs.components;

import com.badlogic.ashley.core.Component;

public class WorldComponent implements Component {

    public enum ZoomLevel {
        TILE, GRID, WORLD
    }
    //initial zoomlevel
    ZoomLevel zoomLevel = ZoomLevel.TILE;

    public ZoomLevel getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(ZoomLevel zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}
