/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class TensionToDistanceCommandActual extends Command
{
    private float dist;
    public TensionToDistanceCommandActual(float distance)
    {
        dist = distance;
    }
    protected void initialize() 
    {
        Robot.puncher.setWinchLimit(dist);
        Robot.puncher.resetDogEar();
    }

    protected void execute() 
    {
        Robot.puncher.setTentionerSpeed(Robot.puncher.TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        return !Robot.puncher.getForwardLimitOK();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
