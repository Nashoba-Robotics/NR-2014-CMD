package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class TopArmIdleCommand extends Command {
    private final TopArm arm = TopArm.getInstance();
    
    public TopArmIdleCommand() {
        requires(arm);
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