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
        double position[] = new double[3];
        double measures[] = new double[360];
        while (running) {
            try {
                String result;
                PipedInputStream pipeIn = new PipedInputStream();
                input = new BufferedReader(new InputStreamReader(pipeIn));
                PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

                robot.setOutput(output);



                robot.sendCommand("R1.GETPOS");
                result = input.readLine();
                position = parsePosition(result);

                robot.sendCommand("L1.SCAN");
                result = input.readLine();
                measures = parseMeasures(result);
                map.drawLaserScan(position, measures);

                moveForward((int) measures[0]);

                turnRight(90);
                while(running){
                    position = getPos();

                    measures = laserScan(position);
                    if(measures[0]>60){
                        moveForward(60);

                    }else{
                        moveForward((int) measures[0]);
                        turnRight(90);

                    }

                }



            } catch (IOException ioe) {
                System.err.println("execution stopped");
                running = false;
            }
        }

    }



    private double[] getPos() throws IOException {
        robot.sendCommand("R1.GETPOS");
        String result = input.readLine();
        return parsePosition(result);
    }

    private double[] laserScan(double position[]) throws IOException {
        robot.sendCommand("L1.SCAN");
        String result = input.readLine();
        double[] measures = parseMeasures(result);
        map.drawLaserScan(position, measures);
        return measures;
    }

    private void moveForward(int distance) throws IOException {
        robot.sendCommand("P1.MOVEFW " + Integer.toString(distance));
        String result = input.readLine();
    }

    private void moveBackward(int distance) throws IOException {
        robot.sendCommand("P1.MOVEBW " + Integer.toString(distance));
        String result = input.readLine();
    }

    private void moveLinear(int distance) throws IOException {
        if (distance < 0) {
            moveBackward(-distance);
        } else {
            moveForward(distance);
        }
    }

    private void turnRight(int degrees) throws IOException {
        robot.sendCommand("P1.ROTATERIGHT " + Integer.toString(degrees));
        String result = input.readLine();
    }

    private void turnLeft(int degrees) throws IOException {
        robot.sendCommand("P1.ROTATELEFT " + Integer.toString(degrees));
        String result = input.readLine();
    }

    private void turn(int degrees) throws IOException{
        //TODO: smaller/larger -360/360
        if(degrees>180){
            turnLeft(360-degrees);
        }else if(degrees<0){
            turnLeft(-degrees);
            //TODO: optimize turn
        }else{
            turnRight(degrees);
        }
    }

    private double[] parsePosition(String value) {
        int indexInit;
        int indexEnd;
        String parameter;
        double[] position = new double[3];

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

        return position;
    }

    private double[] parseMeasures(String value) {
        double[] measures = new double[360];
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
                System.out.println("direction = " + direction + " distance = " + distance);
            }
        }
        return measures;
    }

}
