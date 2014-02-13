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
public class ShooterRotatorIdle extends Command
{
    public ShooterRotatorIdle()
    {
        super("Idle");
        this.requires(Robot.shooterRotator);
    }
    
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        Robot.shooterRotator.rotate(0);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
