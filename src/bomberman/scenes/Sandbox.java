/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.scenes;

import static bomberman.constants.GlobalConstants.CANVAS_HEIGHT;
import static bomberman.constants.GlobalConstants.CANVAS_WIDTH;
import static bomberman.constants.GlobalConstants.CELL_SIZE;
import static bomberman.constants.GlobalConstants.SCENE_HEIGHT;
import static bomberman.constants.GlobalConstants.SCENE_WIDTH;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import bomberman.GameLoop;
import bomberman.Renderer;
import bomberman.entity.Entity;
import bomberman.entity.player.Player;
import bomberman.entity.staticobjects.BlackBomb;
import bomberman.entity.staticobjects.Wall;
import bomberman.gamecontroller.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Ashish
 */
public class Sandbox {

    static Scene s;
    static Group root;
    static Canvas c;
    static GraphicsContext gc;
    private static boolean sceneStarted;
    static Player sandboxPlayer;
    static{
        sceneStarted=false;
    }

	private static Vector<Entity> entities = new Vector<Entity>();

	public static Vector<Entity> getEntities(){
		return entities;
	}

	public static boolean addEntityToGame(Entity e){
		if(!entities.contains(e)){
			entities.add(e);
			return true;
		} else {
			return false;
		}
	}

    private static void init() {
        root = new Group();
        s = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        c = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.setFill(Color.BLUE);
        Renderer.init();
        GameLoop.start(gc);

        //Initialize Objects
        Player p = new Player();
        setPlayer(p);
        
        //load map
        try
        {
            loadMap(new File("Resources/maps/sandbox_map.txt"));
        } catch (IOException e)
        {
            System.err.println("Unable to load map file.");
            System.exit(1);
        }

        //should be called at last it based on player
        EventHandler.attachEventHandlers(s);

    }


    //Eventually this should take some kind of map input, maybe a text file or something
    public static void loadMap(File file) throws IOException
    {
    	Vector<Wall> walls = new Vector<Wall>();

        try(BufferedReader inputStream = new BufferedReader(new FileReader(file)))
        {
            String line;
            int y = 0;
            while((line = inputStream.readLine()) != null)
            {
                for(int x = 0; x < line.length(); x++)
                {
                    //TODO: Remove this magic W
                    if(line.charAt(x) == 'W')
                    {
                        walls.add(new Wall(x * CELL_SIZE, y * CELL_SIZE));
                    }
                }
                y++;
            }
        }

    	for(Wall wall : walls) {
    		addEntityToGame(wall);
    	}
    }

    public static void setupScene(){
        if(!sceneStarted){
            init();
            sceneStarted=true;
        }
    }
    public static Scene getScene() {
        return s;
    }

    public static GraphicsContext getGraphicsContext() {
        return gc;
    }

    public static Canvas getCanvas() {
        return c;
    }

    public static void setPlayer(Player p){
        sandboxPlayer = p;
        addEntityToGame(p);
    }
    public static Player getPlayer(){
        return sandboxPlayer;
    }
}
