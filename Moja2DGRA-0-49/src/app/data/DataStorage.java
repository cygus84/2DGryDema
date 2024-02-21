package app.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{

	// PLAYER STATS
	int lelve;
	int maxLife;
	int life;
	int maxMana;
	int mana;
	int strenght;
	int dexterity;
	int exp;
	int nextLevelExp;
	int coin;
	
	// Player inventory
	
	ArrayList<String> itemNames = new ArrayList<String>();
	ArrayList<Integer> itemAmounts = new ArrayList<Integer>();
	int currentWeaponsSlot;
	int currentShieldSlot;
	
	// object on map
	String mapObjectNames[][];
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapObjectOpened[][];
}
