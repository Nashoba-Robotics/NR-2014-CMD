package edu.nr.main.subsystems.RaspberryPie;

import edu.wpi.first.wpilibj.command.Command;

public class RaspberryPieIdleCommand extends Command {
    
    public RaspberryPieIdleCommand() {
        super("Raspberry Pie Do Nothing");
        requires(RaspberryPie.getInstance());
    }

    protected void initialize() {
    }

    protected void execute() {
        RaspberryPie.getInstance().idle();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
