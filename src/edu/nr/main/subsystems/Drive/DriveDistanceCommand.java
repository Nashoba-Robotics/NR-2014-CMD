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
public class DriveDistanceCommand extends Command
{
    private int count;
    private boolean doneDriving;
    private float distance, speed;
    private double initialGyroAngle;
    private double lastEncoderDistance = 0;
    private double x=0, y=0;
    
    private DriveDistanceCommand(){}
    
    public DriveDistanceCommand(float distance, float speed)
    {
        super("DriveDistanceCommand");
        this.distance = distance;
        this.speed = speed;
        this.requires(Robot.drive);
    }
    protected void initialize() 
    {
        doneDriving = false;
        count = 0;
        Robot.drive.resetEncs();
        Robot.drive.resetGyro();
        initialGyroAngle = Robot.drive.getGyroAngle();
    }

    protected void execute() 
    {
        double angle = Robot.drive.getGyroAngle();// - initialGyroAngle;
        SmartDashboard.putNumber("Gyro", angle);
        double turnAngle = 0;
        if(Math.abs(angle) > 0.3)
        {
            if(angle < 0)
                turnAngle = Math.min(0.5, -angle*0.05);
            else
                turnAngle = Math.min(-0.5, -angle*0.05);
        }
        
        double ave = Robot.drive.getAverageEncoderDistance();
        
        /*Position calculations
        double delta_x_r = (ave-lastEncoderDistance);
        double deltax = delta_x_r * Math.cos(-angle);
        double deltay = delta_x_r * Math.sin(-angle);
        x += deltax;
        y += deltay;*/
        
        count++;
        double err = distance - ave;

        double proportionalStopDistance = 4;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * speed;
        double integralSpeed = count * speed/Math.abs(speed) * 0.002;
        double newSpeed = Math.min(speed, proportionalSpeed + integralSpeed);
        SmartDashboard.putNumber("TurnAngle", turnAngle);
        SmartDashboard.putNumber("New Speed", newSpeed);
        Robot.drive.drive(newSpeed, turnAngle);

        
        SmartDashboard.putNumber("Encs", Robot.drive.getAverageEncoderDistance());
        /*SmartDashboard.putNumber("Encoder 1", val1);
        SmartDashboard.putNumber("Encoder 2", val2);
        SmartDashboard.putNumber("Gyro", angle);
        SmartDashboard.putNumber("turn velocity", turnAngle);
        SmartDashboard.putNumber("Driving Speed", newSpeed);
        SmartDashboard.putNumber("Val1", e1.getRate());
        SmartDashboard.putNumber("Val2", e2.getRate());
        SmartDashboard.putNumber("Integral Speed", integralSpeed);
        SmartDashboard.putNumber("Count", count);*/
        
        lastEncoderDistance = ave;
    }

    protected boolean isFinished() 
    {
        if((Robot.drive.getAverageEncoderDistance() > distance));
            //System.out.println("Should Finish");
        return (Robot.drive.getAverageEncoderDistance() > distance);
    }

    protected void end() 
    {
        Robot.drive.drive(0, 0);
    }

    protected void interrupted() 
    {
        Robot.drive.drive(0, 0);
    }
}
