/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Camera;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class SetInnerLightCommand extends Command
{
    private boolean value;
    public SetInnerLightCommand(boolean value)
    {
        this.requires(Robot.camera);
        this.value = value;
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Robot.camera.setInner(value);
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
