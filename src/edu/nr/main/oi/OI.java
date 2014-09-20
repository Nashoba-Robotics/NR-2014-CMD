package edu.nr.main.oi;

import edu.nr.main.Autonomous.AutonomousCommand;
import edu.nr.main.CommandGroups.BallIntakeCommand;
import edu.nr.main.CommandGroups.StopBallIntakeCommand;
import edu.nr.main.Robot;

import edu.nr.main.subsystems.BottomRollers.*;
import edu.nr.main.subsystems.CancelAllCommand;
import edu.nr.main.subsystems.Drive.*;
import edu.nr.main.subsystems.Puncher.*;
import edu.nr.main.subsystems.ShooterRotator.*;
import edu.nr.main.subsystems.TopArm.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    private static Joystick stick1, stick2;
    
    public static void init()
    {
        stick1 = new Joystick(1);
        stick2 = new Joystick(2);
        
        
        new JoystickButton(stick1, 5).whenPressed(new ShiftCommand(true));
        
        new JoystickButton(stick1, 3).whenPressed(new ShiftCommand(false));
        
        new JoystickButton(stick2, 7).whenPressed(new StopBallIntakeCommand());
        new JoystickButton(stick2, 6).whenPressed(new BallIntakeCommand());

        new JoystickButton(stick2, 11).whileHeld(new ShooterRotationCommand(-ShooterRotator.REGULAR_SPEED));
        new JoystickButton(stick2, 10).whileHeld(new ShooterRotationCommand(ShooterRotator.REGULAR_SPEED));
        
        
        new JoystickButton(stick2, 2).whenPressed(new ShooterRotateTargetCommand(ShooterRotator.AUTONOMOUS_ANGLE));
        new JoystickButton(stick2, 1).whenPressed(new PunchGroupCommand());
        new JoystickButton(stick2, 9).whenPressed(new TopArmDownCommand());
        new JoystickButton(stick2, 8).whenPressed(new TopArmUpCommand());
        new JoystickButton(stick2, 11).whenPressed(new ResetDogEarCommand());
        new JoystickButton(stick2, 3).whenPressed(new AutonomousCommand());
    }
    
    public static double getJoy1Z()
    {
        SmartDashboard.putNumber("Z Axis", stick1.getAxis(Joystick.AxisType.kZ));
        
        return stick1.getAxis(Joystick.AxisType.kZ);
    }
    
    public static double getJoy1Y()
    {
        return stick1.getAxis(Joystick.AxisType.kY);
    }
}

