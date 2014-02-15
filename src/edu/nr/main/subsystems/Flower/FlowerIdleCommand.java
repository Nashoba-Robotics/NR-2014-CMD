/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Flower;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class FlowerIdleCommand extends Command
{
    public FlowerIdleCommand()
    {
        super("Flower Idle Command");
        this.requires(Robot.flower);
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        //Nothing to do here
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
