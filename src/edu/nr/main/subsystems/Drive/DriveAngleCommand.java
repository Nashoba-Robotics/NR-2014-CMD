/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * (WARNING) class was never tested- probably does not work correctly
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
        
        this.requires(Drive.getInstance());
    }
    
    protected void initialize() 
    {
        this.angle = SmartDashboard.getNumber("Angle");
        count = 0;
        Drive.getInstance().resetEncs();
        Drive.getInstance().resetGyro();
    }

    protected void execute() 
    {
        double currentAngle = Drive.getInstance().getGyroAngle();
        error = angle - currentAngle;
        SmartDashboard.putNumber("Error", error);
        double stopAngle = 50;
        double proportionalSpeed = Math.min(speed, (1/stopAngle)*error * speed);
        
        double integralSpeed =  count * error/Math.abs(error) * 0.005;
        double commandingSpeed = proportionalSpeed + integralSpeed;
        Drive.getInstance().drive(0, -commandingSpeed);
        
        SmartDashboard.putNumber("Proportional", proportionalSpeed);
        SmartDashboard.putNumber("Integral", integralSpeed);
        SmartDashboard.putNumber("Count", count);
        SmartDashboard.putNumber("Gyro", Drive.getInstance().getGyroAngle());
        SmartDashboard.putNumber("Error", error);
        
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
        return ((Math.abs(error) < epsilon) && (Math.abs(Drive.getInstance().getGyroRate()) < 10));
    }

    protected void end() 
    {
        
    }

    protected void interrupted()
    {
        
    }
}
