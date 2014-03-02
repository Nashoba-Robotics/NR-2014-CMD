package edu.nr.main.subsystems.Compressor;

import edu.wpi.first.wpilibj.command.Command;

public class ExternalCompressorRun extends Command {
    private final ExternalCompressor compr = ExternalCompressor.getInstance();
    
    public ExternalCompressorRun() {
        super("Run External Compressor Routine");
        requires(compr);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        compr.run();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return compr.getLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
