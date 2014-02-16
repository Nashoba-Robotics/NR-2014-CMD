
package edu.nr.main.oi;

import edu.nr.main.subsystems.BottomRollers.RollCommand;
import edu.nr.main.subsystems.BottomRollers.StopRollCommand;
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
    private static JoystickButton startButton, bButton,
            leftBumperButton, rightBumperButton, aButton,
            xButton, rightStickButton, leftStickButton, yButton,
            backButton;
    
    public static void init()
    {
        stick1 = new Gamepad(1);
        startButton = new JoystickButton(stick1, Gamepad.kStartButton);
        bButton = new JoystickButton(stick1, Gamepad.kBButton);
        leftBumperButton = new JoystickButton(stick1, Gamepad.kLeftBumper);
        rightBumperButton = new JoystickButton(stick1, Gamepad.kRightBumper);
        aButton = new JoystickButton(stick1, Gamepad.kAButton);
        xButton = new JoystickButton(stick1, Gamepad.kXButton);
        rightStickButton = new JoystickButton(stick1, Gamepad.kRightStickButton);
        leftStickButton = new JoystickButton(stick1, Gamepad.kLeftStickButton);
        yButton = new JoystickButton(stick1, Gamepad.kYButton);
        backButton = new JoystickButton(stick1, Gamepad.kBackButton);
        
        startButton.whenPressed(new DriveJoystickCommand());
        
        //rightBumperButton.whileHeld(new ShooterRotationCommand(0.4));
        
        //leftBumperButton.whileHeld(new ShooterRotationCommand(-0.4));
        
        aButton.whenPressed(new RollCommand());
        
        xButton.whenPressed(new PunchCommand());
        
        backButton.whenPressed(new DriveIdleCommand());
        bButton.whenPressed(new StopRollCommand());
        
        yButton.whenPressed(new ResetDogEarCommand());
        
        rightBumperButton.whileHeld(new ShooterRotationCommand(0.5, 1));
        leftBumperButton.whileHeld(new ShooterRotationCommand(-0.5, 0));
    }
    
    public static double getJoy1Z()
    {
        return ((Gamepad)stick1).getX("right");
    }
    
    public static double getJoy1Y()
    {
        return -((Gamepad)stick1).getY("left");
    }
}

