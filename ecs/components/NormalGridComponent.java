package ecsTest.ecs.components;

import com.badlogic.ashley.core.Component;
import ecsTest.Grid;

public class NormalGridComponent implements Component {
    private Grid grid;

    public NormalGridComponent() {
        this.grid = new Grid();
        grid.setGridType(Grid.Gridtype.NORMAL);
    }

    public Grid getGrid() {
        return grid;
    }
}
