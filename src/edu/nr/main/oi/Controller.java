/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;

/**
 *
 * @author colin
 */
public abstract class Controller extends Joystick
{

    public Controller(int port) {
        super(port);
    }
    
    public int aButton;
    public int bButton;
    public int xButton;
    public int yButton;
    public int rightBumperButton;
    public int leftBumperButton;
    public int selectButton;
    public int startButton;
    public int leftStickButton;
    public int rightStickButton;
    
    public int leftStickXAxis;
    public int leftStickYAxis;
    public int rightStickXAxis;
    public int rightStickYAxis;
    public int extraAxis1;
    public int extraAxis2;
    public int extraAxis3;
    
    
}
