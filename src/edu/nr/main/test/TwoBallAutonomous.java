/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.test;

import edu.nr.main.Autonomous.*;
import edu.nr.main.subsystems.Drive.DriveDistanceCommand;
import edu.nr.main.subsystems.Drive.ResetEncs;
import edu.nr.main.subsystems.Drive.ShiftCommand;
import edu.nr.main.subsystems.Puncher.PunchCommand;
import edu.nr.main.subsystems.Puncher.PunchGroupCommand;
import edu.nr.main.subsystems.Puncher.ResetDogEarCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotateTargetCommand;
import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.nr.main.subsystems.Puncher.TensionToDistanceCommandActual;

/**
 *
 * @author colin
 */
public class TwoBallAutonomous extends CommandGroup
{
    public TwoBallAutonomous()
    {
        this.addSequential(new ResetEncs());
        this.addSequential(new ShiftCommand(true));
        this.addSequential(new AutonomousInitialGroup());
        this.addParallel(new TopArmUpCommand());
        
        this.addSequential(new WaitCommand(2));
        this.addSequential(new PunchGroupCommand());
    }
}
