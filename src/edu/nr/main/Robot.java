/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.nr.main;


import edu.nr.main.oi.OI;
import edu.nr.main.subsystems.BottomRollers.BottomRollers;
import edu.nr.main.subsystems.Camera.Camera;
import edu.nr.main.subsystems.Compressor.Compressor;
import edu.nr.main.subsystems.Drive.Drive;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.nr.main.subsystems.Flower.Flower;
import edu.nr.main.subsystems.OffBoardCompressor.OffBoardCompressor;
import edu.nr.main.subsystems.Puncher.Puncher;
import edu.nr.main.subsystems.ShooterRotator.ShooterRotator;
import edu.nr.main.subsystems.TopArm.TopArm;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//10.17.68.15:3200

public class Robot extends IterativeRobot 
{
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public static Drive drive = null;
    public static BottomRollers rollers = null;
    public static Flower flower = null;
    public static Puncher puncher = null;
    public static ShooterRotator shooterRotator = null;
    public static Compressor compressor;
    public static TopArm topArm;
    public static OffBoardCompressor extCompressor;
    public static Camera camera;
    
    static SocketConnection pieConnection;
    static InputStream pieInput;
    
    boolean connectedToPie = false;
    boolean sensorsStarted =false;
    
    public void robotInit() 
    {
        System.out.println("ROBOT STARTED");
        drive = new Drive();
        compressor = new Compressor();
        rollers = new BottomRollers();
        puncher = new Puncher();
        shooterRotator = new ShooterRotator();
        topArm = new TopArm();
        flower = new Flower();
        extCompressor = new OffBoardCompressor();
        camera = new Camera();
        
        SmartDashboard.putNumber("Tensioner Speed", 0);
        SmartDashboard.putData(rollers);
        
        topArm.sendInfo();
        puncher.sendInfo();
        drive.sendInfo();
        rollers.sendInfo();
        shooterRotator.sendInfo();
        compressor.sendInfo();
        extCompressor.sendInfo();
        camera.sendInfo();
        
        new Thread(new Runnable()
        {
            public void run() 
            {
                try
                {
                    pieConnection = (SocketConnection) Connector.open("socket://10.17.68.12:8888");
                    pieInput = pieConnection.openInputStream();
                    connectedToPie = true;
                }
                catch(IOException e)
                {
                    System.err.println("ERROR: couldn't connect to pie");
                }
            }
        }).start();
        
        new Thread(new Runnable()
        {
            public void run() 
            {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Robot.drive.initGyroAccel();
                System.out.println("SENSORS STARTED");
                sensorsStarted = true;
            }
        }).start();
        
        System.out.println("ROBOT FINISHED STARTING");
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
        /*SmartDashboard.putData("DriveDistanceCommand", new DriveDistanceCommand(10, 0.8f));
        SmartDashboard.putData("DriveAngleCommand", new DriveAngleCommand(90, 0.6));*/
        OI.init();
        System.out.println("TELEOP STARTED");
    }

    /**
     * This function is called periodically during operator control
     */
    
    public void teleopPeriodic() 
    {
        if(sensorsStarted)
        {
            Scheduler.getInstance().run();
            SmartDashboard.putNumber("Potentiometer",Robot.shooterRotator.getRotation());
            SmartDashboard.putNumber("Linear Encoder", Robot.puncher.getLinearEncoderDistance());
            SmartDashboard.putNumber("Encoder 1", Robot.drive.getRawEncoder(1));
            SmartDashboard.putNumber("Encoder 2", Robot.drive.getRawEncoder(2));
            SmartDashboard.putNumber("Gyro", Robot.drive.getGyroAngle());
            SmartDashboard.putNumber("Accel y", Robot.drive.getAccel(ADXL345_I2C.Axes.kY));
            SmartDashboard.putNumber("Accel x", Robot.drive.getAccel(ADXL345_I2C.Axes.kX));
            SmartDashboard.putNumber("Accel z", Robot.drive.getAccel(ADXL345_I2C.Axes.kZ));
        
            if(connectedToPie)
                listenForPieInput();
        }
    }
    
    Vector pieBytes = new Vector();
    boolean startedReading = false;
    void listenForPieInput()
    {
        try 
        {
            while(pieInput.available() > 0)
            {
                byte input = (byte)pieInput.read();
                if(!startedReading)
                {
                    if(input == 10)
                    {
                        startedReading = true;
                    }
                }
                else
                {
                    if(input == 0)
                    {
                        startedReading = false;
                        byte[] bytes = new byte[pieBytes.size()];
                        for(int i = 0; i < bytes.length; i++)
                        {
                            bytes[i] = ((Byte)pieBytes.elementAt(i)).byteValue();
                        }
                        String message = new String(bytes);
                        System.out.println(message);
                        pieBytes.removeAllElements();
                    }
                    else
                    {
                        pieBytes.addElement(Byte.valueOf(input));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
