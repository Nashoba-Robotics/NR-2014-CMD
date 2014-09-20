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
 * A command that uses PID to drive the robot a certain distance at a defined max speed
 */
public class DriveDistanceCommand extends Command
{
    private int count = 0;
    private float distance, speed;
    private double initialGyroAngle, initialEncoderDistance;
    private boolean goingForward;
    
    private DriveDistanceCommand(){}
    
    public DriveDistanceCommand(float distance, float speed)
    {
        super("DriveDistanceCommand");
        this.speed = speed;
        this.requires(Drive.getInstance());
        this.distance = distance;
    }
    protected void initialize() 
    {
        Drive.getInstance().setSecondGear();
        
        initialEncoderDistance = Drive.getInstance().getAverageEncoderDistance();
        
        goingForward = (distance > 0);
        
        initialGyroAngle = Drive.getInstance().getGyroAngle();
    }

    protected void execute() 
    {
        //Use the gyroscope to correct our angle if we have started to turn slightly
        double angle = Drive.getInstance().getGyroAngle() - initialGyroAngle;
        SmartDashboard.putNumber("Delta Gyro", angle);
        double turnAngle = 0;

        if(angle < 0)
            turnAngle = Math.min(0.1, -angle*0.05);
        else
            turnAngle = Math.max(-0.1, -angle*0.05);

        double ave = Drive.getInstance().getAverageEncoderDistance() - initialEncoderDistance;
        SmartDashboard.putNumber("Encoder Delta", ave);


        //Use PID to figure out how fast we want to be moving
        count++;

        //Do the math in posotive
        double err = Math.abs(distance - ave);

        double proportionalStopDistance = 4;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * speed;
        double integralSpeed = count * speed/Math.abs(speed) * 0.002;
        double newSpeed = Math.min(speed, proportionalSpeed + integralSpeed);

        newSpeed *= ((goingForward)?-1:1); // Reverse the speed if we are going backwards

        SmartDashboard.putNumber("TurnAngle", turnAngle);
        SmartDashboard.putNumber("I Value", integralSpeed);
        SmartDashboard.putNumber("New Speed", newSpeed);
        SmartDashboard.putNumber("turn Angle", turnAngle);

        Drive.getInstance().drive(newSpeed, turnAngle);
    }

    protected boolean isFinished() 
    {
        if(goingForward)
            return (Drive.getInstance().getAverageEncoderDistance() - initialEncoderDistance >= distance);
        else
            return (Drive.getInstance().getAverageEncoderDistance() - initialEncoderDistance <= distance);
    }

    protected void end() 
    {
        Drive.getInstance().drive(0, 0);
    }

    protected void interrupted() 
    {
        Drive.getInstance().drive(0, 0);
    }
}
