package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class FloweBloomCommand extends Command {
    private final Flower flower = Flower.getInstance();
    
    public FloweBloomCommand() {
        super("Top Arm Up");
        requires(flower);
    }
    
    protected void initialize() {
    }

    protected void execute()  {
        flower.bloom();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}