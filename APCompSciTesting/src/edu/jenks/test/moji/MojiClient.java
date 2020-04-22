package edu.jenks.test.moji;

import java.io.File;
import java.util.Collection;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class MojiClient {
        public static void main(String[] args) throws Exception {
                // a list of students' source code files located in the prepared
                // directory.
                Collection<File> files = FileUtils.listFiles(new File(
                		"G:\\My Drive\\Software\\JavaPrograms\\F2019_CS\\Hashtable"), new String[] { "java" }, true);

                // a list of base files that was given to the students for this
                // assignment.
                Collection<File> baseFiles = FileUtils.listFiles(new File(
                		"G:\\My Drive\\Software\\JavaPrograms\\Basefiles\\Hashtable"), new String[] { "java" }, true);
                
                //get a new socket client to communicate with the MOSS server
                //and set its parameters.
                SocketClient socketClient = new SocketClient();
                
                //set your MOSS user ID
                // old - 463371048
                socketClient.setUserID("201019706");
                //socketClient.setOpt...
                
                //set the programming language of all student source codes
                socketClient.setLanguage("java");
                
                //initialize connection and send parameters
                socketClient.run();
                
                // upload all base files
                for (File f : baseFiles) {
                        socketClient.uploadBaseFile(f);
                }
                
                //upload all source files of students
                for (File f : files) {
                        socketClient.uploadFile(f);
                }
                
                //finished uploading, tell server to check files
                socketClient.sendQuery();
                
                //get URL with MOSS results and do something with it
                URL results = socketClient.getResultURL();
                System.out.println("Results available at " + results.toString());
        }
}