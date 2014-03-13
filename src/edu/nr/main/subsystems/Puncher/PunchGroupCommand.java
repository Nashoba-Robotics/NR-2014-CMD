/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.subsystems.TopArm.TopArmUpCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author colin
 */
public class PunchGroupCommand extends CommandGroup
{
    public PunchGroupCommand()
    {
        this.addSequential(new TopArmUpCommand());
        this.addSequential(new WaitCommand(0.5));
        this.addSequential(new PunchCommand());
        this.addSequential(new WaitCommand(3));
        this.addSequential(new ResetDogEarCommand());
        this.addSequential(new TensionToDistanceCommandActual(0.862f));
    }
}
