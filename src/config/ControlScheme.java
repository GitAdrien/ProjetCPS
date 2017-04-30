	package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;

import enums.Command;
import enums.SimpleCommand;


public class ControlScheme {
	private final static String PLAYER_NODE = "player";
	
	
	private static Map<String, SimpleCommand> p1;
	private static Map<String, SimpleCommand> p2;
	
	
	public static void loadControlScheme(String configFilePath) throws FileNotFoundException {
		File f = new File(configFilePath);
		String content;
		JSONObject root, p1, p2;

		content = readFile(f);
		root = new JSONObject(content);
			 œ
		p1 = root.getJSONObject(PLAYER_NODE + "1");
		p2 = root.getJSONObject(PLAYER_NODE + "2");
		
		parsePlayer(p1, 1);
		parsePlayer(p2, 2);
			
			
			
		
		
	}
	
	private static void parsePlayer(JSONObject playerNode, int p) {
		HashMap<String, SimpleCommand> controls = new HashMap<>();
		Map<String, Object> node = playerNode.toMap();
		SimpleCommand crtCmd;
		for (String key : node.keySet()) {
			crtCmd = SimpleCommand.parseString(key);
			
			if (crtCmd == null) {
				System.out.println("Unkown command " + key);
			} else {
				controls.put((String) node.get(key), crtCmd);
			}
			
		}
		
		if (p == 1)
			ControlScheme.p1 = controls;
		else if (p == 2)
			ControlScheme.p2 = controls;
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
	
	
	public static Map<String, SimpleCommand> getP1() {
		return p1;
	}
	
	public static Map<String, SimpleCommand> getP2() {
		return p2;
	}
	
	private static SimpleCommand parseCommand_aux(int player, String key) {
		if (player < 0 || player > 2) {
			System.out.println("Player number out of bound.");
			return null;
		} else if (p1 == null || p2 == null) {
			System.out.println("Command schemes has not been loaded yet.");
			return null;
		}
		
		
		if (player == 0)
			return p1.get(key);
		else
			return p2.get(key);
	}

	
	public static SimpleCommand parseCommand_p1(String key) {
		return parseCommand_aux(0, key);
	}
	
	public static SimpleCommand parseCommand_p2(String key) {
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
