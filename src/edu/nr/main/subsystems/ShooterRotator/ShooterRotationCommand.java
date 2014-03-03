package edu.nr.main.subsystems.ShooterRotator;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterRotationCommand extends Command {
    private double m_speed;
    private double m_destination;
    private final ShooterRotator rotate = ShooterRotator.getInstance();
    
    public ShooterRotationCommand(double speed, double destination) {
        m_speed = speed;
        m_destination = destination;
        requires(rotate);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        rotate.rotate(m_destination);
    }

    protected boolean isFinished() {
        return rotate.isAtDestination(m_destination);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
