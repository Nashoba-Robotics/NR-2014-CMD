/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Robotics
 */
public class DriveToUltrasonicDistance extends Command
{
    private float target;
    private boolean goingForward;
    public DriveToUltrasonicDistance(float target)
    {
        this.target = target;
    }
    
    protected void initialize() 
    {
        this.requires(Robot.drive);
        goingForward = (Robot.drive.getUltrasonicFeet() > target);
    }

    float speed = 0.8f;
    protected void execute() 
    {
        double err = Math.abs(target - Robot.drive.getUltrasonicFeet());

        double proportionalStopDistance = 3;
        double proportionalSpeed = ((1/(proportionalStopDistance)) * err) * speed;
        double newSpeed = Math.min(speed, proportionalSpeed);
        
        newSpeed *= ((goingForward)?-1:1); // Reverse the speed if we are going backwards NOTE: The drive train is reversed
    }

    protected boolean isFinished() 
    {
        return Math.abs(target - Robot.drive.getUltrasonicFeet()) < 0.5;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
