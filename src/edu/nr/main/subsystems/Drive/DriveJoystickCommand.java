/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.oi.OI;
import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class DriveJoystickCommand extends Command
{
    public DriveJoystickCommand()
    {
        this.requires(Drive.getInstance());
    }
    
    protected void initialize() 
    {
        
    }
    
    protected void execute()
    {
        double ySpeed = OI.getJoy1Y();
        double zSpeed = OI.getJoy1Z();
        
        SmartDashboard.putNumber("Encoder 1", Drive.getInstance().getRawEncoder(1));
        SmartDashboard.putNumber("Encoder 2", Drive.getInstance().getRawEncoder(2));
        
        //Snap to value: corrects for joystick error when it is close to 0 while it isn't being touched
        if(ySpeed < 0.05 && ySpeed > -0.05)
            ySpeed = 0;
        if(zSpeed < 0.05 && zSpeed > -0.05)
            zSpeed = 0;
        
        Drive.getInstance().drive(ySpeed, zSpeed);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}
