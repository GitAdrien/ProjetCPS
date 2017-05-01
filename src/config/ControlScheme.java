	package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import enums.Command;
import enums.attack.SimpleAttackCommand;
import enums.direction.SimpleDirectionCommand;


public class ControlScheme {
	private final static String PLAYER_NODE = "player";
	
	
	private static Map<String, SimpleDirectionCommand> p1_dir, p2_dir;
	private static Map<String, SimpleAttackCommand> p1_att, p2_att;
	
	
	public static void loadControlScheme(String configFilePath) throws FileNotFoundException {
		File f = new File(configFilePath);
		String content;
		JSONObject root, p1, p2;

		content = readFile(f);
		root = new JSONObject(content);
		p1 = root.getJSONObject(PLAYER_NODE + "1");
		p2 = root.getJSONObject(PLAYER_NODE + "2");
		
		parsePlayer(p1, 1);
		parsePlayer(p2, 2);
			
			
			
		
		
	}
	
	private static void parsePlayer(JSONObject playerNode, int p) {
		HashMap<String, SimpleDirectionCommand> dirControls = new HashMap<>();
		HashMap<String, SimpleAttackCommand> attControls = new HashMap<>();
		Map<String, Object> node = playerNode.toMap();
		SimpleDirectionCommand crtDirCmd;
		SimpleAttackCommand crtAttCmd;
		for (String key : node.keySet()) {
			crtDirCmd = SimpleDirectionCommand.parseString(key);
			crtAttCmd = SimpleAttackCommand.parseString(key);
			
			if (crtDirCmd != null) 
				dirControls.put((String) node.get(key), crtDirCmd);
			
			if (crtAttCmd != null)
				attControls.put((String) node.get(key), crtAttCmd);
			
		}
		
		if (p == 1) {
			ControlScheme.p1_dir = dirControls;
			ControlScheme.p1_att = attControls;
		} else if (p == 2) {
			ControlScheme.p2_dir = dirControls;
			ControlScheme.p2_att = attControls;
		}
	}
	
	public static String readFile(File f) throws FileNotFoundException {
		String result = "";
		
		Scanner sc = new Scanner(f);
		
		
		while (sc.hasNext()) {
			result += sc.nextLine();
		}
		
		sc.close();
		
		return result;
	}
	
	
	public static Map<String, SimpleDirectionCommand> getP1() {
		return p1_dir;
	}
	
	public static Map<String, SimpleDirectionCommand> getP2() {
		return p2_dir;
	}
	
	private static Command parseCommand_aux(int player, String key) {
		if (player < 0 || player > 2) {
			System.out.println("Player number out of bound.");
			return null;
		} else if (p1_dir == null || p2_dir == null) {
			System.out.println("Command schemes has not been loaded yet.");
			return null;
		}
		
		Command result = null;
		
		if (player == 0) {
			result = p1_dir.get(key);
			if (result == null)
				result = p1_att.get(key);
		} else {
			result = p2_dir.get(key);
			if (result == null)
				result = p2_att.get(key);
		}

	
		return result;
	}

	
	public static Command parseCommand_p1(String key) {
		return parseCommand_aux(0, key);
	}
	
	public static Command parseCommand_p2(String key) {
		return parseCommand_aux(1, key);
	}
	
	public static void main(String[] args) {
		try {
			loadControlScheme("keybind.json");
			
			getP1().forEach((k, v) -> System.out.println(k + " -> " + v));
			getP2().forEach((k, v) -> System.out.println(k + " -> " + v));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
