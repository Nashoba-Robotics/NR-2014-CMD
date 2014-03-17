/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.nr.main.subsystems.Puncher.PunchGroupCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author colin
 */
public class AutonomousPunchCommand extends CommandGroup
{
    public AutonomousPunchCommand(int delay)
    {
        this.addSequential(new WaitCommand(delay));
        this.addSequential(new PunchGroupCommand());
    }
}
