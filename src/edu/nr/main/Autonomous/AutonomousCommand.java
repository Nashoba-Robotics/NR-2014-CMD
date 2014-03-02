/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.nr.main.subsystems.NetworkCameraLights.NetworkCameraLightsOnCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author colin
 */
public class AutonomousCommand extends CommandGroup
{
    public AutonomousCommand()
    {
        this.addParallel(new NetworkCameraLightsOnCommand());
        this.addSequential(new WaitCommand(1));
        this.addSequential(new CheckHotGoalCommand());
    }
}
