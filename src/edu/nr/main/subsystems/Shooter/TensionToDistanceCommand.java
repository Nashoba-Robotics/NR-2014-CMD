package edu.nr.main.subsystems.Shooter;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TensionToDistanceCommand extends Command {
    private final Winch winch = Winch.getInstance();
    
    public TensionToDistanceCommand() {
        super("Tension to Distance");
        requires(winch);
    }
    
    protected void initialize() {
        winch.setWinchLimits(SmartDashboard.getNumber("Tension Distance"));
    }

    protected void execute() {
        winch.setWinchSpeed(RobotMap.WINCH_REGULAR_SPEED);
    }

    protected boolean isFinished() {
        return !winch.getLimitSwitch();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
