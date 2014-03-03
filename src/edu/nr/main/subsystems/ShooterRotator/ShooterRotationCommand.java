/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class ShooterRotationCommand extends Command
{
    private double speed;
    public ShooterRotationCommand(double speed)
    {
        this.speed = speed;
        this.requires(Robot.shooterRotator);
    }
    
    protected void initialize() {
    }

    protected void execute()
    {
        /*double err = destination - Robot.shooterRotator.getRotation();
        double proportionalStopDistance = 0.2;
        double proportionalSpeed = ((1/proportionalStopDistance)*err)*speed;
        double finalSpeed = Math.min(speed, proportionalSpeed);*/
        Robot.shooterRotator.rotate(speed);
    }

    protected boolean isFinished()
    {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
