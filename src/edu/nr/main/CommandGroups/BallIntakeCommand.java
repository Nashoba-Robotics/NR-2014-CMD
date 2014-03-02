/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.CommandGroups;

import edu.nr.main.subsystems.BottomRollers.RollCommand;
import edu.nr.main.subsystems.Flower.FlowerCloseCommand;
import edu.nr.main.subsystems.Flower.TopArmRunCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author colin
 */
public class BallIntakeCommand extends CommandGroup
{
    public BallIntakeCommand()
    {
        this.addParallel(new RollCommand());
        this.addParallel(new FlowerCloseCommand());
        this.addParallel(new TopArmRunCommand());
    }
}
