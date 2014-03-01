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
    private double sample_x, sample_y, sample_z;
    private char count_x, count_y;
    private int[] acc_x = new int[2];
    private int[] acc_y = new int[2];
    private int[] acc_z = new int[2];
    private int[] velocity_x = new int[2];
    private int[] velocity_y = new int[2];
    private long[] position_x = new long[2];
    private long[] position_y = new long[2];
    private long[] position_z = new long[2];
    private char direction;
    private long s_state_x, s_state_y;
    
    public void run() {
        
    }
    
    private void calibrate() {
        int count = 0;
        
        do {
            sample_x = Robot.drive.getAccel(ADXL345_I2C.Axes.kX);
            sample_y = Robot.drive.getAccel(ADXL345_I2C.Axes.kY);
            sample_z = Robot.drive.getAccel(ADXL345_I2C.Axes.kZ);
            s_state_x += sample_x;
            s_state_y += sample_y;
            count++;
        } while(count != 1024);
        
        s_state_x = s_state_x >> 10;
        s_state_y = s_state_x >> 10;
    }
    
  
}
