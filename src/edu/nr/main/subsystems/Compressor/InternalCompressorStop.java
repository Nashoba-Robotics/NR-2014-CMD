package edu.nr.main.subsystems.Compressor;

import edu.wpi.first.wpilibj.command.Command;

public class InternalCompressorStop extends Command {
    private final InternalCompressor compr = InternalCompressor.getInstance();
    
    public InternalCompressorStop() {
        super("Stop Internal Compressor");
        requires(compr);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        compr.stop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}