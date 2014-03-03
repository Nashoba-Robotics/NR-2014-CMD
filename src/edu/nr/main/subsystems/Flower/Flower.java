/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Flower;

import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Flower extends Subsystem implements Printable
{
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new FlowerIdleCommand());
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Flower", this);
        SmartDashboard.putData("Flower Idle Command", new FlowerIdleCommand());
    }
}
