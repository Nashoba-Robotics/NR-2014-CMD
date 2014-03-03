package edu.nr.main.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

public class ResetDogGearCommand extends Command {
    private final Puncher punch = Puncher.getInstance();
    
    public ResetDogGearCommand() {
        super("Reset Dog Gear");
        requires(punch);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        punch.resetDogGear();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
