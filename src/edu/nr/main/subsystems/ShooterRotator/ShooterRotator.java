/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.Robot;
import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterRotator extends Subsystem
{
    private static ShooterRotator singleton = null;
    public static ShooterRotator getInstance()
    {
        if(singleton == null)
            singleton = new ShooterRotator();
        
        return singleton;
    }
    
    public static final double STARTING_POSITION = 85.4, AUTONOMOUS_ANGLE = 42.5;
    public static final double REGULAR_SPEED = 0.9;
    public static final double LOWER_LIMIT = -16.7, UPPER_LIMIT = STARTING_POSITION;
    private CANJaguar rotationJag;
    ShooterPotentiometer pot;
    
    private ShooterRotator()
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
            reportCANException(ex);
        }
    }
    public void initCAN()
    {
        try 
        {
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
        } 
        catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
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
        catch (CANTimeoutException ex) 
        {
            reportCANException(ex);
        }
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Shooter Rotator", this);
        SmartDashboard.putData("Shooter Rotator Idle", new ShooterRotatorIdle());
        SmartDashboard.putData("Rotate Starting Position", new ShooterRotateTargetCommand(STARTING_POSITION));
    }
    
    private void reportCANException(Exception ex)
    {
        Robot.reportCANException(ex, "Rotator jag #" + RobotMap.SHOOTER_ROTATION_JAG);
    }
}

/**
 * A class that converts the potentiometer's value into degrees
 */
class ShooterPotentiometer extends AnalogChannel
{
    public ShooterPotentiometer(int channel)
    {
        super(channel);
    }
    private final double FACTOR = .2888192405, OFFSET = -47.9963;
    
    /**
     * 
     * @return The angle of the potentiometer in degrees
     */
    public double getAngle()
    {
        return (this.getValue() * FACTOR + OFFSET);
    }
    
    public double getRawAngle()
    {
        return this.getValue();
    }
}
