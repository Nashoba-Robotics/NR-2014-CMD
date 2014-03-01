package edu.nr.main;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AccelerationCalc 
{
    private static double sample_x, sample_y, sample_z;
    private static char count_x, count_y;
    private static double[] acc_x = new double[2];
    private static double[] acc_y = new double[2];
    private static double[] acc_z = new double[2];
    private static double[] velocity_x = new double[2];
    private static double[] velocity_y = new double[2];
    private static long[] position_x = new long[2];
    private static long[] position_y = new long[2];
    private static long[] position_z = new long[2];
    private static char direction;
    private static long s_state_x, s_state_y;
    
    private static boolean hasInit = false;
    
    public static void run() {
        if(!hasInit) {
            calibrate();
            hasInit = true;
        }
        position();
    }
    
    private static void get_all_axes() {
        sample_x = Robot.drive.getAccel(ADXL345_I2C.Axes.kX);
        sample_y = Robot.drive.getAccel(ADXL345_I2C.Axes.kY);
        sample_z = Robot.drive.getAccel(ADXL345_I2C.Axes.kZ);
    }
    
    private static void calibrate() {
        int count = 0;
        
        do {
            get_all_axes();
            s_state_x += sample_x;
            s_state_y += sample_y;
            count++;
        } while(count != 1024);
        
        s_state_x = s_state_x >> 10;
        s_state_y = s_state_x >> 10;
    }
    
    private static void movement_end_check() {
        if (acc_x[1]==0) { 
            count_x++;
        }
        
        else { 
            count_x =0;
        }
        if (count_x>=25) {
            velocity_x[1]=0;
            velocity_x[0]=0;
        }

        //we count the number of acceleration samples that equals cero
        //if this number exceeds 25, we can assume that velocity is cero

        if (acc_y[1]==0) { 
            count_y++;
        }
        else { 
            count_y =0;
        }

        //we do the same for the Y axis

        if (count_y>=25) {
            velocity_y[1]=0;
            velocity_y[0]=0;
        }
    }
    
    private static void position() {
        char count2 = 0;
        
        do {
            get_all_axes();
            acc_x[1]=acc_x[1] + sample_x;
            acc_y[1]=acc_y[1] + sample_y;
            count2++;
        } while (count2 != 64);
        
        acc_x[1]= Double.longBitsToDouble(Double.doubleToLongBits(acc_x[1])>>6);
        acc_y[1]= Double.longBitsToDouble(
                                        Double.doubleToLongBits(acc_y[1]) >> 6);
        
        acc_x[1] = acc_x[1] - (int)s_state_x;
        acc_y[1] = acc_y[1] - (int)s_state_y;
        
        if ((acc_x[1] <=3) && (acc_x[1] >= -3)) {
            acc_x[1] = 0;
        }
        
        if ((acc_y[1] <=3)&&(acc_y[1] >= -3)) {
            acc_y[1] = 0;
        }
        
        velocity_x[1]= velocity_x[0]+ 
                       acc_x[0]+ 
                       Double.doubleToLongBits(Double.doubleToLongBits(
                                                    acc_x[1] -acc_x[0])>>1);
        
        position_x[1]= position_x[0] + 
                       Double.doubleToLongBits(velocity_x[0]) + 
                       Double.doubleToLongBits(Double.doubleToLongBits(
                                        velocity_x[1] - velocity_x[0])>>1);
        
        velocity_y[1] = velocity_y[0] + 
                        acc_y[0] + 
                        Double.doubleToLongBits(Double.doubleToLongBits(
                                    acc_y[1] -acc_y[0])>>1);
        
        position_y[1] = position_y[0] + 
                        Double.doubleToLongBits(velocity_y[0]) + 
                        Double.doubleToLongBits(Double.doubleToLongBits(
                                         velocity_y[1] - velocity_y[0])>>1);
        displayData();
        
        acc_x[0] = acc_x[1];
        acc_y[0] = acc_y[1];

        velocity_x[0] = velocity_x[1];
        velocity_y[0] = velocity_y[1];

        movement_end_check();
        
        position_x[0] = position_x[1];
        position_y[0] = position_y[1];

        
        direction = 0;
    }
    
    private static void displayData() {
        SmartDashboard.putNumber("X Position", position_x[1]);
        SmartDashboard.putNumber("Y Position", position_y[1]);
        
        SmartDashboard.putNumber("X Velocity", velocity_x[1]);
        SmartDashboard.putNumber("Y Velocity", velocity_y[1]);
        
        SmartDashboard.putNumber("X Acceleration", acc_x[1]);
        SmartDashboard.putNumber("Y Acceleration", acc_y[1]);
    }
}