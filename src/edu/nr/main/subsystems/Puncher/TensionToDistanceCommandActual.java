/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class TensionToDistanceCommandActual extends Command
{
    private float dist;
    float start;
    public TensionToDistanceCommandActual(float distance)
    {
        this.requires(Robot.puncher);
        dist = distance;
    }
    protected void initialize() 
    {
        Robot.puncher.initCAN();
        Robot.puncher.setWinchLimit(dist);
        Robot.puncher.resetDogEar();
        start = System.currentTimeMillis();
    }

    protected void execute() 
    {
        Robot.puncher.setTentionerSpeed(Puncher.TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        if(System.currentTimeMillis() - start > 8000)
            return true;
        
        return !Robot.puncher.getForwardLimitOK();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
