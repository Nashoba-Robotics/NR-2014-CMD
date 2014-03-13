/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.subsystems.Puncher.PunchGroupCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Robotics
 */
public class UltrasonicGroupShot extends CommandGroup
{
    public UltrasonicGroupShot()
    {
        this.addSequential(new PrepareUltrasonicShot());
        //this.addSequential(new PunchGroupCommand());
    }
}
