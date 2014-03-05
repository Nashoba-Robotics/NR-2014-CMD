/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nr.main;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author colin
 */
public class FieldCentric 
{
    private static double x = 0, y = 0, lastEncoderDistance = 0;
    
    private static long lastUpdateTime;
    private static NetworkTable table;
    private static boolean initialized = false;
    
    public static void update()
    {
        if(!initialized)
        {
            initialized = true;
            table = NetworkTable.getTable("FieldCentric");
            lastUpdateTime = System.currentTimeMillis();
        }
        else if(System.currentTimeMillis() - lastUpdateTime > 200)
        {
            System.err.println("ERROR: FieldCentric not being called often enough: (" + ((System.currentTimeMillis() - lastUpdateTime)/1000f) + "s)");
        }
        
        double angle = Robot.drive.getGyroAngle();
        angle *= (Math.PI / 180); //Convert to radians
        
        double ave = Robot.drive.getAverageEncoderDistance();// * (34d/33d);
        double delta_x_r = (ave-lastEncoderDistance);
        double deltax = delta_x_r * Math.cos(-angle);
        double deltay = delta_x_r * Math.sin(-angle);
        x += deltax;
        y += deltay;
        
        lastEncoderDistance = ave;
        
        SmartDashboard.putNumber("Location x", x);
        SmartDashboard.putNumber("Location y", y);
        table.putNumber("x", x);
        table.putNumber("y", y);
        table.putNumber("angle", angle);
    }
}
