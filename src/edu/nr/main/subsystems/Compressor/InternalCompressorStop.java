package edu.nr.main.subsystems.Compressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class InternalCompressorStop extends Command
{
    public InternalCompressorStop()
    {
        super("Stop Internal Compressor");
        this.requires(Robot.intCompressor);
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
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
