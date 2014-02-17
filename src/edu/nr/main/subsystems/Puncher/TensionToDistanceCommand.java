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
public class TensionToDistanceCommand extends Command
{

    private double target = 1;
    protected void initialize() 
    {
        Robot.puncher.setWinchLimit((float)target);
    }

    protected void execute() 
    {
        Robot.puncher.setTentionerSpeed(Robot.puncher.TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        /*double diff = target - Robot.puncher.getLinearEncoderDistance();
        return diff <=0f;*/
        return Math.abs(Robot.puncher.getWinchVoltage()) > 0.1;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
