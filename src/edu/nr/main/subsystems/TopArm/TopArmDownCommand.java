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
public class TopArmDownCommand extends Command
{
    public TopArmDownCommand()
    {
        super("Top Arm Down");
        this.requires(Robot.topArm);
    }
    protected void initialize() 
    {
        
    }

    protected void execute()
    {
        Robot.topArm.deploy();
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
