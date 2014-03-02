package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class FlowerCloseCommand extends Command {
    private final Flower flower = Flower.getInstance();
    
    public FlowerCloseCommand() {
        super("Top Arm Down");
        requires(flower);
    }
    protected void initialize() {
        
    }

    protected void execute() {
        flower.close();
    }

    protected boolean isFinished() {
         return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
