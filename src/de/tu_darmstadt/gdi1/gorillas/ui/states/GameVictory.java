package de.tu_darmstadt.gdi1.gorillas.ui.states;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.slick.BasicTWLGameState;
import de.matthiasmann.twl.slick.RootPane;
import de.tu_darmstadt.gdi1.gorillas.main.*;
import de.tu_darmstadt.gdi1.gorillas.main.Game;
import de.tu_darmstadt.gdi1.gorillas.utils.KeyMap;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


/**
 * GameVictor, gets triggered when a player reaches a specific Score
 * It calls the Game.Save actions on exit, which will Save the Highscore and Config File
 */
public class GameVictory extends BasicTWLGameState {

    private Color color = new Color(50,50,50,150);
    private Button btnNewGame;
    private Button btnMainMenu;
    private RootPane rp;
    private StateBasedGame game;
    private Label lWin;

    @Override
    public int getID() {
        return Game.GAMEVICTORY;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        if (Game.getInstance().isTestMode()) { return; } // Don't draw anything in testmode
        GamePlayState s = (GamePlayState) game.getState(Game.GAMEPLAYSTATE);
        s.render(gc, game, g);
        g.setColor(color);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        if(s.getActivePlayer() != null) {
            Player player = s.getActivePlayer();
            if(player != null) {
                g.setColor(Color.yellow);
                g.drawString("Herzlichen Glückwunsch " + player.getName() + "\nSie haben die Runde gewonnen !\nSie benötigten " + player.getThrow() + " Würfe.",100,80);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int i) throws SlickException {
        KeyMap.globalKeyPressedActions(container.getInput(), game);
    }

    @Override
    protected void layoutRootPane() {
        int paneHeight = this.getRootPane().getHeight();
        int paneWidth = this.getRootPane().getWidth();

        lWin.setSize(128, 32);
        lWin.setPosition((Gorillas.FRAME_WIDTH - lWin.getWidth()) / 2, 150);

        btnNewGame.setSize(128, 32);
        btnNewGame.setPosition((Gorillas.FRAME_WIDTH - btnNewGame.getWidth()) / 2, 450);

        btnMainMenu.setSize(128,32);
        btnMainMenu.setPosition((Gorillas.FRAME_WIDTH - btnMainMenu.getWidth()) / 2, 500);
    }

    @Override
    protected RootPane createRootPane() {
        rp = super.createRootPane();

        lWin = new Label("");

        btnNewGame = new Button("New Game");
        btnNewGame.addCallback(() -> game.enterState(Game.GAMESETUPSTATE));

        btnMainMenu = new Button("OK");
        btnMainMenu.addCallback(() -> game.enterState(Game.MAINMENUSTATE));

        rp.add(btnNewGame);
        rp.add(btnMainMenu);
        rp.add(lWin);

        return rp;
    }
}
