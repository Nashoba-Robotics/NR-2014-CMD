package edu.nr.main.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.nr.main.Robot;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public class DriveCommand extends Command 
{
    public DriveCommand()
    {
        super("DriveCommand");
        this.requires(Robot.drive);
    }
    protected void initialize()
    {
        
    }

    protected void execute()
    {
        Robot.drive.drive(1, 0);
    }

    protected void end() 
    {
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWw");
    }

    protected void interrupted() 
    {
        
    }
    
    public boolean isFinished()
    {
        return false;
    }
}
