/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author Robotics
 */
public class CancelAllCommand extends Command
{

    public CancelAllCommand() {
        this.requires(Robot.drive);
        this.requires(Robot.rollers);
        this.requires(Robot.topArm);
        this.requires(Robot.shooterRotator);
        this.requires(Robot.puncher);
    }
     
    protected void initialize() 
    {
    }

    protected void execute() {
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
