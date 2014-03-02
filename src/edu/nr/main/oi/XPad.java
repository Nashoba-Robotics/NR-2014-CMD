package edu.nr.main.oi;

public class XPad extends Controller {
    public XPad(int port) {
        super(port);
    }
    
    public static class ControllerAxisType {
        private int m_val;
        private static final int leftStickXAxis = 1,
        leftStickYAxis = 2,
        triggerAxis = 3,
        rightStickXAxis = 4,
        rightStickYAxis = 5,
        dPadXAxis = 6,
        dPadYAxis = 7;
        
        private ControllerAxisType(int val) {
            m_val = val;
        }
        
        public int getValue() {
            return m_val;
        }
        
        public static final ControllerAxisType kLX = new ControllerAxisType(leftStickXAxis);
        public static final ControllerAxisType kLY = new ControllerAxisType(leftStickYAxis);
        public static final ControllerAxisType kTrig = new ControllerAxisType(triggerAxis);
        public static final ControllerAxisType kRX = new ControllerAxisType(rightStickXAxis);
        public static final ControllerAxisType kRY = new ControllerAxisType(rightStickYAxis);
        public static final ControllerAxisType kDPadX = new ControllerAxisType(dPadXAxis);
        public static final ControllerAxisType kDPadY = new ControllerAxisType(dPadYAxis);
    }
    
    public static class ControllerButtonType {
        private int m_val;
        private static final int aButton = 1,
        bButton = 2,
        xButton = 3,
        yButton = 4,
        leftBumperButton = 5,
        rightBumperButton = 6,
        backButton = 7,
        startButton = 8,
        leftStickButton = 9,
        rightStickButton = 10;

        private ControllerButtonType(int val) {
            m_val = val;
        }

        public int getValue() {
            return m_val;
        }

        public static final ControllerButtonType kA = new ControllerButtonType(aButton);
        public static final ControllerButtonType kB = new ControllerButtonType(bButton);
        public static final ControllerButtonType kX = new ControllerButtonType(xButton);
        public static final ControllerButtonType kY = new ControllerButtonType(yButton);

        public static final ControllerButtonType kLBump = new ControllerButtonType(leftBumperButton);
        public static final ControllerButtonType kRBump = new ControllerButtonType(rightBumperButton);

        public static final ControllerButtonType kBack = new ControllerButtonType(backButton);
        public static final ControllerButtonType kStart = new ControllerButtonType(startButton);

        public static final ControllerButtonType kLStick = new ControllerButtonType(leftStickButton);
        public static final ControllerButtonType kRStick = new ControllerButtonType(rightStickButton);
    }
    
    public boolean getControllerRawButton(int button) {
        switch(button) {
            case ControllerButtonType.aButton: 
                return getRawButton(ControllerButtonType.aButton);
            case ControllerButtonType.bButton: 
                return getRawButton(ControllerButtonType.bButton);
            case ControllerButtonType.leftBumperButton:
                return getRawButton(ControllerButtonType.leftBumperButton);
            case ControllerButtonType.leftStickButton:
                return getRawButton(ControllerButtonType.leftStickButton);
            case ControllerButtonType.rightBumperButton:
                return getRawButton(ControllerButtonType.rightBumperButton);
            case ControllerButtonType.rightStickButton:
                return getRawButton(ControllerButtonType.rightStickButton);
            case ControllerButtonType.backButton:
                return getRawButton(ControllerButtonType.backButton);
            case ControllerButtonType.startButton:
                return getRawButton(ControllerButtonType.startButton);
            case ControllerButtonType.xButton:
                return getRawButton(ControllerButtonType.xButton);
            case ControllerButtonType.yButton:
                return getRawButton(ControllerButtonType.yButton);
            default: {
                System.err.println("Not a button!");
                return false;
            }
        }
    }
    
    public double getControllerRawAxis(int axis) {
        switch(axis) {
            case ControllerAxisType.dPadXAxis:
                return getRawAxis(ControllerAxisType.dPadXAxis);
            case ControllerAxisType.dPadYAxis:
                return getRawAxis(ControllerAxisType.dPadYAxis);
            case ControllerAxisType.triggerAxis:
                return getRawAxis(ControllerAxisType.triggerAxis);
            case ControllerAxisType.leftStickXAxis:
                return getRawAxis(ControllerAxisType.leftStickXAxis);
            case ControllerAxisType.leftStickYAxis:
                return getRawAxis(ControllerAxisType.leftStickYAxis);
            case ControllerAxisType.rightStickXAxis:
                return getRawAxis(ControllerAxisType.rightStickXAxis);
            case ControllerAxisType.rightStickYAxis:
                return getRawAxis(ControllerAxisType.rightStickYAxis);
            default: {
                System.err.println("Not an axis!");
                return 0.0;
            }
        }
    }
    
    public boolean getControllerButton(ControllerButtonType button) {
        if(button.equals(ControllerButtonType.kA)) {
            return getRawButton(ControllerButtonType.kA.getValue());
        }
        else if(button.equals(ControllerButtonType.kB)) {
            return getRawButton(ControllerButtonType.kB.getValue());
        }
        else if(button.equals(ControllerButtonType.kLBump)) {
            return getRawButton(ControllerButtonType.kLBump.getValue());
        }
        else if(button.equals(ControllerButtonType.kLStick)) {
            return getRawButton(ControllerButtonType.kLStick.getValue());
        }
        else if(button.equals(ControllerButtonType.kRBump)) {
            return getRawButton(ControllerButtonType.kRBump.getValue());
        }
        else if(button.equals(ControllerButtonType.kRStick)) {
            return getRawButton(ControllerButtonType.kRStick.getValue());
        }
        else if(button.equals(ControllerButtonType.kBack)) {
            return getRawButton(ControllerButtonType.kBack.getValue());
        }
        else if(button.equals(ControllerButtonType.kStart)) {
            return getRawButton(ControllerButtonType.kX.getValue());
        }
        else if(button.equals(ControllerButtonType.kY)) {
            return getRawButton(ControllerButtonType.kY.getValue());
        }
        else {
            System.err.println("Not a button!");
            return false;
        }
    }
    
    public double getControllerAxis(ControllerAxisType axis) {
        if(axis.equals(ControllerAxisType.kDPadX)) {
            return getRawAxis(ControllerAxisType.kDPadX.getValue());
        }
        else if(axis.equals(ControllerAxisType.kDPadY)) {
            return getRawAxis(ControllerAxisType.kDPadY.getValue());
        }
        else if(axis.equals(ControllerAxisType.kTrig)) {
            return getRawAxis(ControllerAxisType.kTrig.getValue());
        }
        else if(axis.equals(ControllerAxisType.kLX)) {
            return getRawAxis(ControllerAxisType.kLX.getValue());
        }
        else if(axis.equals(ControllerAxisType.kLY)) {
            return getRawAxis(ControllerAxisType.kLY.getValue());
        }
        else if(axis.equals(ControllerAxisType.kRX)) {
            return getRawAxis(ControllerAxisType.kRX.getValue());
        }
        else if(axis.equals(ControllerAxisType.kRY)) {
            return getRawAxis(ControllerAxisType.kRY.getValue());
        }
        else {
            System.out.println("Not an axis!");
            return 0.0;
        }     
    }
}