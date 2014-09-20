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
 * @author colin
 */
public class TopArmIdleCommand extends Command
{
    boolean startedCounting = false;
    long lastTime;
    public TopArmIdleCommand()
    {
        this.requires(TopArm.getInstance());
    }
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        /*if(TopArm.getInstance().isRunning && TopArm.getInstance().getIRSensor())
        {
            if(!startedCounting)
            {
                startedCounting = true;
                lastTime = System.currentTimeMillis();
            }
            else// if(((System.currentTimeMillis() - lastTime)/1000) > 1)
            {
                new DelayedStopIntakeCommand().start();
            }
        }
        else if(!TopArm.getInstance().getIRSensor())
        {
            startedCounting = false;
        }*/
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
