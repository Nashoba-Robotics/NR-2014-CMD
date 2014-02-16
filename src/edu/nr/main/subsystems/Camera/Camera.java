/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Camera;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author colin
 */
public class Camera extends Subsystem
{
    DigitalOutput light1, light2;
    
    public Camera()
    {
        //light1 = new DigitalOutput()
    }
    
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new CameraIdle());
    }
    
}
