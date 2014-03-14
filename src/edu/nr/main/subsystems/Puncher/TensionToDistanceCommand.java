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
public class TensionToDistanceCommand extends Command
{
    public TensionToDistanceCommand()
    {
        this.requires(Robot.puncher);
    }
    
    protected void initialize() 
    {
        Robot.puncher.initCAN();
        Robot.puncher.setWinchLimit((float) SmartDashboard.getNumber("Tension Distance"));
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
