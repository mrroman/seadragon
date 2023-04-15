package seadragon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;

/**
 * Created with IntelliJ IDEA.
 * User: konrad
 * Date: 23.03.13
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class ScoreBoard implements GameObject {

    @Override
    public void draw(App app) {
        TerminalSize size = app.screen.getTerminalSize();
        int liveSize = (int)(size.getColumns()*0.6);
        int livePart = (int)(liveSize*app.lives/100.0);

        String scorePanel = String.format("  Score: %05d", app.score);
        StringBuilder sb = new StringBuilder();
        for (int i =0 ; i< liveSize; i++) {
            sb.append(' ');
        }

        app.putString(0, size.getRows() - 2, "  ", TextColor.ANSI.WHITE, TextColor.ANSI.BLUE);
        app.putString(2, size.getRows() - 2, sb.substring(0, livePart), TextColor.ANSI.BLACK, TextColor.ANSI.WHITE);
        app.putString(livePart + 2, size.getRows() - 2, sb.substring(0, liveSize - livePart), TextColor.ANSI.BLACK, TextColor.ANSI.RED);
        app.putString(liveSize + 2, size.getRows() - 2, scorePanel, TextColor.ANSI.WHITE, TextColor.ANSI.BLUE);
    }

    @Override
    public boolean collides(App app, int x1, int y1, int x2, int y2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(App app, KeyStroke key) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
