
package edu.nr.main.oi;

import edu.nr.main.subsystems.BottomRollers.RollCommand;
import edu.nr.main.subsystems.Drive.DriveIdleCommand;
import edu.nr.main.subsystems.Drive.DriveJoystickCommand;
import edu.nr.main.subsystems.Puncher.PunchCommand;
import edu.nr.main.subsystems.Puncher.ResetDogEarCommand;
import edu.nr.main.subsystems.Puncher.TensionCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotationCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    private static Joystick stick1;
    private static JoystickButton button7, button1, button5, button6, button3, xButton, rightStickButton, leftStickButton, yButton;
    private static int joystickType = 2;
    
    public static void setJoystickType(int type)
    {
        joystickType = type;
    }
    
    public static void init()
    {
        if(joystickType == 1)
        {
            stick1 = new Joystick(1);
            button7 = new JoystickButton(stick1, 7);
            button1 = new JoystickButton(stick1, 1);
            button5 = new JoystickButton(stick1, 5);
            button6 = new JoystickButton(stick1, 6);
            button3 = new JoystickButton(stick1, 3);
        }
        else
        {
            stick1 = new Gamepad(1);
            button7 = new JoystickButton(stick1, Gamepad.kStartButton);
            button1 = new JoystickButton(stick1, Gamepad.kBButton);
            button5 = new JoystickButton(stick1, Gamepad.kLeftBumper);
            button6 = new JoystickButton(stick1, Gamepad.kRightBumper);
            button3 = new JoystickButton(stick1, Gamepad.kRightTrigger);
            xButton = new JoystickButton(stick1, Gamepad.kXButton);
            rightStickButton = new JoystickButton(stick1, Gamepad.kRightStickButton);
            leftStickButton = new JoystickButton(stick1, Gamepad.kLeftStickButton);
            yButton = new JoystickButton(stick1, Gamepad.kYButton);
        }
        
        button7.whenPressed(new DriveJoystickCommand());
        
        button6.whileHeld(new ShooterRotationCommand(0.4));
        
        button5.whileHeld(new ShooterRotationCommand(-0.4));
        
        button3.whileHeld(new TensionCommand(0.4f));
        
        xButton.whenPressed(new PunchCommand());
        
        button1.whenPressed(new DriveIdleCommand());
        rightStickButton.whenPressed(new RollCommand(0.8));
        leftStickButton.whenPressed(new RollCommand(0));
        
        yButton.whenPressed(new ResetDogEarCommand());
    }
    
    public static double getJoy1Z()
    {
        if(joystickType == 1)
        {
            return stick1.getAxis(Joystick.AxisType.kZ);
        }
        else
        {
            return ((Gamepad)stick1).getX("right");
        }
    }
    
    public static double getJoy1Y()
    {
        if(joystickType == 1)
            return stick1.getAxis(Joystick.AxisType.kY);
        else
            return ((Gamepad)stick1).getY("left");
    }
}

