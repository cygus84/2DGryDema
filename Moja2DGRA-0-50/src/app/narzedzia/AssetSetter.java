package app.narzedzia;

import app.entity.NPC_Merchant;
import app.entity.NPC_OldMan;
import app.monster.MON_GreenSlime;
import app.monster.MON_Orck;
import app.object.OBJ_Axe;
import app.object.OBJ_Chest;
import app.object.OBJ_Coin_Bronze;
import app.object.OBJ_Door;
import app.object.OBJ_Heart;
import app.object.OBJ_Key;
import app.object.OBJ_Lantern;
import app.object.OBJ_ManaCrystal;
import app.object.OBJ_Potion_Red;
import app.object.OBJ_Shield_Blue;
import app.object.OBJ_Tent;
import app.panele.GamePanel;
import tiles_interactive.IT_DryTree;

public class AssetSetter {
// klasa do ustawiania obikotw na mapie
	
	public GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		// dodawanie objetkow
		
		int mapNum = 0;
		int i = 0;
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 21;
//		i++;
//		
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 32;
		gp.obj[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		// e45 dodanie lampy na mape
		gp.obj[mapNum][i] = new OBJ_Lantern(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 18;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		// e46 dodanie lampy na mape
		gp.obj[mapNum][i] = new OBJ_Tent(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 19;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
		gp.obj[mapNum][i].worldY = gp.tileSize * 12;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 20;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 17;
		gp.obj[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 27;
		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_Heart(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 29;
//		i++;
//		
//		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 31;
//		i++;
//		
		
	}
	
	public void setNPC() {
		// dodawanie botow
		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][0] = new NPC_OldMan(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 21;
		gp.npc[mapNum][0].worldY = gp.tileSize * 21;
		
		mapNum = 1;
		i = 0;
		gp.npc[mapNum][0] = new NPC_Merchant(gp);
		gp.npc[mapNum][0].worldX = gp.tileSize * 12;
		gp.npc[mapNum][0].worldY = gp.tileSize * 07;
	}
	
	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i]= new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 21;
		gp.monster[mapNum][i].worldY = gp.tileSize * 38;
		i++;
		
		gp.monster[mapNum][i]= new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 23;
		gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		
		
		gp.monster[mapNum][i]= new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 24;
		gp.monster[mapNum][i].worldY = gp.tileSize * 37;
		i++;
		
		gp.monster[mapNum][i]= new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 34;
		gp.monster[mapNum][i].worldY = gp.tileSize * 42;
		i++;
		
		gp.monster[mapNum][i]= new MON_Orck(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 12;
		gp.monster[mapNum][i].worldY = gp.tileSize * 33;
		i++;
			
	}
	
	public void setInetractiveTile() {
		int mapNum = 0;
		int i = 0;
		//01 drzewa z mozliwoscia sciecia
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);
//		gp.iTile[i].worldX = gp.tileSize * 27;  // zmina mteody i parametry przeniesione wyzje !!
//		gp.iTile[i].worldY = gp.tileSize * 12;
		i++;
		//02
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12);
		i++;
		// 03
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12);
		i++;
		//04
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12);
		i++;
		//05
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12);
		i++;
		//06
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12);
		i++;
		// 07
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12);
		i++;
		
		
		
		//dodatkowe drzewa
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 20);
		i++;
		// 03
		//gp.iTile[i] = new IT_DryTree(gp, 30, 21);
		//i++;
		//04
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 22);
		i++;
		//05
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 20);
		i++;
		//06
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 21);
		i++;
		// 07
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 20, 22);
		i++;
		
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 22, 24);
		i++;
		//06
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 23, 24);
		i++;
		// 07
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 24, 24);
		i++;
		
	}
}
