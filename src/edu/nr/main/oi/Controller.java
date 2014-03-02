package edu.nr.main.oi;

import edu.wpi.first.wpilibj.Joystick;

public abstract class Controller extends Joystick
{
    public Controller(int port) {
        super(port);
    }
    public static abstract class ControllerButtonType {}

    public static abstract class ControllerAxisType {}

    public abstract boolean getControllerRawButton(int button);
    public abstract boolean getControllerButton(ControllerButtonType button);
    public abstract double getControllerRawAxis(int axis);
    public abstract double getControllerAxis(ControllerAxisType axis);
}
