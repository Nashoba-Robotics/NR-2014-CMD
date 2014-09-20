/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.TopArm;

import edu.nr.main.Robot;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class DelayedStopIntakeCommand extends Command
{
    int init;
    boolean done = false;
    protected void initialize() 
    {
        init = (int)(System.currentTimeMillis()/1000f);
    }

    protected void execute() 
    {
        int current = (int)(System.currentTimeMillis()/1000f);
        if(current - init > 0.5)
        {
            done = true;
            BottomRollers.getInstance().stopRoll();
            TopArm.getInstance().runTopArm(0);
        }
    }

    protected boolean isFinished() 
    {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
