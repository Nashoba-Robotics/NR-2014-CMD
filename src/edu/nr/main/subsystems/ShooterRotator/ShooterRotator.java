/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class ShooterRotator extends Subsystem implements Printable
{
    //public static final double BOTTOM_POSITION = 0.078, FORTY_FIVE = 0.162, NINETY = 0.267, STARTING_POSITION = 0.257, FORTY=.1585, AUTONOMOUS = 0.146;
    public static final double STARTING_POSITION = 418, AUTONOMOUS = 263;
    private CANJaguar rotationJag;
    RotaryPot pot;
    public ShooterRotator()
    {
        pot = RotaryPot.getInstance();
        SmartDashboard.putNumber("Shooter Rotate Distance", 0);
        try 
        {
            rotationJag = new CANJaguar(RobotMap.SHOOTER_ROTATION_JAG);
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
        } 
        catch (CANTimeoutException ex) 
        {
            Robot.canExceptions++;
        }
    }
    public void initCAN()
    {
        try {
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
        } catch (CANTimeoutException ex) {
            Robot.canExceptions++;
        }
    }
    
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ShooterRotatorIdle());
    }
    
    public double getAngle()
    {
        return pot.getAngle();
    }
    
    public double getRotation()
    {
        return pot.getRaw();
    }
    
    public void rotate(double speed)
    {
        try 
        {
            rotationJag.setX(speed);
        }
        catch (CANTimeoutException ex) {
            Robot.canExceptions++;
        }
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Shooter Rotator", this);
        SmartDashboard.putData("Shooter Rotator Idle", new ShooterRotatorIdle());
        SmartDashboard.putData("Rotate vertical", new ShooterRotateTargetCommand(.267));
        SmartDashboard.putData("Rotate bottom", new ShooterRotateTargetCommand(.078));
        SmartDashboard.putData("Rotate 45", new ShooterRotateTargetCommand(.162));
        SmartDashboard.putData("Rotate 40", new ShooterRotateTargetCommand(.1585));
        SmartDashboard.putData("Rotate Starting Position", new ShooterRotateTargetCommand(STARTING_POSITION));
    }
}
