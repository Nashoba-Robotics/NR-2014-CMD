/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;

public class TensionCommand extends Command
{
   public TensionCommand()
   {
       this.requires(Puncher.getInstance());
   }
   protected void initialize() 
   {
        
   }

    protected void execute() 
    {
        Puncher.getInstance().setTentionerSpeed(Puncher.getInstance().TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}
