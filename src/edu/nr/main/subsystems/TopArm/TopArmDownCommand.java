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
        this.requires(TopArm.getInstance());
    }
    protected void initialize() 
    {
        
    }

    protected void execute()
    {
        TopArm.getInstance().deploy();
        //TopArm.getInstance().runTopArm(1.0);
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
