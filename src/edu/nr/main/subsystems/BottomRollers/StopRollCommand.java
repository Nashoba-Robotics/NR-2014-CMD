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
public class StopRollCommand extends Command
{
    public StopRollCommand()
    {
        super("Stop Roll");
        this.requires(BottomRollers.getInstance());
    }
    protected void initialize()
    {
        
    }

    protected void execute() 
    {
        BottomRollers.getInstance().stopRoll();
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
