/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.nr.main.subsystems.Puncher.PunchGroupCommand;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the ultrasonic sensor to drive to a certain distance from an object (PID)
 */
public class DriveToUltrasonicDistance extends Command
{
    private float target;
    private boolean goingForward;
    public DriveToUltrasonicDistance(float target)
    {
        this.target = target;
        this.requires(Drive.getInstance());
    }
    
    protected void initialize() 
    {
        goingForward = (Drive.getInstance().getUltrasonicFeet() > target);
    }

    float speed = 0.8f;
    protected void execute() 
    {
        goingForward = Drive.getInstance().getUltrasonicFeet() > target;
        double err = Math.abs(target - Drive.getInstance().getUltrasonicFeet());
        
        if(Drive.getInstance().getUltrasonicFeet() < 8)
        {
            new PunchGroupCommand().start();
        }

        double proportionalStopDistance = 3;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * speed;
        double newSpeed = Math.min(speed, proportionalSpeed);
        
        newSpeed *= ((goingForward)?-1:1); // Reverse the speed if we are going backwards NOTE: The drive train is reversed
        
        Drive.getInstance().drive(newSpeed, 0);
    }

    protected boolean isFinished() 
    {
        return Math.abs(target - Drive.getInstance().getUltrasonicFeet()) < 0.5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
