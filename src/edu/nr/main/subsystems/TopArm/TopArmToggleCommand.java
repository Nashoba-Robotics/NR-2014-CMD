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
 * @author Robotics
 */
public class TopArmToggleCommand extends Command{

    protected void initialize() {
    }

    protected void execute() 
    {
        if(Robot.topArm.isDeployed())
        {
            Robot.topArm.undeploy();
        }
        else
        {
            Robot.topArm.deploy();
        }
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
