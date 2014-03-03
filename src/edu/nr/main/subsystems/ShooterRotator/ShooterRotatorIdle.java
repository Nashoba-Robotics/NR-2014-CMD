package edu.nr.main.subsystems.ShooterRotator;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterRotatorIdle extends Command {
    private final ShooterRotator rotate = ShooterRotator.getInstance();
    
    public ShooterRotatorIdle() {
        super("Shooter Rotator Idle");
        requires(rotate);
    }
    
    protected void initialize() {
        
    }

    protected void execute() {
        rotate.stop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
