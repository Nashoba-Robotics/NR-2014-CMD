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
 * @author colin
 */
public class ShooterRotator extends Subsystem implements Printable
{
    //public static final double BOTTOM_POSITION = 0.078, FORTY_FIVE = 0.162, NINETY = 0.267, STARTING_POSITION = 0.257, FORTY=.1585, AUTONOMOUS_ANGLE = 0.146;
    public static final double STARTING_POSITION = 85.4, AUTONOMOUS_ANGLE = 42.5;
            ;
    public static final double REGULAR_SPEED = 0.9;
    public static final double LOWER_LIMIT = -16.7, UPPER_LIMIT = STARTING_POSITION;
    private CANJaguar rotationJag;
    ShooterPotentiometer pot;
    public ShooterRotator()
    {
        pot = new ShooterPotentiometer(2);
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
    
    public double getRotation()
    {
        return pot.getAngle();
    }
    
    public void rotate(double speed)
    {
        try 
        {
            rotationJag.setX(-speed);
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

class ShooterPotentiometer extends AnalogChannel
{
    public ShooterPotentiometer(int channel)
    {
        super(channel);
    }
    private final double FACTOR = .2888192405, OFFSET = -47.9963;
    //private final double FACTOR = 3.4622266289, OFFSET = 166.1860728346;
    public double getAngle()
    {
        return (this.getValue() * FACTOR + OFFSET);
    }
    
    public double getRawAngle()
    {
        return this.getValue();
    }
}
