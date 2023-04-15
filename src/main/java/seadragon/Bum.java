package seadragon;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;

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

    TextCharacter textCharacter = TextCharacter.fromCharacter('#',
            TextColor.ANSI.WHITE,
            TextColor.ANSI.YELLOW)[0];

    @Override
    public void draw(App app) {
        if (y-2 >= 0)
            app.screen.setCharacter(x,y-2,textCharacter);
        if (y-1 >= 0)
            app.putString(x-1,y-1, "###", TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        app.putString(x-2,y, "#####", TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        app..putString(x-1,y+1, "###", TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
        app.putString(x,y+2, "#", TextColor.ANSI.WHITE, TextColor.ANSI.YELLOW);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(App app, KeyStroke key) {
        if (time-- == 0) {
            app.removeObject(this);
        }
    }
}
