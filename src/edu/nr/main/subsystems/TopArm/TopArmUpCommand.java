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
public class TopArmUpCommand extends Command
{
    public TopArmUpCommand()
    {
        super("Top Arm Up");
        this.requires(Robot.topArm);
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Robot.topArm.undeploy();
        //Robot.topArm.runTopArm(0);
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
