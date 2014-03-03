/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main.subsystems.Camera;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class Camera extends Subsystem implements Printable
{
    DigitalOutput light1, light2;
    
    public Camera()
    {
        light1 = new DigitalOutput(RobotMap.CAMERA_LIGHT_1);
        light2 = new DigitalOutput(RobotMap.CAMERA_LIGHT_2);
    }
    
    protected void initDefaultCommand() 
    {
        this.setDefaultCommand(new CameraIdle());
    }
    
    public void turnAllOn()
    {
        light1.set(true);
        light2.set(true);
    }
    
    public void turnAllOff()
    {
        light1.set(false);
        light2.set(false);
    }
    
    public void setInner(boolean value)
    {
        light1.set(value);
    }
    
    public void setOuter(boolean value)
    {
        light2.set(value);
    }

    public void sendInfo() 
    {
        SmartDashboard.putData(new CameraOnCommand());
        SmartDashboard.putData(new CameraOffCommand());
        SmartDashboard.putData(new CameraPatternCommand());
    }
    
}