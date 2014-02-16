/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class ShiftCommand extends Command
{
    private boolean direction;
    public ShiftCommand(boolean direction)
    {
        this.direction = direction;
    }
    protected void initialize() 
    {
    }

    protected void execute() 
    {
        if(direction)
            Robot.drive.setFirstGear();
        else
            Robot.drive.setSecondGear();
    }

    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
