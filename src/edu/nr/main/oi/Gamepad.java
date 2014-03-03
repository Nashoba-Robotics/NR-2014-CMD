package edu.nr.main.oi;

import edu.wpi.first.wpilibj.Joystick;

/**
 * XPad
 * 
 * An extension of the Joystick class that works with the Microsoft X-Box
 * controller
 */
public class Gamepad extends Controller {
    
    /*private final int kLeftStick = 1;
    private final int kRightStick = 2;
    
    private final int kLeftXAxis = 1;
    private final int kLeftYAxis = 2;
    private final int kRightXAxis = 3;
    private final int kRightYAxis = 4;
    private final int kDPadXAxis = 5;
    private final int kDPadYAxis = 6;
           
    private final int kXButton = 1;
    private final int kAButton = 2;
    private final int kBButton = 3;
    private final int kYButton = 4;
    private final int kLeftBumper = 5;
    private final int kRightBumper = 6;
    private final int kLeftTrigger = 7;
    private final int kRightTrigger = 8;
    private final int kBackButton = 9;
    private final int kStartButton = 10;
    private final int kLeftStickButton = 11;
    private final int kRightStickButton = 12;*/

    public Gamepad(int port)
    {
        super(port);
        aButton = 2;
        bButton = 3;
        xButton = 1;
        yButton = 4;
        rightBumperButton = 6;
        leftBumperButton = 5;
        selectButton = 9;
        startButton = 10;
        leftStickButton = 11;
        rightStickButton = 12;

        leftStickXAxis = 1;
        leftStickYAxis = 2;
        rightStickXAxis = 3;
        rightStickYAxis = 4;
        extraAxis1 = 5;
        extraAxis2 = 6;
        extraAxis3 = 7;
    }
}