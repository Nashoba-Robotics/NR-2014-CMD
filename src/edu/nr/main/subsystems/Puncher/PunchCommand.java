/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;

public class PunchCommand extends Command
{
    public PunchCommand()
    {
        this.requires(Puncher.getInstance());
    }
    
    protected void initialize() 
    {
        Puncher.getInstance().setTentionerSpeed(0);
    }

    protected void execute() 
    {
        Puncher.getInstance().punch();
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
