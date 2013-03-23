package seadragon;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 16:23
 * To change this template use File | Settings | File Templates.
 */
public class Uboot implements GameObject {

    public static final String UBOOT_UP   = "__/|_";
    public static final String UBOOT_DOWN = "\\___/";
    private int x, y;

    public Uboot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(App app) {
        app.screen.putString(x, y, UBOOT_UP, Terminal.Color.YELLOW, Terminal.Color.BLACK);
        app.screen.putString(x, y + 1, UBOOT_DOWN, Terminal.Color.BLACK, Terminal.Color.YELLOW);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return false;
    }

    @Override
    public void update(App app, Key key) {
        if (key != null) {
            handleKeys(app, key);
        }

        List<GameObject> collides = app.checkCollisions(x,y,x+UBOOT_DOWN.length(), y + 2);
        if (collides.size() > 0) {
            app.addObject(new Bum(x,y));
            app.kill();
        }
    }

    private void handleKeys(App app, Key key) {
        if (key.getKind() == Key.Kind.ArrowUp)  {
            y = Math.max(0, y - 1);
        }

        if (key.getKind() == Key.Kind.ArrowLeft)  {
            x = Math.max(0, x - 1);
        }

        if (key.getKind() == Key.Kind.ArrowDown)  {
            y = Math.min(app.screen.getTerminalSize().getRows() - 1, y + 1);
        }

        if (key.getKind() == Key.Kind.ArrowRight)  {
            x = Math.min(app.screen.getTerminalSize().getColumns() - 1, x + 1);
        }

        if (key.getCharacter() == ' ') {
            app.addObject(new Missle(x + UBOOT_DOWN.length() + 1, y + 1));
        }
    }
}
