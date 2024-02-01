package app.panele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import app.Start;
import app.ai.PathFinder;
import app.entity.Entity;
import app.entity.Player;
import app.environment.EnvironmentManager;
import app.monster.MON_GreenSlime;
import app.narzedzia.AssetSetter;
import app.narzedzia.CollisionChecker;
import app.narzedzia.Config;
import app.narzedzia.EventHandler;
import app.narzedzia.UI;
import app.sound.Sound;
import app.sterowanie.KeyHandler;
import app.tile.TileManager;
import tiles_interactive.Interactivetile;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	public final int orginalTileSize = 16; // 16x16 tile
	public final int scale = 3;

	public final int tileSize = orginalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 960 pixel
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixel
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxMap = 10;
	public int currentMap = 0;
	
	
	// FOR FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	public BufferedImage tempScreen;
	public Graphics2D g2;
	public boolean fullScrenOn = false;
	
	// FPS
	private int FPS = 60;
	
	// SYSTEM
	// menager plytek 
	public TileManager tileM =  new TileManager(this);
	// wlasny sluchacz sterowania
	public KeyHandler keyH = new KeyHandler(this);
	//doanie muzyki Sound
	public Sound music = new Sound();
	public Sound se = new Sound();
	//dodanie kolizji
	public CollisionChecker colChecker = new CollisionChecker(this);
	//dodanie ustawienia obiktow
	public AssetSetter aSetter = new AssetSetter(this);
	//doanie Ui klasy
	public UI ui = new UI(this);
	// dodanie EvenHanlder
	public EventHandler eHandler = new EventHandler(this);
	// dodanie load and save
	public Config config = new Config(this);
	// daodanie path finder 
	public PathFinder pFinder = new PathFinder(this);
	public EnvironmentManager eManager = new EnvironmentManager(this);
	// dodanie  watku
	public Thread gameThread;
	
	// ENTITY  AND OBJECT
	
	//dodanie gracza
	public Player player =  new Player(this, keyH);
	//dodanie objektu
	public Entity obj[][] = new Entity[maxMap][20];
	//dodanie NPC
	public Entity npc[][] = new Entity[maxMap][10];
	// doaniae npc potwory
	public Entity monster[][] = new Entity[maxMap][20];
	// dodanie plytek do zniszczenia 
	public Interactivetile iTile[][] =  new Interactivetile[maxMap][50];
	// epizod 41
	public Entity projectile[][] = new Entity[maxMap][50]; /// nowe
	
	// dodanie listy obiktow
	//public ArrayList<Entity> projectileList = new ArrayList<>();
	// dodanie efetyu rozpadania grafiki
	public ArrayList<Entity> particleList = new ArrayList<>();
	
	public ArrayList<Entity> entytiyList = new ArrayList<>();
	
	// Game Status
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	
	// dodawanie do mapy 
	public void setupGame() {
		// dodanie obiktu
		aSetter.setObject();
		// dodoanie NPC
		aSetter.setNPC();
		// dodanie potworow
		aSetter.setMonster();
		// danie drzew do wyciecia
		aSetter.setInetractiveTile();
		eManager.setup();
		
		//wlaczenie muzyki o indeksie 0 
		//playMusic(0);
		//stopMusic();
		// doadanie statusu gry
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		g2 = (Graphics2D)tempScreen.getGraphics();
		
		// wywolanie metody do pelnego ekranu	
		if(fullScrenOn == true) {
			setFullScreen();
		}
	}
	
	public void retry() {
		player.setDefaultPosition();
		player.restoreLifeAndMana();
		aSetter.setNPC();
		aSetter.setMonster();
	}
	
	public void reset() {
		
		player.setDefaultPosition();
		player.restoreLifeAndMana();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInetractiveTile();
	}

	public void setFullScreen() {
		// GET THE LOCAL DEVICE SCREEN
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // pobranie wielkosci monitora
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Start.windows);
		
		// GET THE FULL SCREEN HEAIGHT AND WIDHT
		screenWidth2 = Start.windows.getWidth();
		screenHeight2 = Start.windows.getHeight();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		
		double drawInterval = 1000000000/ FPS; //0,1666
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime ) / drawInterval;
			timer += (currentTime - lastTime );
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				//repaint();
				drawToTempScreen();
				drawToScreen();
				delta--;
				drawCount++;
			}
			if(timer > 1000000000) {
				//System.out.println("FPS " + drawCount); // ja wylaczam widocznosc
				drawCount = 0;
				timer = 0;
			}
		}
		
	}
	
	public void update() {
		//dodanie statusu
		if(gameState == playState) {
			// PLAYER
			player.update();
			// NPC
			for(int i= 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			// dodanie upate dla potworow
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			
			// dodanie kuli ogniste
			for(int i = 0; i < projectile[1].length; i++) {
				if(projectile[currentMap][i] != null) {
					if(projectile[currentMap][i].alive == true) {
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive == false) {
						projectile[currentMap][i] = null;
					}
				}
			}	
		
			// dodanie particle listy
			for (int i = 0; i < particleList.size(); i++) {
				if (particleList.get(i) != null) {
					if (particleList.get(i).alive == true) {
						particleList.get(i).update();
					}
					if (particleList.get(i).alive == false) {
						particleList.remove(i);
					}
				}
			}

			// dodanie plytek kotore mozna zniszczyc
			for (int i = 0; i < iTile[1].length; i++) {
				if (iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}
			eManager.update();

		}
		if (gameState == pauseState) {
			// nothing at moment
		}

	}
	
	public void drawToTempScreen() {
		
		// Debug
				long drawStart = 0;
				if(keyH.checkDrawTime == true) {
					drawStart = System.nanoTime();
				}
				// Title screen
				if(gameState == titleState) {
					ui.draw(g2);
				// others	
				} else {
					
					// plytki tile TILE
					tileM.draw(g2);
					// Rysowanie plytek do zniszczenia
					// interactive tiles
					for(int i = 0;i < iTile[1].length; i++) {
						if(iTile[currentMap][i] != null) {
							iTile[currentMap][i].draw(g2);
						}
					}
					
					
					// ADD ENTITIS TO THE LIST
					entytiyList.add(player);
					
					for(int i = 0; i < npc[1].length; i++) {
						if(npc[currentMap][i] != null)
						entytiyList.add(npc[currentMap][i]);
					}
					
					for(int i = 0; i < obj[1].length; i++) {
						if(obj[currentMap][i] != null) {
							entytiyList.add(obj[currentMap][i]);
						}
					}
					
					// dodanie do rysowania listy potworow
					for(int i = 0; i < monster[1].length; i++) {
						if(monster[currentMap][i] != null) {
							entytiyList.add(monster[currentMap][i]);
						}
					}
					// dodanie rysowania kulli 
					for(int i = 0; i < projectile[1].length; i++) {
						if(projectile[currentMap][i] != null) {
							entytiyList.add(projectile[currentMap][i]);
						}
					}
					
					// dodanie rysowania particle
					for (int i = 0; i < particleList.size(); i++) {
						if (particleList.get(i) != null) {
							entytiyList.add(particleList.get(i));
						}
					}
					
					// SORT
					Collections.sort(entytiyList, new Comparator<Entity>() {

						@Override
						public int compare(Entity e1, Entity e2) {
							
							int result = Integer.compare(e1.worldY, e2.worldY); 
							return result;
						}
					});
					
					// DRAW ENTITIS
					for(int i = 0; i < entytiyList.size(); i++) {
						entytiyList.get(i).draw(g2);
					}
					
					// EMPTY ENTITY LIST
					entytiyList.clear();
					
					// ENVIROMENT
					eManager.draw(g2);
					
					// doadnie UI
					ui.draw(g2);
				}
			
			
				
				// DEBUG
				if(keyH.checkDrawTime == true) {
					long drawEnd = System.nanoTime();
					long passed = drawEnd - drawStart;
					g2.setColor(Color.WHITE);
					g2.drawString("Draw time:" + passed, 10, 400);
					System.out.println("Draw time:" + passed);
				}
	}
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();	
	}
	
	public void stopMusic() {
		music.stop();
	}

	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
