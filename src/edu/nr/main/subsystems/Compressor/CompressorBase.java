/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor;

import edu.nr.main.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public abstract class CompressorBase extends Subsystem
{
    private static DigitalInput pressureLimit = new DigitalInput(RobotMap.PRESSURE_LIMIT);
    protected Relay compressorRelay;
    
    
    public static boolean getPressureLimitStatus()
    {
        return pressureLimit.get();
    }
    
    public void startCompressor()
    {
        compressorRelay.set(Relay.Value.kOn);
    }
    
    public void stopCompressor()
    {
        compressorRelay.set(Relay.Value.kOff);
    }
}
