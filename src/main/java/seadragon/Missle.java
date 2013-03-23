package seadragon;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
public class Missle implements GameObject {

    private int x;
    private int y;

    private int fuel = 200;

    public Missle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(App app) {
        app.screen.putString(x, y, "*", Terminal.Color.BLACK, Terminal.Color.RED);
        app.screen.putString(x+1, y, "-", Terminal.Color.BLACK, Terminal.Color.YELLOW);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return (x1 <= x+1 && x+1 <= x2) && (y1 <= y &&  y <= y2);
    }

    @Override
    public void update(App app, Key key) {
        if (app.tick % 4 == 1) {
            x++;
        }

        if (fuel-- == 0) {
            app.removeObject(this);
            app.score = Math.max(0, app.score - 10);
            return;
        }

        List<GameObject> collides = app.checkCollisions(x, y, x + 1, y);
        if (collides.size() > 1) {
            for (GameObject object : collides) {
                if (object instanceof Background) {
                    app.removeObject(this);
                }
                if (object instanceof SeaMine) {
                    SeaMine seaMine = (SeaMine)object;
                    if (seaMine.getTop() == y) {
                        app.removeObject(this);
                        app.removeObject(object);
                        app.score += 20;
                        app.addObject(new Bum(x+1,y));
                    }
                }
            }
        }
    }
}
