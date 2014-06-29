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
 *
 * @author colin
 */
public class DriveDistanceCommand extends Command
{
    private int count = 0;
    private boolean doneDriving = false;
    private float distance, speed;
    private double initialGyroAngle, initialEncoderDistance;
    private double lastEncoderDistance = 0;
    private boolean goingForward;
    private double x=0, y=0;
    
    private DriveDistanceCommand(){}
    
    public DriveDistanceCommand(float distance, float speed)
    {
        super("DriveDistanceCommand");
        this.speed = speed;
        this.requires(Robot.drive);
        this.distance = distance;
    }
    protected void initialize() 
    {
        this.setTimeout(1.5);
        Robot.drive.setSecondGear();
        initialEncoderDistance = Robot.drive.getAverageEncoderDistance();
        
        goingForward = (distance > 0);
        
        initialGyroAngle = Robot.drive.getGyroAngle();
    }

    protected void execute() 
    {
        if(!this.isTimedOut()) {
        double angle = Robot.drive.getGyroAngle() - initialGyroAngle;
        SmartDashboard.putNumber("Delta Gyro", angle);
        double turnAngle = 0;
        
        if(angle < 0)
            turnAngle = Math.min(0.1, -angle*0.05);
        else
            turnAngle = Math.max(-0.1, -angle*0.05);
        
        double ave = Robot.drive.getAverageEncoderDistance() - initialEncoderDistance;
        SmartDashboard.putNumber("Encoder Delta", ave);
        
        count++;
        
        //Do the math in posotive
        double err = Math.abs(distance - ave);

        double proportionalStopDistance = 4;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * speed;
        double integralSpeed = count * speed/Math.abs(speed) * 0.002;
        double newSpeed = Math.min(speed, proportionalSpeed + integralSpeed);
        
        newSpeed *= ((goingForward)?-1:1); // Reverse the speed if we are going backwards
        newSpeed = -0.7;
        
        SmartDashboard.putNumber("TurnAngle", turnAngle);
        SmartDashboard.putNumber("I Value", integralSpeed);
        SmartDashboard.putNumber("New Speed", newSpeed);
        Robot.drive.drive(newSpeed, turnAngle);
        SmartDashboard.putNumber("turn Angle", turnAngle);

        
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
        else {
            Robot.drive.drive(0, 0);
        }
    }

    protected boolean isFinished() 
    {
        /*
        if(goingForward)
            return (Robot.drive.getAverageEncoderDistance() - initialEncoderDistance >= distance);
        else
            return (Robot.drive.getAverageEncoderDistance() - initialEncoderDistance <= distance); */
        //if((Robot.drive.getAverageEncoderDistance() > distance));
            //System.out.println("Should Finish"); 
        
        //return (Robot.drive.getAverageEncoderDistance() > distance);
        return this.isTimedOut();
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
