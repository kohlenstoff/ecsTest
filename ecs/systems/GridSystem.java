package ecsTest.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import ecsTest.ecs.components.GridMapComponent;
import ecsTest.ecs.components.NormalGridComponent;
import ecsTest.ecs.components.IsometricGridComponent;
import ecsTest.ecs.components.WorldComponent;

public class GridSystem extends EntitySystem {

    private ComponentMapper<IsometricGridComponent> isometricGridMapper;
    private ComponentMapper<NormalGridComponent> normalGridMapper;

    public GridSystem(){
        isometricGridMapper = ComponentMapper.getFor( IsometricGridComponent.class );
        normalGridMapper = ComponentMapper.getFor( NormalGridComponent.class );
    }

    @Override
    public void update(float deltaTime) {
        /*
        TODO: First both for-loops should only run if in ZoomLevel.TERRITORY, third loop only in ZoomLevel.GRID
         */

        //the grid on each tile/area -> ISOMETRIC
        for (Entity e:
                getEngine().getEntitiesFor( Family.all( IsometricGridComponent.class ).get() )) {

        }
        //for the grid that consists of tiles in the world -> ISOMETRIC
        for (Entity e:
                getEngine().getEntitiesFor( Family.all( IsometricGridComponent.class, WorldComponent.class ).get() )) {

        }
        //the gridmap in zoomlevel grid -> NORMAL
        for (Entity e:
                getEngine().getEntitiesFor( Family.all( NormalGridComponent.class, GridMapComponent.class).get() )) {

        }
    }
}
