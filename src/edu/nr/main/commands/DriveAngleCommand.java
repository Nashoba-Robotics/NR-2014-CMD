/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.commands;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class DriveAngleCommand extends Command
{
    private double angle, speed;
    private int count=0;
    private double initialGyroAngle;
    double error;
    
    private DriveAngleCommand(){}
    
    public DriveAngleCommand(double angle, double speed)
    {
        this.angle = angle;
        this.speed = speed;
    }
    
    protected void initialize() 
    {
        count = 0;
        Robot.drive.resetEncs();
        initialGyroAngle = Robot.drive.getGyroAngle();
    }

    protected void execute() 
    {
        double currentAngle = Robot.drive.getGyroAngle() - initialGyroAngle;
        double error = angle - currentAngle;
        double stopAngle = SmartDashboard.getNumber("Stop Rotation Angle");
        double finalSpeed = Math.min(speed, (1/stopAngle)*error * speed);
        
        speed = (error)/Math.abs(error);
        double integralSpeed = count * speed/Math.abs(speed) * 0.005;
        double commandingSpeed = -finalSpeed - integralSpeed;
        Robot.drive.drive(0, commandingSpeed);
        
        /*SmartDashboard.putNumber("Commanding angle", commandingSpeed);
        SmartDashboard.putNumber("Gyro", currentAngle);
        SmartDashboard.putNumber("Final Speed", finalSpeed);
        SmartDashboard.putNumber("Integral Speed", integralSpeed);
        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());*/
        
        count++;
    }

    protected boolean isFinished()
    {
        double epsilon = 2;
        return (Math.abs(error) < epsilon && Math.abs(Robot.drive.getGyroRate()) < 10);
    }

    protected void end() 
    {
        
    }

    protected void interrupted()
    {
        
    }
}
