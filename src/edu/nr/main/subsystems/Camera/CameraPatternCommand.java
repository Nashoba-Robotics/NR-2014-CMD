/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Camera;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author colin
 */
public class CameraPatternCommand extends CommandGroup
{
    public CameraPatternCommand()
    {
        this.addParallel(new CameraOffCommand());
        this.addSequential(new SetInnerLightCommand(true));
        this.addSequential(new WaitCommand(.5));
        this.addParallel(new SetInnerLightCommand(false));
        this.addSequential(new SetOuterLightCommand(true));
        this.addSequential(new WaitCommand(0.5));
        this.addParallel(new CameraOffCommand());
        this.addSequential(new WaitCommand(0.5));
        this.addSequential(new CameraOnCommand());
    }
}
