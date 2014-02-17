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
public class SetOuterLightCommand extends Command
{
    private boolean value;
    public SetOuterLightCommand(boolean value)
    {
        this.requires(Robot.camera);
        this.value = value;
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Robot.camera.setOuter(value);
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
