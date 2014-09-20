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
        super("Shooter Rotator Idle");
        this.requires(ShooterRotator.getInstance());
    }
    
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        ShooterRotator.getInstance().rotate(0);
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
