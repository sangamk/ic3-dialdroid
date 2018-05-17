/*
 * Copyright (C) 2015 The Pennsylvania State University and the University of Wisconsin
 * Systems and Internet Infrastructure Security Laboratory
 *
 * Author: Damien Octeau
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.psu.cse.siis.ic3;

import org.apache.commons.cli.ParseException;

import edu.psu.cse.siis.coal.CommandLineArguments;
import edu.psu.cse.siis.ic3.db.Table;

/**
 * Command line arguments for IC3.
 */
public class Ic3CommandLineArguments extends CommandLineArguments {
  private static final String DEFAULT_SSH_PROPERTIES_PATH = "/db/ssh.properties";
  private static final String DEFAULT_DATABASE_PROPERTIES_PATH = "cc.properties";
  private static final int DEFAULT_LOCAL_PORT = 3306;
  private static final String DEFAULT_COMPILED_MODEL_PATH = "/res/icc.cmodel";
  private static final String DEFAULT_DB_NAME = "dialdroid";
  private static final String DEFAULT_DB_HOST_NAME = "localhost";

  private String manifest;
  private String db;
  private String ssh;
  private String iccStudy;
  private int dbLocalPort = DEFAULT_LOCAL_PORT;
  private boolean computeComponents = true;
  private String dbName;
  private String protobufDestination;
  private boolean binary;
  private String sample;
  private String appCategory="Default";
  private String dbHostName;

  public String getDbName() {
    return dbName != null ? dbName : DEFAULT_DB_NAME;
  }

  /**
   * Gets the path to the manifest or .apk file.
   *
   * @return The path to the manifest or .apk file.
   */
  public String getManifest() {
    return manifest;
  }

  /**
   * Gets the path to the database properties file.
   *
   * @return The path to the database properties file if IC3 should output its results to a
   *         database, null otherwise.
   */
  public String getDb() {
    if (db == null) {
      return DEFAULT_DATABASE_PROPERTIES_PATH;
    }
    return db;
  }

  /**
   * Gets the path to the SSH properties file.
   *
   * @return The path to the SSH properties file if an SSH connection is requested, null otherwise.
   */
  public String getSsh() {
    return ssh;
  }

  public String getIccStudy() {
    return iccStudy;
  }

  /**
   * Gets the local port to which the database connection should be done.
   *
   * @return The local port to connect to.
   */
  public int getDbLocalPort() {
    return dbLocalPort;
  }

  /**
   * Determines if mappings between ICC-sending locations and the components that contain them
   * should be computed.
   *
   * @return True if the components that contain ICC-sending locations should be determined.
   */
  public boolean computeComponents() {
    return computeComponents;
  }

  /**
   * Returns the destination protocol buffer file path.
   *
   * @return The destination path if any, otherwise null.
   */
  public String getProtobufDestination() {
    return protobufDestination;
  }

  /**
   * Determines if the output should be binary, in the case of a protobuf output.
   *
   * @return True if the output should be binary.
   */
  public boolean binary() {
    return binary;
  }

  /**
   * Returns the name of the sample.
   *
   * @return The sample name, if any, otherwise null.
   */
  public String getSample() {
    return sample;
  }

  
  /**
   * Returns the name of the app category.
   *
   * @return The app category name, if any
   */
  public String getAppCategory() {
    return appCategory;
  }

  
  /**
   * Process the command line arguments after initial parsing. This should be called be actually
   * using the arguments contained in this class.
   */
  public void processCommandLineArguments() {
    manifest = getOptionValue("in");

    if (getCompiledModel() == null && getModel() == null) {
      setCompiledModel(DEFAULT_COMPILED_MODEL_PATH);
    }

    iccStudy = getOptionValue("iccstudy");

    if (hasOption("db")) {
      db = getOptionValue("db", DEFAULT_DATABASE_PROPERTIES_PATH);
    }
    
    if (hasOption("dbhost")) {
        this.dbHostName = getOptionValue("dbhost", DEFAULT_DB_HOST_NAME);
        Table.setDBHost(this.dbHostName);
    }

    if (hasOption("cp")) {
      this.setAndroidJar(getOptionValue("cp"));
    }

    if (hasOption("ssh")) {
      ssh = getOptionValue("ssh", DEFAULT_SSH_PROPERTIES_PATH);
    }

    if (hasOption("localport")) {
      try {
        dbLocalPort = ((Number) getParsedOptionValue("localport")).intValue();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    if (hasOption("dbname")) {
      dbName = getOptionValue("dbname", DEFAULT_DB_NAME);
    }

    if (hasOption("category")) {
      appCategory = getOptionValue("category","Default");
    }
    
    if (hasOption("protobuf")) {
        protobufDestination = getOptionValue("protobuf");
      }

     computeComponents = hasOption("computecomponents") || db != null || protobufDestination !=  null;

     binary = hasOption("binary");

    // sample = getOptionValue("sample");
  }
}
