package ecsTest.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import ecsTest.ecs.components.RenderableComponent;

/**
 * Cleanup routine
 */
public class CleanupSystem extends EntitySystem {
    private ComponentMapper<RenderableComponent> renderableMapper;

    public CleanupSystem(){
        renderableMapper = ComponentMapper.getFor(RenderableComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.setProcessing(false);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        for (Entity e :
                engine.getEntitiesFor(Family.one( RenderableComponent.class ).get())) {
            renderableMapper.get(e).dispose();
        }
    }
}
