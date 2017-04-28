package gui;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.ControlScheme;
import enums.Command;
import enums.SimpleCommand;
import impl.EngineImpl;
import impl.PlayerImpl;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUIMain extends Application implements Observer {
	private final static String WINDOW_TITLE = "Combattant de rue 2";
	private final static String CONTROL_SCHEME_PATH = "keybind.json";
	
	//Model
	private final static int ARENA_WIDTH = 800;
	private final static int ARENA_HEIGHT = 600;
	private final static int SPACE_BETWEEN_PLAYERS = 500;
	
	private EngineThread engineThread;
	private EngineService engine;
	
	//VIEW
	public final static Color AREAN_DEBUG_COLOR = Color.WHITE;
	public final static Color P1_DEBUG_COLOR = Color.BLUE;
	public final static Color P2_DEBUG_COLOR = Color.RED;
	public final static Color LIFE_BAR_COLOR = Color.ORANGE;
	public final static Color LIFE_BAR_STROKE_COLOR = Color.BLACK;
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 600;
	public final static int LIFE_BAR_MARGIN = 15;
	
	
	private Rectangle arenaBox;
	
	private Rectangle p1Hitbox, p2Hitbox;
	private Rectangle p1Life, p2Life;
	
	
	private Scene scene;
	private Camera camera;

	public GUIMain() {}
	
	private void initModel() {
		try {
			ControlScheme.loadControlScheme(CONTROL_SCHEME_PATH);
		} catch (FileNotFoundException e) {
			// TODO handle this
			e.printStackTrace();
		}
		
		PlayerService p1 = new PlayerImpl();
		PlayerService p2 = new PlayerImpl();
		
		engineThread = new EngineThread();
		engineThread.init(ARENA_HEIGHT, ARENA_WIDTH, SPACE_BETWEEN_PLAYERS, p1, p2);
		
		engine = engineThread.getEngine();
		
		EngineImpl ei = (EngineImpl) engineThread.getEngine();
		
		ei.addObserver(this);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(WINDOW_TITLE);
		
		initModel();
		
		Group g = new Group();
		
		EngineService e = engineThread.getEngine();
		
		arenaBox = new Rectangle(e.width(), e.height());
		arenaBox.setFill(AREAN_DEBUG_COLOR);
		
		p1Life = new Rectangle(15, 15, getLifeBarWidth(0.5), 25);
		p2Life = new Rectangle(getLifeBarWidth(1.5), 15, getLifeBarWidth(0.5), 25);
		
		p1Hitbox = new Rectangle(e.character(0).positionX(), e.character(0).positionY(), e.character(0).charBox().width(), e.character(0).charBox().height());
		p2Hitbox = new Rectangle(e.character(1).positionX(), e.character(1).positionY(), e.character(1).charBox().width(), e.character(1).charBox().height());
		
		p1Hitbox.setFill(P1_DEBUG_COLOR);
		p2Hitbox.setFill(P2_DEBUG_COLOR);
		
		p1Life.setFill(LIFE_BAR_COLOR);
		p2Life.setFill(LIFE_BAR_COLOR);
		p1Life.setStroke(LIFE_BAR_STROKE_COLOR);
		p2Life.setStroke(LIFE_BAR_STROKE_COLOR);
		
		g.getChildren().add(arenaBox);
		
		g.getChildren().add(p1Life);
		g.getChildren().add(p2Life);
		
		g.getChildren().add(p1Hitbox);
		g.getChildren().add(p2Hitbox);
		
		scene = new Scene(g);
		camera = new PerspectiveCamera();
		
		scene.setCamera(camera);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyEvent);
		
		primaryStage.setScene(scene);
		
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		
		primaryStage.show();
		
		
		new Thread(engineThread).start();
		
	}
	
	
	private void handleKeyEvent(KeyEvent key) {
		SimpleCommand c1, c2;
		
		c1 = ControlScheme.parseCommand_p1(key.getText());
		c2 = ControlScheme.parseCommand_p2(key.getText());
		
		if (c1 != null)
			engineThread.getEngine().player(0).addCommand(c1);
		if (c2 != null)
			engineThread.getEngine().player(1).addCommand(c2);
	
	}
	
	private int getLifeBarWidth(double state) {
		return (int) (((WINDOW_WIDTH - LIFE_BAR_MARGIN) / 2) * state);
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		Logger.getAnonymousLogger().log(Level.INFO, "update");
		refreshPlayers();
	}

	private void refreshPlayers() {
		p1Hitbox.setX(engine.character(0).positionX());
		p1Hitbox.setY(engine.character(0).positionY());
		
		p2Hitbox.setX(engine.character(1).positionX());
		p2Hitbox.setY(engine.character(1).positionY());
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	
	
	
	
	
	
}
