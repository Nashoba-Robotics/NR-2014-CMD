package edu.nr.main.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class PunchCommand extends Command {
    private final Winch winch = Winch.getInstance();
    private final Puncher punch = Puncher.getInstance();
    
    public PunchCommand() {
        super("Punch command");
        requires(punch);
        requires(winch);
    }
    
    protected void initialize() {
        new TensionIdleCommand().start();
    }

    protected void execute() 
    {
        punch.punch();
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
