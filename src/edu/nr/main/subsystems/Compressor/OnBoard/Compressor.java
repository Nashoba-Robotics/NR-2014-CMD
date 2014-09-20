/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor.OnBoard;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Compressor.CompressorBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Compressor extends CompressorBase
{
    private static Compressor singleton = null;
    public static Compressor getInstance()
    {
        if(singleton == null)
            singleton = new Compressor();
        
        return singleton;
    }
    
    private Compressor()
    {
        compressorRelay = new Relay(1);
        compressorRelay.setDirection(Relay.Direction.kForward);
    }
    
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new CompressorIdle());
    }
    
    public void sendInfo() 
    {
        SmartDashboard.putData("Compressor Start", new CompressorStart());
        SmartDashboard.putData("Compressor Stop", new CompressorStop());
        SmartDashboard.putData("Compressor", this);
    }
}
