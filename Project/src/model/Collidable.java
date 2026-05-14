package model;

import java.awt.Rectangle;

public interface Collidable {

	boolean collidesWith(Collidable other);

	Rectangle getBounds();
}
