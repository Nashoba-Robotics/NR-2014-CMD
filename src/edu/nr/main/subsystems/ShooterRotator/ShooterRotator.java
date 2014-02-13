/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class ShooterRotator extends Subsystem
{
    private CANJaguar rotationJag;
    protected void initDefaultCommand() 
    {
        try 
        {
            rotationJag = new CANJaguar(4);
            rotationJag.configNeutralMode(CANJaguar.NeutralMode.kBrake);
            rotationJag.setPositionReference(CANJaguar.PositionReference.kPotentiometer);
            rotationJag.configPotentiometerTurns(1);
        } 
        catch (CANTimeoutException ex) 
        {
            ex.printStackTrace();
        }
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
        try {
            rotationJag.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
