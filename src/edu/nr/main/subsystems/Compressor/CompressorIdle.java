/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor;

import edu.nr.main.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class CompressorIdle extends Command
{
    public CompressorIdle()
    {
        super("Compressor Idle");
        this.requires(Robot.compressor);
    }
    protected void initialize() 
    {
        
    }

    protected void execute() 
    {
        if(Robot.compressor.getPressureSensor())
        {
            Robot.compressor.stopCompressor();
        }
        else
        {
            //Uncomment if we want automatic pressurizing
            //Robot.compressor.startCompressor();
        }
        
        SmartDashboard.putBoolean("Pressure Limit", Robot.compressor.getPressureSensor());
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end() 
    {
        
    }

    protected void interrupted() {
    }
    
}
