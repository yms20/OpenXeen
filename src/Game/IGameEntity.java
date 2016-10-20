package Game;

import Game.Map.MaMWorld;
import Rendering.IRelativeToLocationSprite;
import Toolbox.Direction;
import com.sun.istack.internal.NotNull;
import mamFiles.MaMThing;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by duckman on 3/07/2016.
 *
 * If its in the world, and it not the floor, its an entity.
 */
public interface IGameEntity extends IUpdateable
{
    /**
     * World space position of the item.
     */
    @NotNull Point getLocation();

    /**
     * Direction the entity is pointing.
     * @return NULL iff the entity has no direction (monsters only face the user)
     */
    Direction getHeading();

    /**
     * False means "not part of the game".
     * NB: does not mean not updateable.
     *
     */
    boolean isVisible();

    /**
     * Gets a sprite for use in game.
     */
    IRelativeToLocationSprite getSprite();

    static IGameEntity fromRenderable(MaMThing thing, Point location, Direction heading, IScript<IGameEntity> onUpdate) {
        return new BoundRenderable(location, heading, thing, onUpdate);
    }

    static IGameEntity fromRenderable(MaMThing thing, int wsX, int wsY, Direction heading, IScript<IGameEntity> onUpdate) {
        return new BoundRenderable(new Point(wsX, wsY), heading, thing, onUpdate);
    }
}

class BoundRenderable implements IGameEntity {
    protected Point location;
    protected Direction heading;
    protected boolean visible;
    protected IRelativeToLocationSprite sprite;
    protected IScript<IGameEntity> onUpdate;

    public BoundRenderable(Point location, Direction heading, boolean visible, IRelativeToLocationSprite sprite, IScript<IGameEntity> onUpdate) {
        this.location = location;
        this.heading = heading;
        this.visible = visible;
        this.sprite = sprite;
        this.onUpdate = onUpdate;
    }

    public BoundRenderable(Point location, Direction heading, IRelativeToLocationSprite sprite, IScript<IGameEntity> onUpdate) {
        this.location = location;
        this.heading = heading;
        this.sprite = sprite;
        this.onUpdate = onUpdate;
        visible = true;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public Direction getHeading() {
        return heading;
    }

    public void setHeading(Direction heading) {
        this.heading = heading;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public IRelativeToLocationSprite getSprite() {
        return sprite;
    }

    public void setSprite(IRelativeToLocationSprite sprite) {
        this.sprite = sprite;
    }

    public IScript<IGameEntity> getOnUpdate() {
        return onUpdate;
    }

    public void setOnUpdate(IScript<IGameEntity> onUpdate) {
        this.onUpdate = onUpdate;
    }

    @Override
    public void update(MaMWorld world) {
        if(onUpdate != null) {
            onUpdate.run(world, this);
        }
    }
}
