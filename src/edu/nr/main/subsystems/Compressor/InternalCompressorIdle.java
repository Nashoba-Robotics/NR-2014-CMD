package edu.nr.main.subsystems.Compressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InternalCompressorIdle extends Command
{
    public InternalCompressorIdle()
    {
        super("Internal Compressor Idle");
        requires(Robot.intCompressor);
    }
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        Robot.intCompressor.stop();
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
