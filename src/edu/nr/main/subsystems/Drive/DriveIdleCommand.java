package edu.nr.main.subsystems.Drive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveIdleCommand extends Command {
    private final Drive drv = Drive.getInstance();
    
    public DriveIdleCommand() {
        requires(drv);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        drv.drive(0, 0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
