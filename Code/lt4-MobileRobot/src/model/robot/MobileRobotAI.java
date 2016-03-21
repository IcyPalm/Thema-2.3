package model.robot;

import model.virtualmap.OccupancyMap;

import java.io.PipedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PipedOutputStream;
import java.io.IOException;

import java.util.StringTokenizer;

/**
 * Title : The Mobile Robot Explorer Simulation Environment v2.0 Copyright: GNU
 * General Public License as published by the Free Software Foundation Company :
 * Hanze University of Applied Sciences
 *
 * @author Dustin Meijer (2012)
 * @author Alexander Jeurissen (2012)
 * @author Davide Brugali (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

    private final OccupancyMap map;
    private final MobileRobot robot;

    private BufferedReader input;

    private boolean running;
    
    
    private double[] position;
    private double[] measures;
    private String result;

    private static final int STEP_SIZE = 90;
    private static final int ROBOT_WIDTH = 40;
    private static final int ROBOT_LENGTH = 50;
    
    private static final int LASER_RANGE = 100;
    
    private static final int NORTH = 360;
    private static final int EAST = 90;
    private static final int SOUTH = 180;
    private static final int WEST = 270;

    private static final int WALL_MARGIN = 2;
    
    public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
        this.map = map;
        this.robot = robot;

    }

    /**
     * In this method the gui.controller sends commands to the robot and its
     * devices. At the moment all the commands are hardcoded. The exercise is to
     * let the gui.controller make intelligent decisions based on what has been
     * discovered so far. This information is contained in the OccupancyMap.
     */
    public void run() {
        this.running = true;
        this.position = new double[3];
        this.measures = new double[360];
        this.result = "";
        
        boolean start = true;
        
        System.out.println("Robot is starting");
        
        while (running) {
            try {
                
                PipedInputStream pipeIn = new PipedInputStream();
                input = new BufferedReader(new InputStreamReader(pipeIn));
                PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

                robot.setOutput(output);
                
                while(start){
                    laserScan();
                  //TODO: Scan sonar
                    if(!wallForward()){
                        moveForward(1);
                    }else{
                        System.out.println("found a starting wall, turn left");
                        turnLeft();
                        start=false;
                    }
                }
                
                laserScan();
                //TODO: Scan sonar
                

                if(wallRight() && wallForward()){
                    turnLeft();
                }else{
                    moveForward(1);
                }
                
                
                
            } catch (IOException ioe) {
                System.err.println("execution stopped");
                running = false;
            }
        }

    }

   
    private boolean wallRight(){
        char[][] copyMap = map.getGrid();
        int positions[] = convertPosition();
        
        int direction = getDirectiontoRight(positions[2]);
        
        int[] wallCoordinates = getWallFromMap(positions[0], positions[1], direction);
      

        if (copyMap[wallCoordinates[0]][wallCoordinates[1]] == map.getObstacle()) {
            return true;
        }
        return false;
    }
  
    private boolean wallForward(){
        char[][] copyMap = map.getGrid();
        int positions[] = convertPosition();
        int offsetX = 1;
        int offsetY = 0;
        if(positions[2]==EAST||positions[2]==WEST){
            offsetY = 1;
            offsetX = 0;
        }
        for(int i=-2 ; i<=2;i++){
            int[] wallCoordinates = getWallFromMap(positions[0]+i*offsetY,positions[1]+i*offsetX,positions[2]);
            if (copyMap[wallCoordinates[0]][wallCoordinates[1]] == map.getObstacle()) {
                return true;
            }
        }
        return false;
    }
    
    private int[] getWallFromMap(int x, int y, int direction) {
        int[] wallCoordinates = new int[2];
        
        if(direction==0){
            direction=360;
        }
        switch (direction) {
        case WEST:
            y -= (1 + WALL_MARGIN);
            break;
        case NORTH:
            x += (1 + WALL_MARGIN);
            break;
        case EAST:
            y += (1 + WALL_MARGIN);
            break;
        case SOUTH:
            x -= (1 + WALL_MARGIN);
            break;
        }
        wallCoordinates[0] = x;
        wallCoordinates[1] = y;
        return wallCoordinates;
    }

    

    
    
    
    
    
    
    
    
    private void laserScan() throws IOException {
        robot.sendCommand("R1.GETPOS");
        this.result = input.readLine();
        parsePosition(result);
        
        robot.sendCommand("L1.SCAN");
        String result = input.readLine();
        parseMeasures(result);
        map.drawLaserScan(position, measures);  
    }
    
    
    
    
    
    
    


    private void moveForward(int distance) throws IOException {
        robot.sendCommand("P1.MOVEFW " + Integer.toString(distance*10));
        result = input.readLine();
    }

    private void moveBackward(int distance) throws IOException {
        robot.sendCommand("P1.MOVEBW " + Integer.toString(distance*10));
        String result = input.readLine();
    }

    private void turnRight() throws IOException {
        robot.sendCommand("P1.ROTATERIGHT 90");
        result = input.readLine();
    }

    private void turnLeft() throws IOException {
        robot.sendCommand("P1.ROTATELEFT 90");
        result = input.readLine();
    }
    
    
    
    
    
    
    
    private int getDirectiontoRight(int direction) {
        if (direction == 360) {
            direction = 90;
        } else {
            direction += 90;
        }
        
        return direction;
    }
    
    private int[] convertPosition() {
        int[] positions = new int[3];

        int x = ((int) Math.round(position[0]) / 10);
        int y = ((int) Math.round(position[1]) / 10);
        int direction = (int) Math.floor(position[2]);

        positions[0] = x;
        positions[1] = y;
        positions[2] = direction;

        return positions;
    }

    private void parsePosition(String value) {
        int indexInit;
        int indexEnd;
        String parameter;

        indexInit = value.indexOf("X=");
        parameter = value.substring(indexInit + 2);
        indexEnd = parameter.indexOf(' ');
        position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

        indexInit = value.indexOf("Y=");
        parameter = value.substring(indexInit + 2);
        indexEnd = parameter.indexOf(' ');
        position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

        indexInit = value.indexOf("DIR=");
        parameter = value.substring(indexInit + 4);
        position[2] = Double.parseDouble(parameter);

    }

    private void parseMeasures(String value) {
        for (int i = 0; i < 360; i++) {
            measures[i] = 100.0;
        }
        if (value.length() >= 5) {
            value = value.substring(5); // removes the "SCAN " keyword

            StringTokenizer tokenizer = new StringTokenizer(value, " ");

            double distance;
            int direction;
            while (tokenizer.hasMoreTokens()) {
                distance = Double.parseDouble(tokenizer.nextToken().substring(2));
                direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
                if (direction == 360) {
                    direction = 0;
                }
                measures[direction] = distance;
                // Printing out all the degrees and what it encountered.
                // System.out.println("direction = " + direction + " distance = " + distance);
            }
        }
    }

}
