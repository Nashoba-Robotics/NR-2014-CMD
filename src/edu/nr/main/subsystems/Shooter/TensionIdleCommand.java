package edu.nr.main.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class TensionIdleCommand extends Command {
    private final Winch winch = Winch.getInstance();
    
    public TensionIdleCommand() {
        super("Tension Idle");
        requires(winch);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        winch.setWinchSpeed(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
