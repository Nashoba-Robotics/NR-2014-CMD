package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class TopArmStopCommand extends Command {
    private final TopArm arm = TopArm.getInstance();
    
    public TopArmStopCommand() {
        super("Top Arm Stop");
        requires(arm);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        arm.run(0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}