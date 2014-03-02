package edu.nr.main.subsystems.BottomRollers;

import edu.wpi.first.wpilibj.command.Command;

public class StopRollCommand extends Command
{
    public StopRollCommand() {
        super("Stop Roll");
        requires(BottomRollers.getInstance());
    }
    protected void initialize() {
    }

    protected void execute() {
        BottomRollers.getInstance().stopRoll();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
