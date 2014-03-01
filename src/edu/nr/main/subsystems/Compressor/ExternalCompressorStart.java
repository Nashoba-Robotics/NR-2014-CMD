package edu.nr.main.subsystems.Compressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ExternalCompressorStart extends Command {
    
    public ExternalCompressorStart() {
        super("Start External Compressor");
        requires(Robot.extCompressor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.extCompressor.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}