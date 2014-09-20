

package edu.nr.main.subsystems.Drive;

import edu.nr.main.RobotMap;
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
import edu.wpi.first.wpilibj.Ultrasonic;

public class Drive extends Subsystem
{
    private static Drive singleton;
    public static Drive getInstance()
    {
        if(singleton == null)
            singleton = new Drive();
        
        return singleton;
    }
    
    private ADXL345_I2C accel;
    private RobotDrive drive = null;
    private Gyro gyro;
    private Encoder e1, e2;
    private DoubleSolenoid shifter;
    Ultrasonic sonic;
    
    private Drive()
    {
        drive = new RobotDrive(new Talon(1),new Talon(2),new Talon(3),new Talon(4));
        drive.setSafetyEnabled(false);

        
        e1 = new Encoder(RobotMap.ENCODER_1_A, RobotMap.ENCODER_1_B, false, CounterBase.EncodingType.k4X);
        e2 = new Encoder(RobotMap.ENCODER_2_A, RobotMap.ENCODER_2_B, false, CounterBase.EncodingType.k4X);
        
        //A calculated constant to convert from encoder ticks to feet on 4 inch diameter wheels
        e1.setDistancePerPulse(0.0349065850388889/12);
        e2.setDistancePerPulse(0.0349065850388889/12);
        startEncoders();
        
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);      
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        
        sonic = new Ultrasonic(RobotMap.ULTRASONIC_A, RobotMap.ULTRASONIC_B);
        sonic.setAutomaticMode(true);
        sonic.setEnabled(true);
        
        
        shifter = new DoubleSolenoid(RobotMap.SHIFTER_ENGAGE, RobotMap.SHIFTER_DISENGAGE);
    }
    
    public double getUltrasonicFeet()
    {
        return (sonic.getRangeInches() / 12d);
    }
    
    public void initGyroAccel()
    {
        accel = new ADXL345_I2C(1, DataFormat_Range.k2G);
        
        gyro = new Gyro(RobotMap.GYRO);
    }
    
    public double getAccel(ADXL345_I2C.Axes axis)
    {
        return accel.getAcceleration(axis);
    }
    
    public void setFirstGear()
    {
        shifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void setSecondGear()
    {
        shifter.set(DoubleSolenoid.Value.kForward);
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveJoystickCommand());
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
        if(gyro != null)
            return gyro.getAngle();
        return 0;
    }
    
    //At time of writing, encoder 2 is broken, so just use encoder 1
    public double getAverageEncoderDistance()
    {
        return e1.getDistance();
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
            System.err.println("Warning: class Drive was asked for raw encoder '" + which + "' when there is no such encoder");
            return 0;
        }
    }
    
    //Rate in ft/sec
    public double getEncoderRate()
    {
        return (e1.getRate() + e2.getRate())/2;
    }

    public void sendInfo() 
    {
        SmartDashboard.putData("Drive", this);
        SmartDashboard.putData("Drive Idle Command", new DriveIdleCommand());
        SmartDashboard.putData("Drive Joystick Command", new DriveJoystickCommand());
        SmartDashboard.putData("Shift First Gear", new ShiftCommand(true));
        SmartDashboard.putData("Shift Second Gear", new ShiftCommand(false));
        SmartDashboard.putData("Drive Distance Command", new DriveDistanceCommand(8f, .6f));
        SmartDashboard.putData("Drive to Ultrasonic Command", new DriveToUltrasonicDistance(5f));
        SmartDashboard.putData("Reset Encoders", new ResetEncs());
    }
}

