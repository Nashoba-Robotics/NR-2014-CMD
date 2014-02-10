/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Pneumatics;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class SolenoidForwardCommand extends Command
{
    public SolenoidForwardCommand()
    {
        this.requires(Robot.solenoidSys);
    }
    
    protected void initialize() {
    }

    protected void execute() 
    {
        Robot.solenoidSys.set(DoubleSolenoid.Value.kForward);
    }

    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
