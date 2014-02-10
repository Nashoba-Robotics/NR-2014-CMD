/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Pneumatics;

import edu.nr.main.commands.InfiniteCommand;
import edu.nr.main.commands.NamedRunnable;
import edu.nr.main.commands.OneRunCommand;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Pneumatics extends Subsystem
{
    
    private Relay relay;
    public Pneumatics()
    {
        relay = new Relay(1);
        relay.setDirection(Relay.Direction.kForward);
    }
    
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new InfiniteCommand(idleCompressor, this));
    }
    
    
    public NamedRunnable idleCompressor = new NamedRunnable("Idle Compressor")
    {
        public void run() 
        {
            //Nothing to do here
        }
    };
    
    public NamedRunnable startCompressor = new NamedRunnable("Start Compressor")
    {
        public void run() 
        {
            relay.set(Relay.Value.kOn);
            SmartDashboard.putString("Compressor", "On");
        }
    };
    
    public NamedRunnable stopCompressor = new NamedRunnable("Stop Compressor")
    {
        public void run()
        {
            relay.set(Relay.Value.kOff);
            SmartDashboard.putString("Compressor", "Off");
        }
    };
}
