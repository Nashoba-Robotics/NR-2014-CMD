/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Drive;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for shifting gears on the drive train
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
            Drive.getInstance().setFirstGear();
        else
            Drive.getInstance().setSecondGear();
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
