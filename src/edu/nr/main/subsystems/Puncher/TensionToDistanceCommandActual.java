/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;

public class TensionToDistanceCommandActual extends Command
{
    private float dist;
    float start;
    public TensionToDistanceCommandActual(float distance)
    {
        this.requires(Puncher.getInstance());
        dist = distance;
    }
    protected void initialize() 
    {
        Puncher.getInstance().initCAN();
        Puncher.getInstance().setWinchLimit(dist);
        Puncher.getInstance().resetDogEar();
        start = System.currentTimeMillis();
    }

    protected void execute() 
    {
        Puncher.getInstance().setTentionerSpeed(Puncher.TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        if(System.currentTimeMillis() - start > 8000)
            return true;
        
        return !Puncher.getInstance().getForwardLimitOK();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
