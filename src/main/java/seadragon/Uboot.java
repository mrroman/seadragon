package seadragon;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
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
        app.putString(x, y, UBOOT_UP, TextColor.ANSI.YELLOW, TextColor.ANSI.BLACK);
        app.putString(x, y + 1, UBOOT_DOWN, TextColor.ANSI.BLACK, TextColor.ANSI.YELLOW);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return false;
    }

    @Override
    public void update(App app, KeyStroke key) {
        if (key != null) {
            handleKeys(app, key);
        }

        List<GameObject> collides = app.checkCollisions(x,y,x+UBOOT_DOWN.length(), y + 2);
        if (collides.size() > 0) {
            app.addObject(new Bum(x,y));
            app.kill();
        }
    }

    private void handleKeys(App app, KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp)  {
            y = Math.max(0, y - 1);
        }

        if (key.getKeyType() == KeyType.ArrowLeft)  {
            x = Math.max(0, x - 1);
        }

        if (key.getKeyType() == KeyType.ArrowDown)  {
            y = Math.min(app.screen.getTerminalSize().getRows() - 1, y + 1);
        }

        if (key.getKeyType() == KeyType.ArrowRight)  {
            x = Math.min(app.screen.getTerminalSize().getColumns() - 1, x + 1);
        }

        if (key.getCharacter() == ' ') {
            app.addObject(new Missle(x + UBOOT_DOWN.length() + 1, y + 1));
        }
    }
}
