/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class AccelerationCalc 
{
    double lastTime = 0;
    double lastAccelX, lastAccelY;
    double initialVX = 0, initialVY;
    public void updateAcceleration()
    {
        double deltaT = System.currentTimeMillis() - lastTime;
        double vX = initialVX + (Robot.drive.getAccel(ADXL345_I2C.Axes.kX) * deltaT);
        double vY = initialVY + (Robot.drive.getAccel(ADXL345_I2C.Axes.kY) * deltaT);
        SmartDashboard.putNumber("X Velocity", vX);
        SmartDashboard.putNumber("Y ", vY);
        lastTime = System.currentTimeMillis();
    }
}
