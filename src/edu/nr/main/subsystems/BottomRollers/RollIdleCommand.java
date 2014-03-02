package edu.nr.main.subsystems.BottomRollers;

import edu.wpi.first.wpilibj.command.Command;

public class RollIdleCommand extends Command {
    public RollIdleCommand() {
        requires(BottomRollers.getInstance());
    }
    
    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
