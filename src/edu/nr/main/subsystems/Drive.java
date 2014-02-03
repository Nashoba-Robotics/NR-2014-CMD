
package edu.nr.main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.nr.main.commands.DriveIdleCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class Drive extends Subsystem 
{
    private RobotDrive drive = null;
    private Gyro gyro;
    private Encoder e1, e2;
    public Drive()
    {
        try
        {
            drive = new RobotDrive(new CANJaguar(1),new CANJaguar(2),new CANJaguar(3),new CANJaguar(4));
        }
        catch(CANTimeoutException e)
        {
            throw new RuntimeException("Couldn't create robotdrive :(");
        }
        drive.setSafetyEnabled(false);

        gyro = new Gyro(1);
        e1 = new Encoder(1, 2, true, CounterBase.EncodingType.k4X);
        e2 = new Encoder(3,4, false, CounterBase.EncodingType.k4X);
        
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
        drive.drive(speed, -angle);
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
}

