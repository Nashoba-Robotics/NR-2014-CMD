/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class PunchCommand extends Command
{
    public PunchCommand()
    {
        this.requires(Robot.puncher);
    }
    
    protected void initialize() 
    {
        Robot.puncher.setTentionerSpeed(0);
    }

    protected void execute() 
    {
        Robot.puncher.punch();
    }

    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}
