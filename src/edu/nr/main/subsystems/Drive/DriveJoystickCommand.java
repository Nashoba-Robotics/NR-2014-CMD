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
        this.requires(Robot.drive);
    }
    
    protected void initialize() 
    {
        
    }
    
    protected void execute()
    {
        double ySpeed = OI.getJoy1Y();
        double zSpeed = OI.getJoy1Z();
        
        SmartDashboard.putNumber("Encoder 1", Robot.drive.getRawEncoder(1));
        SmartDashboard.putNumber("Encoder 2", Robot.drive.getRawEncoder(2));
        
        //Snap to value: corrects for joystick error when it is close to 0 while it isn't being touched
        if(ySpeed < 0.05 && ySpeed > -0.05)
            ySpeed = 0;
        if(zSpeed < 0.05 && zSpeed > -0.05)
            zSpeed = 0;
        
        //START NEW CODE
        //Cut the speeds down because they are really fast
        //Try squaring it to get more fine control at first
        //zSpeed = ((zSpeed*zSpeed) * (Math.abs(zSpeed)/zSpeed));// * 2f / 3f;
        
        //For a steeper curve:
        //zSpeed = (Math.abs(zSpeed) / zSpeed) * Math.sqrt(Math.abs(zSpeed * zSpeed * zSpeed));
        
        //ySpeed = (Math.abs(ySpeed) / ySpeed) * Math.sqrt(Math.abs(ySpeed * ySpeed * ySpeed));
        //END NEW CODE
        
        //START OLD CODE
        //ySpeed *= (7f / 8f);
        //zSpeed *= 3f / 4f;
        
        Robot.drive.drive(ySpeed, zSpeed);
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
