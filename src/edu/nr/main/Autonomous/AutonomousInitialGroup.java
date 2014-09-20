package edu.nr.main.Autonomous;

import edu.nr.main.subsystems.Drive.DriveDistanceCommand;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.Puncher.TensionToDistanceCommandActual;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotateTargetCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousInitialGroup extends CommandGroup
{
    public AutonomousInitialGroup()
    {
        this.addParallel(new TensionToDistanceCommandActual(Puncher.TENSIONER_SHOOTING_TENSION));
        this.addParallel(new ShooterRotateTargetCommand(ShooterRotator.AUTONOMOUS_ANGLE));
        this.addParallel(new DriveDistanceCommand(8.5f, .7f));
    }
}
