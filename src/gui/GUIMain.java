package gui;

import impl.EngineImpl;
import interfaceservice.EngineService;
import interfaceservice.PlayerService;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUIMain extends Application {
	private final static String WINDOW_TITLE = "Combattant de rue 2";
	
	
	
	//Model
	private final static int ARENA_WIDTH = 800;
	private final static int ARENA_HEIGHT = 600;
	private final static int SPACE_BETWEEN_PLAYERS = 50;
	
	
	private EngineService engine;
	private PlayerService p1, p2;
	
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

	public GUIMain() {
	}
	
	private void initModel() {
		engine = new EngineImpl();
		engine.init(ARENA_HEIGHT, ARENA_WIDTH, SPACE_BETWEEN_PLAYERS, p1, p2);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(WINDOW_TITLE);
		
		initModel();
		
		Group g = new Group();
		
		
		arenaBox = new Rectangle(800, 600);
		arenaBox.setFill(AREAN_DEBUG_COLOR);
		p1Life = new Rectangle(15, 15, getLifeBarWidth(0.5), 25);
		p2Life = new Rectangle(getLifeBarWidth(1.5), 15, getLifeBarWidth(0.5), 25);
		
		p1Hitbox = new Rectangle(0, 500, 50, 100);
		p2Hitbox = new Rectangle(750, 500, 50, 100);
		
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
		
		primaryStage.setScene(scene);
		
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		
		primaryStage.show();
		
	}
	
	
	private int getLifeBarWidth(double state) {
		return (int) (((WINDOW_WIDTH - LIFE_BAR_MARGIN) / 2) * state);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
