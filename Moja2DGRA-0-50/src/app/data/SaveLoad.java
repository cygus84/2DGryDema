package app.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import app.entity.Entity;
import app.object.OBJ_Axe;
import app.object.OBJ_Boots;
import app.object.OBJ_Chest;
import app.object.OBJ_Door;
import app.object.OBJ_Key;
import app.object.OBJ_Lantern;
import app.object.OBJ_Potion_Red;
import app.object.OBJ_Shield_Blue;
import app.object.OBJ_Shield_Wood;
import app.object.OBJ_Sword_Normal;
import app.object.OBJ_Tent;
import app.panele.GamePanel;

public class SaveLoad {

	public GamePanel gp;

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(String itemName) {
		Entity obj = null;
		
		switch(itemName) {
		case "Woodcuters' Axe": obj = new OBJ_Axe(gp); break;
		case "Boots": obj = new OBJ_Boots(gp); break;
		case "Key": obj = new OBJ_Key(gp); break;
		case "Lantern": obj = new OBJ_Lantern(gp); break;
		case "Red Potion": obj = new OBJ_Potion_Red(gp); break;
		case "Blue Shield": obj = new OBJ_Shield_Blue(gp); break;
		case "Wood Shield": obj = new OBJ_Shield_Wood(gp); break;
		case "Normal Sword": obj = new OBJ_Sword_Normal(gp); break;
		case "Tent": obj = new OBJ_Tent(gp); break;
		case "Door": obj = new OBJ_Door(gp); break;
		case "Chest": obj = new OBJ_Chest(gp); break;
		}
		return obj;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			DataStorage ds = new DataStorage();
			
			ds.lelve = gp.player.level;
			ds.maxLife = gp.player.maxLife;
			ds.life = gp.player.life;
			ds.maxMana = gp.player.maxMana;
			ds.mana = gp.player.mana;
			ds.strenght = gp.player.strenght;
			ds.dexterity = gp.player.dexterity;
			ds.exp = gp.player.exp;
			ds.nextLevelExp = gp.player.nextLevelExp;
			ds.coin = gp.player.coin;
			
			// Player invetory
			for(int i = 0; i < gp.player.invetory.size(); i++) {
				ds.itemNames.add(gp.player.invetory.get(i).name);
				ds.itemAmounts.add(gp.player.invetory.get(i).amount);
			}
			
			// Player equipment
			ds.currentWeaponsSlot = gp.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = gp.player.getCurrentSheildSlot();
			
			//OBJECT ON MAP
			ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
			ds.mapObjectLootNames = new String[gp.maxMap][gp.obj[1].length];
			ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
			
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum ++) {
				
				for(int i = 0; i < gp.obj[1].length; i ++) {
					if(gp.obj[mapNum][i] == null) {
						ds.mapObjectNames[mapNum][i] = "NA";
					} else {
						ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						if(gp.obj[mapNum][i].loot != null) {
							ds.mapObjectLootNames[mapNum][i] = gp.obj[mapNum][i].loot.name;
						}
						ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}	
				}				
			}
			
			
			
			// Write the DataStorage objects
			oos.writeObject(ds);
		}
		catch (Exception e) {
			System.out.println("Save Eceprions");
		}
	}
	
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			// Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			gp.player.level = ds.lelve;
			gp.player.maxLife = ds.maxLife;
			gp.player.life = ds.life;
			gp.player.maxMana = ds.maxMana;
			gp.player.mana = ds.mana;
			gp.player.strenght = ds.strenght;
			gp.player.dexterity = ds.dexterity;
			gp.player.exp = ds.exp;
			gp.player.nextLevelExp = ds.nextLevelExp;
			gp.player.coin = ds.coin;
			
			// Player inventory 
			gp.player.invetory.clear();
			for(int i = 0; i < ds.itemNames.size(); i++) {
				gp.player.invetory.add(getObject(ds.itemNames.get(i)));
				gp.player.invetory.get(i).amount = ds.itemAmounts.get(i);
			}
			
			// Player equipment
			gp.player.currentWeapon = gp.player.invetory.get(ds.currentWeaponsSlot);
			gp.player.currentShield = gp.player.invetory.get(ds.currentShieldSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getAttackImage();
			
			//OBJECT ON MAP 
			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
				
				for(int i = 0; i < gp.obj[1].length; i++) {
					
					if(ds.mapObjectNames[mapNum][i].equals("NA")) {
						gp.obj[mapNum][i] = null;
					} else {
						gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];
						if(ds.mapObjectLootNames[mapNum][i] != null) {
							gp.obj[mapNum][i].loot = getObject(ds.mapObjectLootNames[mapNum][i]);
						}
						gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened = true) {
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
						}
					}
				}
			}
			
		}
		catch(Exception e) {
			System.out.println("Load Exception!");
		}
	}
}
