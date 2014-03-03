package edu.nr.main.subsystems.RaspberryPi;

import edu.wpi.first.wpilibj.command.Command;

public class RaspberryPiIdleCommand extends Command {
    
    public RaspberryPiIdleCommand() {
        super("Raspberry Pie Do Nothing");
        requires(RaspberryPi.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
        RaspberryPi.getInstance().idle();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
