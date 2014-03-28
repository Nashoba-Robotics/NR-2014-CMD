/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.TopArm;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Robotics
 */
public class DelayedTopArmCommand extends Command
{
    private final int DELAY_TIME = 500;
    private boolean delayed;
    private long start;
    protected void initialize() 
    {
        if(Robot.topArm.isDeployed())
        {
            delayed = true;
            start = System.currentTimeMillis();
        }
        else
        {
            delayed = false;
        }
        Robot.topArm.undeploy();
    }

    protected void execute() 
    {
        
    }

    protected boolean isFinished() 
    {
        if(delayed)
        {
            return (System.currentTimeMillis() - start > DELAY_TIME);
        }
        else
        {
            return true;
        }
    }

    protected void end() 
    {
        
    }

    protected void interrupted() 
    {
        
    }
    
}
