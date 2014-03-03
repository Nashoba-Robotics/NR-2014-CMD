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
    
    double x = 0, y = 0, lastEncoderDistance = 0;
    protected void initialize() 
    {
        x = 0;
        y = 0;
        lastEncoderDistance = 0;
        Robot.drive.resetGyro();
    }
    
    protected void execute()
    {
        double ySpeed = OI.getJoy1Y();
        double zSpeed = OI.getJoy1Z();
        
        double angle = Robot.drive.getGyroAngle();
        angle = angle * (Math.PI / 180);
        
        double ave = Robot.drive.getAverageEncoderDistance();// * (34d/33d);
        double delta_x_r = (ave-lastEncoderDistance);
        double deltax = delta_x_r * Math.cos(-angle);
        double deltay = delta_x_r * Math.sin(-angle);
        x += deltax;
        y += deltay;
        
        lastEncoderDistance = ave;
        
        SmartDashboard.putNumber("Location x", x);
        SmartDashboard.putNumber("Location y", y);
        
        
        //SmartDashboard.putNumber("Y axis", ySpeed);
        //SmartDashboard.putNumber("Z axis", zSpeed);
        SmartDashboard.putNumber("Encoder 1", Robot.drive.getRawEncoder(1));
        SmartDashboard.putNumber("Encoder 2", Robot.drive.getRawEncoder(2));
        
        //Snap to value: corrects for joystick error when it is close to 0 while it isn't being touched
        if(ySpeed < 0.05 && ySpeed > -0.05)
            ySpeed = 0;
        if(zSpeed < 0.05 && zSpeed > -0.05)
            zSpeed = 0;
        
        //Cut the speeds down because they are really fast
        zSpeed = (zSpeed/3) * 2;
        ySpeed = (ySpeed/4) * 3;
        
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
