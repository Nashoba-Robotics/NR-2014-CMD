/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems;

import edu.nr.main.subsystems.Pneumatics.SolenoidIdleCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class SolenoidSys extends Subsystem 
{
    DoubleSolenoid sol;
    public SolenoidSys()
    {
        sol = new DoubleSolenoid(2,1,2);
    }
    protected void initDefaultCommand() 
    {
        setDefaultCommand(new SolenoidIdleCommand());
    }
    
    public void set(DoubleSolenoid.Value value)
    {
        sol.set(value);
    }
}
