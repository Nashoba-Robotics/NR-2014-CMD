package edu.nr.main.oi;

import edu.wpi.first.wpilibj.Joystick;

public abstract class Controller extends Joystick
{

    public Controller(int port) {
        super(port);
    }
    
    public static abstract class ButtonType {
        public static int aButton;
        public static int bButton;
        public static int xButton;
        public static int yButton;
        public static int rightBumperButton;
        public static int leftBumperButton;
        public static int selectButton;
        public static int startButton;
        public static int leftStickButton;
        public static int rightStickButton;
    }

    public static abstract class AxisType {
        public static int leftXAxis;
        public static int rightXAxis;
        public static int leftYAxis;
        public static int rightYAxis;
        public static int extraAxis1;
        public static int extraAxis2;
        public static int extraAxis3;
    }

    public abstract boolean getButton(int button);
    public abstract double getRawAxis(int axis);
}
