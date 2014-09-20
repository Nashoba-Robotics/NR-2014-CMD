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
        this.requires(TopArm.getInstance());
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        TopArm.getInstance().undeploy();
        //TopArm.getInstance().runTopArm(0);
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
