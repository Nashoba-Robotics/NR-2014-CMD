package edu.nr.main.oi;

import edu.wpi.first.wpilibj.Joystick;

public class Controller extends Joystick
{
    public Controller(int port) {
        super(port);
    }
    public static class ControllerButtonType {}

    public static class ControllerAxisType {}

    public boolean getControllerRawButton(int button) {
        return false;
    }
    public boolean getControllerButton(ControllerButtonType button) {
        return false;
    }
    public double getControllerRawAxis(int axis) {
        return 0.0;
    }
    public double getControllerAxis(ControllerAxisType axis) {
        return 0.0;
    }
}
