package edu.nr.main.Autonomous;

import edu.nr.main.subsystems.Drive.ResetEncs;
import edu.nr.main.subsystems.Drive.ShiftCommand;
import edu.nr.main.subsystems.Puncher.PunchGroupCommand;
import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup
{
    public AutonomousCommand()
    {
        this.addSequential(new ResetEncs());
        this.addSequential(new ShiftCommand(false));
        this.addSequential(new AutonomousInitialGroup());
        
        this.addParallel(new TopArmUpCommand());
        this.addSequential(new WaitCommand(2));
        this.addSequential(new PunchGroupCommand());
    }
}
