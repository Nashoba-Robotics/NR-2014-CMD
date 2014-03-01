package edu.nr.main.subsystems.ShooterRotator;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterRotationCommand extends Command
{
    private double speed;
    private double destination;
    public ShooterRotationCommand(double speed, double destination)
    {
        this.speed = speed;
        this.destination = destination;
        this.requires(Robot.shooterRotator);
    }
    
    protected void initialize() {
    }

    protected void execute()
    {
        Robot.shooterRotator.rotate(speed, destination);
    }

    protected boolean isFinished()
    {
        return Robot.shooterRotator.isAtDestination(destination);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
