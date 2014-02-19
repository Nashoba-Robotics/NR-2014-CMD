/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class CheckHotGoalCommand extends Command
{

    protected void initialize() {
    }

    protected void execute() 
    {
        try
        {
            if(SmartDashboard.getNumber("isHot") == 1 && (SmartDashboard.getNumber("isVisible") == 1))
            {
                new AutonomousPunchCommand(0).start();
            }
            else
            {
               new AutonomousPunchCommand(4).start();
            }
        }
        catch(Throwable t)
        {
            System.err.println("ERROR DURING AUTONOMOUS");
        }
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
