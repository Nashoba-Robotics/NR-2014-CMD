/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.subsystems.TopArm.DelayedTopArmCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PunchGroupCommand extends CommandGroup
{
    public PunchGroupCommand()
    { 
        //this.addSequential(new ShooterRotateTargetCommand(ShooterRotator.AUTONOMOUS_ANGLE));
        this.addSequential(new DelayedTopArmCommand());
        this.addSequential(new PunchCommand());
        this.addSequential(new WaitCommand(1));
        this.addSequential(new TensionToDistanceCommandActual(Puncher.TENSIONER_SHOOTING_TENSION));
        //this.addSequential(new WaitCommand(2));
        //this.addSequential(new ResetDogEarCommand());
        //this.addParallel(new TopArmDownCommand());
        //this.addParallel(new ShooterRotateTargetCommand(ShooterRotator.STARTING_POSITION));
        //this.addSequential(new TensionToDistanceCommandActual(0.862f));
    }
}
