package TestCase;

import com.mycompany.changedcom.ChangeDcom;
import com.mycompany.streamprocessbuilder.ReProcessBuilder;

public class TestCase {

    public static void main(String[] args) {
        ChangeDcom changeDcom = new ChangeDcom();
        changeDcom.start()
                .connectDcom("MobifoneFastConnect").result((result, valueExist) -> handleResult(result, valueExist))
                .disConnectIpDcom("MobifoneFastConnect").result((result, valueExist) -> handleResult(result, valueExist));
    }

    static void handleResult(String result, int valueExist) {
        if (valueExist != 0) {//err
            System.out.println(result);
        }
    }

    static void testReProcessBuilder() {
        ReProcessBuilder rePB = new ReProcessBuilder();
        rePB.runScrips("rasdial MobifoneFastConnect", (result, valueExis) -> {
            if (valueExis != 0) {
                System.out.println(result);
            }
        }).endScrips();
    }
}
