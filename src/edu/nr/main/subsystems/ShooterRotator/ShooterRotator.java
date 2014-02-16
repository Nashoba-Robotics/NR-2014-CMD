/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
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
    private CANJaguar rotationJag;
    public ShooterRotator()
    {
        try 
        {
            rotationJag = new CANJaguar(RobotMap.SHOOTER_ROTATION_JAG);
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            rotationJag.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            rotationJag.configPotentiometerTurns(1);
        } 
        catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
    }
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ShooterRotatorIdle());
    }
    
    public double getRotation()
    {
        try 
        {
            return rotationJag.getPosition();
        } 
        catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
        return 2;
    }
    
    public void rotate(double speed)
    {
        try 
        {
            rotationJag.setX(speed);
        }
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Shooter Rotator", this);
        SmartDashboard.putData("Shooter Rotator Idle", new ShooterRotatorIdle());
//SmartDashboard.putData("Shooter Rotation (0.4)", new ShooterRotationCommand(0.4));
        //SmartDashboard.putData("Shooter Rotation (-0.4)", new ShooterRotationCommand(-0.4));
    }
}
