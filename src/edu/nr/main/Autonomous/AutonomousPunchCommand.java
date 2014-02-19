/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.Autonomous;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class AutonomousPunchCommand extends Command
{
    private int delay;
    private float startTime;
    private boolean done = false;
    public AutonomousPunchCommand(int delay)
    {
        this.delay = delay;
    }
    protected void initialize()
    {
        startTime = (System.currentTimeMillis() / 1000f);
    }

    protected void execute() 
    {
        if((System.currentTimeMillis()/1000f) - startTime > delay)
        {
            System.out.println("SHOOT THE BALL");
            done = true;
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
