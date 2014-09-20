/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor.OffBoard;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Compressor.CompressorBase;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class OffBoardCompressor extends CompressorBase
{
    private static OffBoardCompressor singleton = null;
    public static OffBoardCompressor getInstance()
    {
        if(singleton == null)
            singleton = new OffBoardCompressor();
        
        return singleton;
    }
    
    private OffBoardCompressor()
    {
        compressorRelay = new Relay(RobotMap.OFF_BOARD_COMPRESSOR_RELAY);
        compressorRelay.setDirection(Relay.Direction.kForward);
    }
    
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new ExtCompressorIdle());
    }

    public void sendInfo() 
    {
        SmartDashboard.putData(new ExtCompressorOn());
        SmartDashboard.putData(new ExtCompressorOff());
    }
}
