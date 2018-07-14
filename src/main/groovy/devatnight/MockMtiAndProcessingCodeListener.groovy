package devatnight
import org.jpos.iso.ISOMsg
import org.jpos.iso.ISOSource

public class MockMtiAndProcessingCodeListener extends MtiAndProcessingCodeListener {
    public MockMtiAndProcessingCodeListener () {
        super()
    }

    public boolean processMatchedMessage(ISOSource source, ISOMsg m) {
        log.info("This message has been processed by the MockMtiAndProceessingCodeListener: " + m);
        return true
    }
}