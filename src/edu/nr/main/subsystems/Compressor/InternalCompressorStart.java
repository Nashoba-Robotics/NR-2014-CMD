package edu.nr.main.subsystems.Compressor;

import edu.wpi.first.wpilibj.command.Command;

public class InternalCompressorStart extends Command {
    private final InternalCompressor compr = InternalCompressor.getInstance();
    
    public InternalCompressorStart() {
        super("Start Internal Compressor");
        this.requires(compr);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        compr.start();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}