package edu.nr.main.subsystems.Flower;

import edu.nr.main.subsystems.BottomRollers.RollCommand;
import edu.wpi.first.wpilibj.command.Command;

public class GetReadyToIntakeCommand extends Command {
    
    public GetReadyToIntakeCommand() {
        super("Start Ball Intake Command");
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        new FlowerBloomCommand().start();
        new TopArmRunCommand().start();
        new RollCommand().start();
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
