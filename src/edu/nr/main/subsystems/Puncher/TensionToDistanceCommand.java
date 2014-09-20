/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Puncher;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TensionToDistanceCommand extends Command
{
    public TensionToDistanceCommand()
    {
        this.requires(Puncher.getInstance());
    }
    
    protected void initialize() 
    {
        Puncher.getInstance().initCAN();
        Puncher.getInstance().setWinchLimit((float) SmartDashboard.getNumber("Tension Distance"));
    }

    protected void execute() 
    {
        Puncher.getInstance().setTentionerSpeed(Puncher.getInstance().TENSIONER_REGULAR_SPEED);
    }

    protected boolean isFinished() 
    {
        return !Puncher.getInstance().getForwardLimitOK();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
