package gui;

import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import config.ControlScheme;
import enums.Command;
import impl.CharacterImpl;
import impl.EngineImpl;
import impl.FrameCounterImpl;
import impl.PlayerImpl;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	
	private EngineThread engineThreadRunnable;
	private Thread engineThread;
	private EngineService engine;
	
	//VIEW
	public final static Color AREAN_DEBUG_COLOR = Color.WHITE;
	public final static Color TEXT_DEBUG_COLOR = Color.WHITE;
	public final static Color P1_DEBUG_COLOR = Color.BLUE;
	public final static Color P2_DEBUG_COLOR = Color.RED;
	public final static Color LIFE_BAR_COLOR = Color.ORANGE;
	public final static Color LIFE_BAR_STROKE_COLOR = Color.BLACK;
	public final static Color TECH_HITBOX_COLOR = Color.BLACK;
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 650;
	public final static int LIFE_BAR_MARGIN = 15;
	
	
	private Rectangle arenaBox;
	
	private Rectangle p1Hitbox, p2Hitbox;
	private Rectangle p1Life, p2Life;
	private Label label1, label2;
	
	private Rectangle[] techHitboxes;
	
	private Scene scene;
	private Camera camera;

	private Group mainGroup;
	
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
		
		
		engineThreadRunnable = new EngineThread();
		engineThreadRunnable.init(ARENA_HEIGHT, ARENA_WIDTH, SPACE_BETWEEN_PLAYERS, p1, p2, new FrameCounterImpl());
		
		engine = engineThreadRunnable.getEngine();
		
		EngineImpl ei = (EngineImpl) engineThreadRunnable.getEngine();
		
		ei.addObserver(this);

		CharacterImpl ci = (CharacterImpl) ei.character(0);
		ci.addObserver(this);
		
		ci = (CharacterImpl) ei.character(1);
		ci.addObserver(this);
	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(WINDOW_TITLE);
		
		initModel();
		
		Group g = new Group();
		
		mainGroup = g;
		
		EngineService e = engineThreadRunnable.getEngine();
		
		arenaBox = new Rectangle(e.width(), e.height());
		arenaBox.setFill(AREAN_DEBUG_COLOR);
		
		p1Life = new Rectangle(15, 15, getLifeBarWidth(0.5), 25);
		p2Life = new Rectangle(getLifeBarWidth(1.5), 15, getLifeBarWidth(0.5), 25);
		
		p1Hitbox = new Rectangle(e.character(0).positionX(), e.character(0).positionY(), e.character(0).charBox().width(), e.character(0).charBox().height());
		p2Hitbox = new Rectangle(e.character(1).positionX(), e.character(1).positionY(), e.character(1).charBox().width(), e.character(1).charBox().height());
		
		label1 = new Label();
		label2 = new Label();
		
		label1.setTextFill(TEXT_DEBUG_COLOR);
		label2.setTextFill(TEXT_DEBUG_COLOR);
		
		p1Hitbox.setFill(P1_DEBUG_COLOR);
		p2Hitbox.setFill(P2_DEBUG_COLOR);
		
		p1Life.setFill(LIFE_BAR_COLOR);
		p2Life.setFill(LIFE_BAR_COLOR);
		p1Life.setStroke(LIFE_BAR_STROKE_COLOR);
		p2Life.setStroke(LIFE_BAR_STROKE_COLOR);
		

		techHitboxes = new Rectangle[2];
		techHitboxes[0] = new Rectangle();
		techHitboxes[0].setFill(TECH_HITBOX_COLOR);
		techHitboxes[0].setVisible(false);
		techHitboxes[1] = new Rectangle();
		techHitboxes[1].setFill(TECH_HITBOX_COLOR);
		techHitboxes[1].setVisible(false);
		
		
		
		g.getChildren().add(arenaBox);
		
		g.getChildren().add(p1Life);
		g.getChildren().add(p2Life);
		
		g.getChildren().add(p1Hitbox);
		g.getChildren().add(p2Hitbox);
		
		g.getChildren().add(label1);
		g.getChildren().add(label2);
		
		g.getChildren().add(techHitboxes[0]);
		g.getChildren().add(techHitboxes[1]);
		
		scene = new Scene(g);
		camera = new PerspectiveCamera();
		
		scene.setCamera(camera);
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressedEvent);
		scene.addEventHandler(KeyEvent.KEY_RELEASED, this::keyReleasedEvent);
		
		
		primaryStage.setScene(scene);
		
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		
		engineThread = new Thread(engineThreadRunnable);
		engineThread.start();

//		engine.character(0).
		
		refreshPlayers();
		
		
		
		primaryStage.show();
		
		
		
		primaryStage.setOnCloseRequest(window -> {
			engineThreadRunnable.setOn(false);
			engineThread.interrupt();
		});
	}
	
	
	private void keyPressedEvent(KeyEvent key) {
		Command c1, c2;

		c1 = ControlScheme.parseCommand_p1(key.getCode().name());
		c2 = ControlScheme.parseCommand_p2(key.getCode().name());
		
		if (c1 != null)
			engineThreadRunnable.getEngine().player(0).inputManager().setPressed(c1);
		if (c2 != null)
			engineThreadRunnable.getEngine().player(1).inputManager().setPressed(c2);
	}
	
	private void keyReleasedEvent(KeyEvent key) {
		Command c1, c2;

		c1 = ControlScheme.parseCommand_p1(key.getCode().name());
		c2 = ControlScheme.parseCommand_p2(key.getCode().name());
		
		if (c1 != null)
			engineThreadRunnable.getEngine().player(0).inputManager().setReleased(c1);
		if (c2 != null)
			engineThreadRunnable.getEngine().player(1).inputManager().setReleased(c2);
	}
	
	private int getLifeBarWidth(double state) {
		return (int) (((WINDOW_WIDTH - LIFE_BAR_MARGIN) / 2) * state);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Platform.runLater(this::refreshPlayers);
	}

	private void refreshPlayers() {
		// Hitbox
		p1Hitbox.setX(engine.character(0).positionX());
		p1Hitbox.setY(engine.character(0).positionY());
		p1Hitbox.setHeight(engine.character(0).charBox().height());
		
		p2Hitbox.setX(engine.character(1).positionX());
		p2Hitbox.setY(engine.character(1).positionY());
		p2Hitbox.setHeight(engine.character(1).charBox().height());
		
		// Debug text
		label1.setText(getDebugText(engine.player(0)));
		label2.setText(getDebugText(engine.player(1)));
		
		label1.setLayoutX(engine.character(0).positionX());
		label1.setLayoutY(engine.character(0).positionY());
		
		label2.setLayoutX(engine.character(1).positionX());
		label2.setLayoutY(engine.character(1).positionY());
		
		// Tech Hitbox
		updatePlayerTechnicHitbox(0);
		updatePlayerTechnicHitbox(1);
		
	}
	
	private void updatePlayerTechnicHitbox(int player) {
		CharacterImpl c = (CharacterImpl) engine.character(player);
		
		if (c.usingTechnic()) {
			System.out.println("gen tech");
			techHitboxes[player].setX(c.currentTechnicHitbox().positionX());
			techHitboxes[player].setY(c.currentTechnicHitbox().positionY());
			techHitboxes[player].setWidth(c.currentTechnicHitbox().width());
			techHitboxes[player].setHeight(c.currentTechnicHitbox().height());
			techHitboxes[player].setVisible(true);
			System.out.println("visible : " + techHitboxes[player].isVisible());
			System.out.println("hit : " + c.currentTechnicHitbox().positionX() +", " +c.currentTechnicHitbox().positionY());
			System.out.println("contains : " + mainGroup.getChildren().contains(techHitboxes[player]));
			
		} else {
			if (techHitboxes[player].isVisible()) {
				techHitboxes[player].setVisible(false);
			}
		}
		
	}
	
	public String getDebugText(PlayerService player) {
		String result = "";
		
		if (player.character().faceRight())
			result += "->";
		else 
			result += "<-";
		
		result += "\n";
		
		result += "l:" + player.character().life() + "\n";
		result += "d:" + player.character().dead() + "\n";
		result += player.character().positionX() + "," + player.character().positionY() + "\n";
		result += "tech:" + player.character().usingTechnic() + "\n";
		result += "stun:" + player.character().stunned() + "\n";
			
		return result;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
	
	
	
	
	
	
	
}
