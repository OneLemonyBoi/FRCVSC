// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
  public LimeLight() {

  }

  public Double[] getData() {
    /*
      tv - Valid Targets (0 or 1)
      tx - Horizontal Offset From Center To Target (-27 - 27)
      ty - Vertical Offset From Center To Target (-20.5 - 20.5)
      ta - Target Area (0% - 100%)
      ts - Skew or rotation (-90 degrees - 0 degrees)
      tl - The pipeline’s ms delay
    */
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    return new Double[]{
      table.getEntry("tv").getDouble(0), 
      table.getEntry("tx").getDouble(0), 
      table.getEntry("ty").getDouble(0), 
      table.getEntry("ta").getDouble(0), 
      table.getEntry("ts").getDouble(0), 
      table.getEntry("tl").getDouble(0)
    };
  }

  public static NetworkTableEntry get(String string) {return NetworkTableInstance.getDefault().getTable("limelight").getEntry(string);}

  public static void set(Entry entry, Integer number) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry(entry.id).setNumber(number);
  }

  enum Entry {
    LEDMODE("ledMode"), CAMMODE("camMode"), PIPELINE("pipeline"), STREAM("stream"), SNAPSHOT("snapshot");

    public final String id;

    Entry(String id) {
      this.id = id;
    }
  }
}