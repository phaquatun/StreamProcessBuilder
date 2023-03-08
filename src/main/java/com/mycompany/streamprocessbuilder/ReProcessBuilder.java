package com.mycompany.streamprocessbuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ReProcessBuilder {

    private ProcessBuilder pb;// = new ProcessBuilder("cmd.exe", "/C", "w32tm/resync");
    private Runtime run;//= Runtime.getRuntime();
    private Process pc;
    private String line;

    public ReProcessBuilder runSctips(String pathDirec, String[] arrStr, StreamResultProcess process) {
        try {
            pb = new ProcessBuilder(arrStr);
            pb.redirectErrorStream(true);
            pb.directory(new File(pathDirec));
            pc = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(pc.getInputStream()));

            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            int valueExist = pc.exitValue();
            process.getResult(sb.toString(), valueExist);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return this;
    }

    public ReProcessBuilder runScrips(String strScrips, StreamResultProcess process) {
        try {
            pb = new ProcessBuilder("cmd.exe", "/C", strScrips);
            pb.redirectErrorStream(true);
            pc = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(pc.getInputStream()));

            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            int valueExist = pc.exitValue();
            process.getResult(sb.toString(), valueExist);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void endScrips() {
        try {
            pc.waitFor();
            pc.destroy();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
