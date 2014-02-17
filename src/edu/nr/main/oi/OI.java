
package edu.nr.main.oi;

import edu.nr.main.subsystems.BottomRollers.RollCommand;
import edu.nr.main.subsystems.BottomRollers.StopRollCommand;
import edu.nr.main.subsystems.Drive.DriveIdleCommand;
import edu.nr.main.subsystems.Drive.DriveJoystickCommand;
import edu.nr.main.subsystems.Puncher.PunchCommand;
import edu.nr.main.subsystems.Puncher.ResetDogEarCommand;
import edu.nr.main.subsystems.Puncher.TensionCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotationCommand;
import edu.nr.main.subsystems.TopArm.TopArmDownCommand;
import edu.nr.main.subsystems.TopArm.TopArmRunCommand;
import edu.nr.main.subsystems.TopArm.TopArmStopCommand;
import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    private static Controller stick1;
    private static JoystickButton startButton, bButton,
            leftBumperButton, rightBumperButton, aButton,
            xButton, rightStickButton, leftStickButton, yButton,
            backButton;
    
    public static void init()
    {
        stick1 = new XPad(1);
        startButton = new JoystickButton(stick1, stick1.startButton);
        bButton = new JoystickButton(stick1, stick1.bButton);
        leftBumperButton = new JoystickButton(stick1, stick1.leftBumperButton);
        rightBumperButton = new JoystickButton(stick1, stick1.rightBumperButton);
        aButton = new JoystickButton(stick1, stick1.aButton);
        xButton = new JoystickButton(stick1, stick1.xButton);
        rightStickButton = new JoystickButton(stick1, stick1.rightStickButton);
        leftStickButton = new JoystickButton(stick1, stick1.leftStickButton);
        yButton = new JoystickButton(stick1, stick1.yButton);
        backButton = new JoystickButton(stick1, stick1.selectButton);

        
        startButton.whenPressed(new DriveJoystickCommand());
        
        //rightBumperButton.whileHeld(new ShooterRotationCommand(0.4));
        
        //leftBumperButton.whileHeld(new ShooterRotationCommand(-0.4));
        
        aButton.whenPressed(new RollCommand());
        
        xButton.whenPressed(new PunchCommand());
        
        backButton.whenPressed(new DriveIdleCommand());
        bButton.whenPressed(new StopRollCommand());
        
        yButton.whenPressed(new ResetDogEarCommand());
        
        rightBumperButton.whenPressed(new TopArmRunCommand());
        leftBumperButton.whenPressed(new TopArmStopCommand());
        
        leftStickButton.whenPressed(new TopArmDownCommand());
        rightStickButton.whenPressed(new TopArmUpCommand());
        //rightBumperButton.whileHeld(new ShooterRotationCommand(0.3, 1));
        //leftBumperButton.whileHeld(new ShooterRotationCommand(-0.3, 0));
    }
    
    public static double getJoy1Z()
    {
        return stick1.getRawAxis(stick1.rightStickXAxis);
    }
    
    public static double getJoy1Y()
    {
        return -stick1.getRawAxis(stick1.leftStickYAxis);
    }
}

