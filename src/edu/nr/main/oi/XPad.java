/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.oi;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class XPad extends Controller
{

    public XPad(int port) 
    {
        super(port);
        aButton = 1;
        bButton = 2;
        xButton = 3;
        yButton = 4;
        rightBumperButton = 6;
        leftBumperButton = 5;
        selectButton = 7;
        startButton = 8;
        leftStickButton = 9;
        rightStickButton = 10;

        leftStickXAxis = 1;
        leftStickYAxis = 2;
        rightStickXAxis = 3;
        rightStickYAxis = 4;
        extraAxis1 = 5;
        extraAxis2 = 6;
        extraAxis3 = 7;
    }
    
}
