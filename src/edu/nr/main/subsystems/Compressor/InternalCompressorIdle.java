package edu.nr.main.subsystems.Compressor;

import edu.wpi.first.wpilibj.command.Command;

public class InternalCompressorIdle extends Command {
    private final InternalCompressor compr = InternalCompressor.getInstance();
    
    public InternalCompressorIdle() {
        super("Internal Compressor Idle");
        requires(compr);
    }
    protected void initialize() {
    }

    protected void execute() {
        compr.stop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
    }
}
