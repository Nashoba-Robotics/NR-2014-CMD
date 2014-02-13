/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Flower;

import edu.nr.main.commands.InfiniteCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class Flower extends Subsystem
{
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new InfiniteCommand(null, this));
    }
}
