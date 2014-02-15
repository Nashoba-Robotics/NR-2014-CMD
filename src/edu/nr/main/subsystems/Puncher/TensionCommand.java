/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class TensionCommand extends Command
{
   private float speed;
   public TensionCommand(float speed)
   {
       this.speed = speed;
       this.requires(Robot.puncher);
   }
   protected void initialize() 
   {
        
   }

    protected void execute() 
    {
        Robot.puncher.setTentionerSpeed((float) SmartDashboard.getNumber("Tensioner Speed"));
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
