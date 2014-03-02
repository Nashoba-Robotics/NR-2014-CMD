/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.CommandGroups;

import edu.nr.main.subsystems.BottomRollers.StopRollCommand;
import edu.nr.main.subsystems.Flower.TopArmStopCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author colin
 */
public class StopBallIntakeCommand extends CommandGroup
{
    public StopBallIntakeCommand()
    {
        this.addParallel(new StopRollCommand());
        this.addParallel(new TopArmStopCommand());
        //Note, we don't want to undeploy the top arm because it is supposed to hold the ball in place,
        //we just want to stop the wheels from rubbing against the ball
    }
}
