/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.scenes;

import static bomberman.constants.GlobalConstants.CANVAS_HEIGHT;
import static bomberman.constants.GlobalConstants.CANVAS_WIDTH;
import static bomberman.constants.GlobalConstants.SCENE_HEIGHT;
import static bomberman.constants.GlobalConstants.SCENE_WIDTH;

import bomberman.GameLoop;
import bomberman.Renderer;
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

    static{
        sceneStarted=false;
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
        EventHandler.attachEventHandlers(s);
        Renderer.init();
        GameLoop.start(gc);
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
}
