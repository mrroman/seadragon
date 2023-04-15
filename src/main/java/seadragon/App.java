package seadragon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

/**
 * Hello world!
 *
 */
public class App
{
    public final Screen screen;
    public int position = 0;
    public int score = 0;
    public int lives = 100;

    public transient int tick = 0;

    public final List<GameObject> objects = new ArrayList<GameObject>();
    public final Background background;
    public final Uboot uboot;
    public final ScoreBoard scoreBoard;

    public App() throws IOException {
        screen = new DefaultTerminalFactory().createScreen();
        background = new Background(screen.getTerminalSize().getRows() / 4);
        scoreBoard = new ScoreBoard();
        uboot = new Uboot(0,0);
        objects.add(background);
        objects.add(uboot);
    }

    public void run() throws IOException {
        screen.startScreen();

        while (lives > 0) {
            updateGame();
            drawObjects();
            tick++;

            if (tick % 5 == 1) {
                position ++;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        screen.stopScreen();
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }

    public List<GameObject> checkCollisions(int x1, int y1, int x2, int y2) {
        List<GameObject> collides = new ArrayList<GameObject>();

        for (GameObject object : objects) {
            if (object.collides(this, x1, y1, x2, y2)) {
                collides.add(object);
            }
        }

        return collides;
    }

    public void putString(int x, int y, String s, TextColor foreground, TextColor background) {
        var chars = TextCharacter.fromString(s, foreground, background);

        for (int i = 0; i < chars.length; i++) {
            screen.setCharacter(i + x, y, chars[i]);
        }
    }

    private void drawObjects() throws IOException {
        screen.clear();
        for (GameObject object : objects) {
            object.draw(this);
        }
        scoreBoard.draw(this);
        screen.refresh();
    }

    private void updateGame() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key != null) {
            handleKeys(key);
        }

        List<GameObject> currentObjects = new ArrayList<GameObject>(objects);
        for (GameObject object : currentObjects) {
            object.update(this, key);
        }
    }

    private void handleKeys(KeyStroke key) {
        if (key.getCharacter() == 'q')  {
            lives = 0;
        }

    }

    public static void main( String[] args ) throws Exception {
        new App().run();
    }

    public void kill() {
        lives--;
    }
}
