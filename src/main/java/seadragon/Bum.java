package seadragon;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class Bum implements GameObject {

    private int x, y;
    private int time = 10;

    public Bum(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(App app) {
        if (y-2 >= 0)
            app.screen.putString(x,y-2, "#", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        if (y-1 >= 0)
            app.screen.putString(x-1,y-1, "###", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        app.screen.putString(x-2,y, "#####", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        app.screen.putString(x-1,y+1, "###", Terminal.Color.WHITE, Terminal.Color.YELLOW);
        app.screen.putString(x,y+2, "#", Terminal.Color.WHITE, Terminal.Color.YELLOW);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(App app, Key key) {
        if (time-- == 0) {
            app.removeObject(this);
        }
    }
}
