package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class FlowerIdleCommand extends Command {
    private final Flower flower = Flower.getInstance();
    
    public FlowerIdleCommand() {
        super("Flower Idle Command");
        requires(flower);
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
