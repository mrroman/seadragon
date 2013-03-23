package seadragon;

import com.googlecode.lanterna.input.Key;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
public interface GameObject {

    void draw(App app);

    boolean collides(App app, int x1, int y1, int x2, int y2);

    void update(App app, Key key);

}
