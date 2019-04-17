package ecsTest.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ecsTest.ecs.components.CameraComponent;
import ecsTest.ecs.components.InputComponent;
import ecsTest.ecs.components.PositionComponent;
import ecsTest.ecs.components.RenderableComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {
    private ComponentMapper<RenderableComponent> renderableMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private Entity cameraEntity;

    SpriteBatch batch;

    public RenderingSystem(SpriteBatch spriteBatch){
        super( Family.all(RenderableComponent.class, PositionComponent.class).get(), new ZComparator() );

        renderableMapper = ComponentMapper.getFor( RenderableComponent.class );
        positionMapper = ComponentMapper.getFor( PositionComponent.class );

        batch = spriteBatch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        cameraEntity = engine.getEntitiesFor( Family.one(CameraComponent.class).get() ).first();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        //Render entity
        //don't need entity.has(), because Family restricts)
        batch.setProjectionMatrix( cameraEntity.getComponent(CameraComponent.class).getCam().combined );
        batch.begin();
        batch.draw( renderableMapper.get(entity).getSprite(), positionMapper.get(entity).x, positionMapper.get(entity).y );
        batch.end();
    }

    private static class ZComparator implements Comparator<Entity> {
        private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum( pm.get(e1).z - pm.get(e2).z );
        }
    }
}
