package de.tu_darmstadt.gdi1.gorillas.main;

import de.matthiasmann.twl.slick.TWLStateBasedGame;
import de.tu_darmstadt.gdi1.gorillas.ui.states.*;
import de.tu_darmstadt.gdi1.gorillas.utils.Utils;
import eea.engine.entity.StateBasedEntityManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.net.URL;

public class Gorillas extends TWLStateBasedGame {

    public static Gorillas game;

    /* State Definitions */
    public static final int MAINMENUSTATE   = 0;
    public static final int GAMESETUPSTATE  = 1;
    public static final int GAMEPLAYSTATE   = 2;
    public static final int HIGHSCORESTATE  = 3;
    public static final int OPTIONSTATE     = 4;
    public static final int TUTORIALSTATE   = 5;
    public static final int INGAMEPAUSE     = 6;
    public static final int HELPSTATE       = 7;
    public static final int INGAMEWIN       = 8;

    /* Global Parameters */
    public static final int FRAME_WIDTH     = 800;
    public static final int FRAME_HEIGHT    = 600;
    public static final int TARGET_FPS      = 120;

    public static final String THEME    = "/theme.xml";

    /* Global Variables */
    public static Player player1;
    public static Player player2;

    public static boolean debug = false;

    public Gorillas(boolean debug) {
        super("Gorillas");
        setDebug(debug);
    }

    public static void setDebug(boolean debuging) {
        debug = debuging;
    }

    public static void main(String[] args) throws SlickException {
        Utils.setNativePath();
        game = new Gorillas(false);
        AppGameContainer app = new AppGameContainer(game);
        app.setShowFPS(true);
        app.setDisplayMode(FRAME_WIDTH, FRAME_HEIGHT, false);
        app.setTargetFrameRate(TARGET_FPS);
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        // Add states to the StateBasedGame
        this.addState(new MainMenuState());
        this.addState(new GameSetupState());
        this.addState(new GamePlayState());
        this.addState(new InGamePause());
        this.addState(new HelpState());
        this.addState(new InGameWin());
        this.addState(new OptionState());
        // TODO: Add the other states...

        // Add states to the StateBasedEntityManager
        StateBasedEntityManager.getInstance().addState(MAINMENUSTATE);
        StateBasedEntityManager.getInstance().addState(GAMESETUPSTATE);
        StateBasedEntityManager.getInstance().addState(GAMEPLAYSTATE);
        StateBasedEntityManager.getInstance().addState(INGAMEPAUSE);
        StateBasedEntityManager.getInstance().addState(HELPSTATE);
        StateBasedEntityManager.getInstance().addState(INGAMEWIN);
        StateBasedEntityManager.getInstance().addState(OPTIONSTATE);
        // TODO: Add the other states...
    }

    @Override
    protected URL getThemeURL() {
        return getClass().getResource(THEME);
    }
}
