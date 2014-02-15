

package edu.nr.main.subsystems.Drive;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem implements Printable
{
    private RobotDrive drive = null;
    private Gyro gyro;
    private Encoder e1, e2;
    public Drive()
    {
        drive = new RobotDrive(new Talon(1),new Talon(2),new Talon(3),new Talon(4));
        drive.setSafetyEnabled(false);

        gyro = new Gyro(1);
        e1 = new Encoder(RobotMap.ENCODER_1_A, RobotMap.ENCODER_1_B, false, CounterBase.EncodingType.k4X);
        e2 = new Encoder(RobotMap.ENCODER_2_A, RobotMap.ENCODER_2_B, false, CounterBase.EncodingType.k4X);
        
        e1.setDistancePerPulse(0.0349065850388889/12);
        e2.setDistancePerPulse(0.0349065850388889/12);
        startEncoders();
        
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveIdleCommand());
    }
    
    public void drive(double speed, double angle)
    {
        drive.arcadeDrive(speed, angle);
    }
    
    public void resetEncs()
    {
        e1.reset();
        e2.reset();
    }
    
    private void startEncoders()
    {
        e1.start();
        e2.start();
    }
    
    public double getGyroAngle()
    {
        return gyro.getAngle();
    }
    
    public double getAverageEncoderDistance()
    {
        return (e1.getDistance() + e2.getDistance())/2.0f;
    }
    
    public double getGyroRate()
    {
        return gyro.getRate();
    }
    
    public void resetGyro()
    {
        gyro.reset();
    }
    
    public double getRawEncoder(int which)
    {
        if(which == 1)
        {
            return e1.getDistance();
        }
        else if(which == 2)
        {
            return e2.getDistance();
        }
        else
        {
            throw new RuntimeException("Error: Invalid encoder value");
        }
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Drive", this);
        SmartDashboard.putData("Drive Idle Command", new DriveIdleCommand());
        SmartDashboard.putData("Drive Joystick Command", new DriveJoystickCommand());
    }
}

