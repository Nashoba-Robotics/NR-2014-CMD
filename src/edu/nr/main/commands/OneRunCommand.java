/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class OneRunCommand extends Command
{
    private Runnable run;
    public OneRunCommand(NamedRunnable run, Subsystem requires)
    {
        super(run.name);
        this.run = run;
        this.requires(requires);
    }
    
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        run.run();
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
