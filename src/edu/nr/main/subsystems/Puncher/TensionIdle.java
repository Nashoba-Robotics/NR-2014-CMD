/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;

public class TensionIdle extends Command
{
    public TensionIdle()
    {
        super("Tension Idle");
        this.requires(Puncher.getInstance());
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Puncher.getInstance().setTentionerSpeed(0);
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() {
    }
    
}
