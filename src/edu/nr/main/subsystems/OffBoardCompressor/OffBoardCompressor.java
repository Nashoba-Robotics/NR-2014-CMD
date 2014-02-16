/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.OffBoardCompressor;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class OffBoardCompressor extends Subsystem implements Printable
{
    private Relay relay;
    public OffBoardCompressor()
    {
        relay = new Relay(RobotMap.OFF_BOARD_COMPRESSOR_RELAY);
        relay.setDirection(Relay.Direction.kForward);
    }
    
    public void startCompressor()
    {
        relay.set(Relay.Value.kOn);
    }
    
    public void stopCompressor()
    {
        relay.set(Relay.Value.kOff);
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
