/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;


import edu.nr.main.Autonomous.AutonomousCommand;
import edu.nr.main.oi.OI;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.CancelAllCommand;
import edu.nr.main.subsystems.Compressor.OnBoard.Compressor;
import edu.nr.main.subsystems.Drive.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.nr.main.subsystems.Compressor.OffBoard.OffBoardCompressor;
import edu.nr.main.subsystems.Drive.DriveJoystickCommand;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArm;
import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{
    private boolean sensorsStarted = false;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {
        System.out.println("ROBOT STARTED");
        SmartDashboard.putBoolean("Auto Compressor", true);
        SmartDashboard.putNumber("Tension Distance", 0);
        SmartDashboard.putNumber("Drive Distance", 10);
        
        SmartDashboard.putNumber("Tensioner Speed", 0);
        SmartDashboard.putData(new CancelAllCommand());
        
        //Sends a bunch of commands and objects to smartdashboard related to each subsystem
        TopArm.getInstance().sendInfo();
        Puncher.getInstance().sendInfo();
        Drive.getInstance().sendInfo();
        BottomRollers.getInstance().sendInfo();
        ShooterRotator.getInstance().sendInfo();
        Compressor.getInstance().sendInfo();
        OffBoardCompressor.getInstance().sendInfo();
        
        OI.init();
        
        //The gyroscope needs a delay before it is initialized (something to do with the hardware's initialization)
        new Thread(new Runnable()
        {
            public void run() 
            {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
                //Initialize the gyro and accel
                Drive.getInstance().initGyroAccel();
                sensorsStarted = true;
                System.out.println("SENSORS STARTED");
            }
        }).start();
        
        System.out.println("ROBOT FINISHED STARTING");
    }
    
    AutonomousCommand auton;
    /**
     * This function is called automatically by the VM when the field systems tell our robot that autonomous has begun
     */
    public void autonomousInit()
    {
        //Start running the autonomous command when 
        auton = new AutonomousCommand();
        auton.start();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        //The scheduler is what runs our commands; this must be called during the autonomous loop for our commands to run.
        Scheduler.getInstance().run();
        
        //Update some values in smart dashboard during autonomous for debugging purposes
        SmartDashboard.putNumber("Encoder 1", Drive.getInstance().getRawEncoder(1));
        SmartDashboard.putNumber("Encoder 2", Drive.getInstance().getRawEncoder(2));
        SmartDashboard.putNumber("Potentiometer",ShooterRotator.getInstance().getRotation());
    }
    
    //This function is called automatically by the VM when the robot is disabled by either Driver Station or the field
    public void disabledInit()
    {
        if(auton != null && auton.isRunning())
            auton.cancel();
    }
    
    //This function is called automatically by the VM when teleop is starting
    public void teleopInit() 
    {
        //Cancel the autonomous commmand if it is still running
        if(auton != null)
            auton.cancel();
        
        //Resets the punching release mechanism so the springs are ready to be tentioned
        Puncher.getInstance().resetDogEar();
        
        System.out.println("TELEOP STARTED");
        
        //The default command for our drive subsystem is to do nothing (DriveIdleCommand),
        //but since we are in teleop now, we want to start controlling the robot with our joysticks
        new DriveJoystickCommand().start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
        if(sensorsStarted)
        {
            //Scheduler must be run so that all of our commands are run in the teleop loop
            Scheduler.getInstance().run();
            
            SmartDashboard.putNumber("Linear Encoder", Puncher.getInstance().getLinearEncoderDistance());
            SmartDashboard.putNumber("Encoder 1", Drive.getInstance().getRawEncoder(1));
            SmartDashboard.putNumber("Encoder 2", Drive.getInstance().getRawEncoder(2));
            SmartDashboard.putNumber("Gyro", Drive.getInstance().getGyroAngle());
            SmartDashboard.putNumber("Accel y", Drive.getInstance().getAccel(ADXL345_I2C.Axes.kY));
            SmartDashboard.putNumber("Accel x", Drive.getInstance().getAccel(ADXL345_I2C.Axes.kX));
            SmartDashboard.putNumber("Accel z", Drive.getInstance().getAccel(ADXL345_I2C.Axes.kZ));
            SmartDashboard.putNumber("Ultrasonic Sensor (feet)", Drive.getInstance().getUltrasonicFeet());
            SmartDashboard.putNumber("Potentiometer",ShooterRotator.getInstance().getRotation());
            SmartDashboard.putBoolean("Tension Limit Condition", Puncher.getInstance().getForwardLimitOK());
        }
    }
    
    //Our subsystems use this in order to report any exceptions when trying to communicate with the jaguars
    public static void reportCANException(Exception e, String speedControllerName)
    {
        System.err.println(e.getClass().getName() + " on speed controller: " + speedControllerName);
    }
}
