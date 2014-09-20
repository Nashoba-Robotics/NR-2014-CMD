/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * Checks to see if RoboRealms indicated in smart dashboard that the goal we are facing is hot.
 * (Not used in competition)
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
        catch(TableKeyNotDefinedException t)
        {
            System.err.println("ERROR DURING AUTONOMOUS HOT GOAL CHECK");
            new AutonomousPunchCommand(0).start();
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
