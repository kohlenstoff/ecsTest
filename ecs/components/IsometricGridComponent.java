package ecsTest.ecs.components;

import com.badlogic.ashley.core.Component;
import ecsTest.Grid;

public class IsometricGridComponent implements Component {
    private Grid grid;

    public IsometricGridComponent() {
        this.grid = new Grid();
        grid.setGridType(Grid.Gridtype.ISOMETRIC);
        grid.setTiles(20);
        grid.setTileSize(10);
        grid.calculate();
    }

    public Grid getGrid() {
        return grid;
    }
}
