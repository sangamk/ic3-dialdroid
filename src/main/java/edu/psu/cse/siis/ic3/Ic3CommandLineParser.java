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

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.psu.cse.siis.coal.CommandLineParser;

/**
 * Command line parser for IC3.
 */
public class Ic3CommandLineParser extends CommandLineParser<Ic3CommandLineArguments> {
  private static final String COPYRIGHT =
      "Copyright (C) 2015 The Pennsylvania State University and the University of Wisconsin\n"
          + "Systems and Internet Infrastructure Security Laboratory\n";

  @Override
  protected void parseAnalysisSpecificArguments(Options options) {
     options.addOption(Option.builder("in")
     .desc("Path to the .apk of the application.").hasArg()
     .argName(".apk path").required().build());
     
     options.addOption(Option.builder("cp")
    	     .desc("Path to android platforms").hasArg()
    	     .argName("android platform path").required().build());
    	    
     options.addOption(Option.builder("db").desc("Store entry points to database.").hasArg()
        .optionalArg(true).argName("DB properties file").build());
    options.addOption(Option.builder("ssh").desc("Use SSH to connect to the database.").hasArg()
        .optionalArg(true).argName("SSH properties file").build());
    options.addOption(Option.builder("localport").desc("Local DB port to connect to.").hasArg()
        .type(Number.class).argName("local DB port").build());
     options.addOption(Option.builder("protobuf").desc("Destination path for the results.").hasArg()
        .argName("destination path").build());
    //options.addOption(Option.builder("sample").desc("Specify a sample name.").hasArg()
     //   .argName("sample name").build());
    options.addOption(Option.builder("dbhost").desc("DB host to connect to.").hasArg()
        .type(Number.class).argName("DB host").build());
    
    options.addOption(Option.builder("category").desc("Category of the application").hasArg()
            .type(Number.class).argName("App Catgorypp").build());
        
    
    options.addOption(Option.builder("dbname").desc("DB name.").hasArg()
            .type(Number.class).argName("DB name").build());
        
    options.addOption("computecomponents", false,
        "Compute which components each exit point belongs to.");
    options.addOption("binary", false, "Output a binary protobuf.");
  }

  @Override
  protected void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    System.out.println(COPYRIGHT);
    formatter.printHelp("ic3 -input <Android directory> -cp <classpath> "
        + "[-computecomponents] "
        + "[-db <path to DB properties file>] [-ssh <path to SSH properties file>] "
        + "[-localport <DB local port>] [-modeledtypesonly] [-output <output directory>] "
         + "[-dbhost DB host name/IP] [-dbname DB name]"
        + "[-threadcount <thread count>] [-category App Category]", options);
  }
}
