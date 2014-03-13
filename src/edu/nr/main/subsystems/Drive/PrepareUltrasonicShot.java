/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.subsystems.Puncher.TensionToDistanceCommandActual;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotateTargetCommand;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Robotics
 */
public class PrepareUltrasonicShot extends CommandGroup
{
    public PrepareUltrasonicShot()
    {
        this.addParallel(new TopArmUpCommand());
        this.addParallel(new DriveToUltrasonicDistance(5));
        this.addParallel(new ShooterRotateTargetCommand(ShooterRotator.AUTONOMOUS));
        this.addParallel(new TensionToDistanceCommandActual(0.96f));
    }
}
