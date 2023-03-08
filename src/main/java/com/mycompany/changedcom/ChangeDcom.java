package com.mycompany.changedcom;

import com.mycompany.streamprocessbuilder.ReProcessBuilder;
import com.mycompany.streamprocessbuilder.StreamResultProcess;

public class ChangeDcom {

    private String namePort;
    private String result;
    private int valueExist;
    private boolean isCon;

    public ChangeDcom() {
    }

    public ChangeDcom(String namePort) {
        this.namePort = namePort;
    }

    public ChangeDcom start() {
        isCon = true;
        return this;
    }

    public ChangeDcom connectDcom(String namePort) {
        String strScrips = namePort.contains("-wf") ? "netsh interface set interface name=\"" + namePort.replace("-wf", namePort) + "\" admin=enabled" : "rasdial " + namePort;
        return handleConDcom(strScrips);
    }

    public ChangeDcom connectDcom() {
        String strScrips = namePort.contains("-wf") ? "netsh interface set interface name=\"" + namePort.replace("-wf", namePort) + "\" admin=enabled" : "rasdial " + this.namePort;
        return handleConDcom(strScrips);
    }

    public ChangeDcom result(StreamResultProcess handle) {
        handle.getResult(result, valueExist);
        return this;
    }

    public ChangeDcom disConnectIpDcom(String namePort) {
        String strScrips = namePort.contains("-wf") ? "netsh interface set interface name=\"" + namePort.replace("-wf", namePort) + "\" admin=Disabled" : "rasdial /disconnect";
        return handleConDcom(strScrips);
    }

    public ChangeDcom disConnectIpDcom() {
        String strScrips = namePort.contains("-wf") ? "netsh interface set interface name=\"" + this.namePort.replace("-wf", namePort) + "\" admin=Disabled" : "rasdial /disconnect";
        return handleConDcom(strScrips);
    }

    private ChangeDcom handleConDcom(String strScrips) {
        if (isCon) {
            ReProcessBuilder rePB = new ReProcessBuilder();
            rePB.runScrips(strScrips, (r, ve) -> {
                this.result = r;
                this.valueExist = ve;
            });
        }
        return this;
    }
}
