package edu.nr.main.oi;

/**
 * XPad
 * 
 * An extension of the Joystick class that works with the Microsoft X-Box
 * controller
 */
public class Gamepad extends Controller 
{
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