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
    final private double speed;
    private double angle;
    private int count=0;
    private double initialGyroAngle;
    double error;
    private boolean wasLastNegative =false;
    
    private DriveAngleCommand(){angle=-1; speed=-1;}
    
    public DriveAngleCommand(double angle, double speed)
    {
        this.speed = speed * (angle/Math.abs(angle));
        this.requires(Robot.drive);
    }
    
    protected void initialize() 
    {
        this.angle = SmartDashboard.getNumber("Angle");
        count = 0;
        Robot.drive.resetEncs();
        Robot.drive.resetGyro();
    }

    protected void execute() 
    {
        double currentAngle = Robot.drive.getGyroAngle();
        error = angle - currentAngle;
        SmartDashboard.putNumber("Error", error);
        double stopAngle = 50;
        double proportionalSpeed = Math.min(speed, (1/stopAngle)*error * speed);
        
        double integralSpeed =  count * error/Math.abs(error) * 0.005;
        double commandingSpeed = proportionalSpeed + integralSpeed;
        Robot.drive.drive(0, -commandingSpeed);
        
        SmartDashboard.putNumber("Proportional", proportionalSpeed);
        SmartDashboard.putNumber("Integral", integralSpeed);
        SmartDashboard.putNumber("Count", count);
        SmartDashboard.putNumber("Gyro", Robot.drive.getGyroAngle());
        SmartDashboard.putNumber("Error", error);
        /*SmartDashboard.putNumber("Commanding angle", commandingSpeed);
        SmartDashboard.putNumber("Gyro", currentAngle);
        SmartDashboard.putNumber("Final Speed", finalSpeed);
        SmartDashboard.putNumber("Integral Speed", integralSpeed);
        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());*/
        
        if(error < stopAngle)
            count++;
        
        //If we overshot, reset the integral value because we will just shoot back and forth
        //faster and faster otherwise
        if(wasLastNegative != (error < 0))
            count = 0;
        wasLastNegative = (error < 0);
    }

    protected boolean isFinished()
    {
        double epsilon = 2;
        return ((Math.abs(error) < epsilon) && (Math.abs(Robot.drive.getGyroRate()) < 10));
    }

    protected void end() 
    {
        
    }

    protected void interrupted()
    {
        
    }
}
