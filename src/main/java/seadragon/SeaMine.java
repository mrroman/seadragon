package seadragon;

import java.util.Arrays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class SeaMine implements GameObject {

    private int x;
    private int y1,y2;

    public SeaMine(int x, int y1, int y2) {
        this.x = x;
        this.y1 = y1;
        this.y2 = y2;
    }

    public int getTop() {
        return y1;
    }

    @Override
    public void draw(App app) {
        if (isVisible(app)) {
            app.putString(x - app.position, y1, "@", TextColor.ANSI.BLACK, TextColor.ANSI.BLUE);

            if (y1 < y2) {
                for (int i = y1+1; i < y2; i++) {
                    app.putString(x - app.position, i, "|", TextColor.ANSI.BLUE, TextColor.ANSI.BLACK);
                }
            }
        }

    }

    private boolean isVisible(App app) {
        TerminalSize size = app.screen.getTerminalSize();
        int realX = x - app.position;
        return 0 <= realX && realX < size.getColumns();

    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        int realX = x - app.position;

        if ((x1 <= realX && realX <= x2)
                && ((this.y1 <= y1 && y1 <= this.y2) || (this.y1 <= y2 && y1 <= this.y2))) {
            return true;
        }

        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(App app, KeyStroke key) {

    }

    public String toString() {
        return Arrays.asList(x, y1, y2).toString();
    }
}
