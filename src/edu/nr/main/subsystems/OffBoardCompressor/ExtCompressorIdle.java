/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.OffBoardCompressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class ExtCompressorIdle extends Command
{
    public ExtCompressorIdle()
    {
        this.requires(Robot.extCompressor);
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        if(Robot.compressor.getPressureSensor())
        {
            Robot.extCompressor.stopCompressor();
        }
        else
        {
            //Nothin to do here
        }
    }

    protected boolean isFinished() 
    {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
