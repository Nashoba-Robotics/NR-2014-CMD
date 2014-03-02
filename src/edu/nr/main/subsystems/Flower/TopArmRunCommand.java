package edu.nr.main.subsystems.Flower;

import edu.wpi.first.wpilibj.command.Command;

public class TopArmRunCommand extends Command {
    private final TopArm arm = TopArm.getInstance();
    
    public TopArmRunCommand() {
        super("Top Arm Run");
        requires(arm);
    }

    protected void initialize() {
    }

    protected void execute() {
        arm.run(1.0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
