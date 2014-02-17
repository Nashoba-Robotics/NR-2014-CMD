/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Compressor;

import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Compressor extends Subsystem implements Printable
{
    private Relay relay;
    DigitalInput limit;
    
    public Compressor()
    {
        relay = new Relay(1);
        relay.setDirection(Relay.Direction.kForward);
        
        limit = new DigitalInput(3);
    }
    
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new CompressorIdle());
    }
    
    public void startCompressor()
    {
        relay.set(Relay.Value.kOn);
    }
    
    public void stopCompressor()
    {
        relay.set(Relay.Value.kOff);
    }
    
    public boolean getPressureSensor()
    {
        return limit.get();
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Compressor Start", new CompressorStart());
        SmartDashboard.putData("Compressor Stop", new CompressorStop());
        SmartDashboard.putData("Compressor", this);
    }
}
