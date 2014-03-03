package edu.nr.main.Autonomous;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;


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
            if( (realmTable.getNumber("isHot") == 1) && 
                (realmTable.getNumber("isVisible") == 1))
            {
                new AutonomousPunchCommand(0).start();
            }
            else
            {
               new AutonomousPunchCommand(4).start();
            }
        }
        catch(TableKeyNotDefinedException e)
        {
            System.err.println("ERROR DURING CHECK HOT GOAL");
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