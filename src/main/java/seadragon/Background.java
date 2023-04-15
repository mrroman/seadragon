package seadragon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Background implements GameObject {

    public final List<Integer> levels = new ArrayList<Integer>();

    public final Random random = new Random(123456);

    public Background(int level) {
        levels.add(level);
    }

    @Override
    public void draw(App app) {
        TerminalSize size = app.screen.getTerminalSize();
        for (int i = 0; i < size.getColumns(); i++) {
            drawLine(app.screen, i, getLevel(app, i + app.position), size.getRows());
        }
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        int hx1 = getLevel(app, app.position + x1);
        int hx2 = getLevel(app, app.position + x2);

        if (hx1 < y2 || hx2 < y2)  {
            return true;
        }

        return false;
    }

    public int getLevel(App app, int i) {
        if (i < levels.size()) {
            return levels.get(i);
        } else {
            int compute = Math.max(2, Math.min(app.screen.getTerminalSize().getRows() - 3,
                    getLevel(app, i - 1) + (3 - random.nextInt(6))));

            levels.add(compute);
            return compute;
        }

    }

    @Override
    public void update(App app, KeyStroke key) {
        if (random.nextInt(80) == 1) {

            int x = levels.size();
            int level = getLevel(app, levels.size());
            app.addObject(new SeaMine(x, Math.max(2, level - random.nextInt(level/4) * 3), level));
        }
    }

    TextCharacter[] backgroundChar = TextCharacter.fromCharacter('#',
            TextColor.ANSI.MAGENTA,
            TextColor.ANSI.BLACK);

    private void drawLine(Screen screen, int x, int y1, int y2) {
        for (int i= y1; i< y2; i++) {
            screen.setCharacter(x, i, backgroundChar[0]);
        }
    }
}
