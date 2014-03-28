/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.nr.main.subsystems.Camera.CameraOnCommand;
import edu.nr.main.subsystems.Drive.DriveDistanceCommand;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.Puncher.ResetDogEarCommand;
import edu.nr.main.subsystems.Puncher.TensionToDistanceCommandActual;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotateTargetCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Robotics
 */
public class AutonomousInitialGroup extends CommandGroup
{
    public AutonomousInitialGroup()
    {
        this.addParallel(new CameraOnCommand());
        this.addParallel(new TensionToDistanceCommandActual(Puncher.TENSIONER_SHOOTING_TENSION));
        this.addParallel(new ShooterRotateTargetCommand(ShooterRotator.AUTONOMOUS_ANGLE));
        this.addParallel(new DriveDistanceCommand(8.5f, .6f));
    }
}
