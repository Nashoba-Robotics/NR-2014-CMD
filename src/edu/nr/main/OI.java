
package edu.nr.main;

import edu.nr.main.commands.DriveIdleCommand;
import edu.nr.main.commands.DriveJoystickCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    private static Joystick stick1;
    private static JoystickButton button1, button2;
    public static void init()
    {
        stick1 = new Joystick(1);
        button1 = new JoystickButton(stick1, 7);
        button1.whenPressed(new DriveJoystickCommand());
        
        button2 = new JoystickButton(stick1, 1);
        button2.whenPressed(new DriveIdleCommand());
    }
    
    public static double getJoy1Z()
    {
        return stick1.getAxis(Joystick.AxisType.kZ);
    }
    
    public static double getJoy1Y()
    {
        return stick1.getAxis(Joystick.AxisType.kY);
    }
}

