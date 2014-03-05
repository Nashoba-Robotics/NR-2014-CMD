/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor.OffBoard;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class ExtCompressorOn extends Command
{
    public ExtCompressorOn()
    {
        this.requires(Robot.extCompressor);
    }
    protected void initialize() {
    }

    protected void execute() 
    {
        Robot.extCompressor.startCompressor();
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