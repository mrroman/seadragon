package seadragon;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import java.util.ArrayList;
import java.util.List;

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

    public App() {
        screen = TerminalFacade.createScreen(new SwingTerminal(80,24));
        background = new Background(screen.getTerminalSize().getRows() / 4);
        scoreBoard = new ScoreBoard();
        uboot = new Uboot(0,0);
        objects.add(background);
        objects.add(uboot);
    }

    public void run() {
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

    private void drawObjects() {
        screen.clear();
        for (GameObject object : objects) {
            object.draw(this);
        }
        scoreBoard.draw(this);
        screen.refresh();
    }

    private void updateGame() {
        Key key = screen.readInput();

        if (key != null) {
            handleKeys(key);
        }

        List<GameObject> currentObjects = new ArrayList<GameObject>(objects);
        for (GameObject object : currentObjects) {
            object.update(this, key);
        }
    }

    private void handleKeys(Key key) {
        if (key.getCharacter() == 'q')  {
            lives = 0;
        }

    }

    public static void main( String[] args ) {
        new App().run();
    }

    public void kill() {
        lives--;
    }
}
