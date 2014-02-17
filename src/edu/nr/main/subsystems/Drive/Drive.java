

package edu.nr.main.subsystems.Drive;

import edu.nr.main.RobotMap;
import edu.nr.main.subsystems.Printable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.DataFormat_Range;

public class Drive extends Subsystem implements Printable
{
    private ADXL345_I2C accel;
    private RobotDrive drive = null;
    private Gyro gyro;
    private Encoder e1, e2;
    private DoubleSolenoid shifter;
    public Drive()
    {
        drive = new RobotDrive(new Talon(1),new Talon(2),new Talon(3),new Talon(4));
        drive.setSafetyEnabled(false);

        
        e1 = new Encoder(RobotMap.ENCODER_1_A, RobotMap.ENCODER_1_B, false, CounterBase.EncodingType.k4X);
        e2 = new Encoder(RobotMap.ENCODER_2_A, RobotMap.ENCODER_2_B, false, CounterBase.EncodingType.k4X);
        
        e1.setDistancePerPulse(0.0349065850388889/12);
        e2.setDistancePerPulse(0.0349065850388889/12);
        startEncoders();
        
        gyro = new Gyro(RobotMap.GYRO);
        
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        
        shifter = new DoubleSolenoid(RobotMap.SHIFTER_ENGAGE, RobotMap.SHIFTER_DISENGAGE);
    }
    
    public void initGyroAccel()
    {
        accel = new ADXL345_I2C(1, DataFormat_Range.k2G);
        
        gyro.reset();
    }
    
    public double getAccel(ADXL345_I2C.Axes axis)
    {
        return accel.getAcceleration(axis);
    }
    
    public void setFirstGear()
    {
        shifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void setSecondGear()
    {
        shifter.set(DoubleSolenoid.Value.kReverse);
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
        SmartDashboard.putData("Shift First Gear", new ShiftCommand(true));
        SmartDashboard.putData("Shift Second Gear", new ShiftCommand(false));
        SmartDashboard.putData("Drive Distance", new DriveDistanceCommand(10f, 0.6f));
    }
}
