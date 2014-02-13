/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.BottomRollers;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class RollCommand extends Command
{
    private double speed;
    public RollCommand(double speed)
    {
        super("Roll Command");
        this.speed = speed;
        this.requires(Robot.rollers);
    }
    
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        Robot.rollers.setRollSpeed(speed);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end()
    {
        Robot.rollers.setRollSpeed(0);
    }

    protected void interrupted() 
    {
        Robot.rollers.setRollSpeed(0);
    }
    
}
