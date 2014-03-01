package edu.nr.main.Autonomous;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


public class CheckHotGoalCommand extends Command
{
    private NetworkTable realmTable = NetworkTable.getTable
                                               (RobotMap.ROBO_REALM_TABLE_NAME);

    protected void initialize() {
    }

    protected void execute() 
    {
        try
        {
            if(realmTable.getNumber("isHot") == 1 && (realmTable.getNumber("isVisible") == 1))
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