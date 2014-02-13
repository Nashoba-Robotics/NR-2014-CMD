/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author colin
 */
public class CompressorStart extends Command
{
    public CompressorStart()
    {
        super("Start");
        this.requires(Robot.compressor);
    }
    protected void initialize() {
    }

    protected void execute()
    {
        Robot.compressor.startCompressor();
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
