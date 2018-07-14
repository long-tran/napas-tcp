package devatnight

import bsh.Interpreter
import org.jpos.core.Configurable
import org.jpos.core.Configuration
import org.jpos.core.ConfigurationException
import org.jpos.iso.ISOMsg
import org.jpos.iso.ISORequestListener
import org.jpos.iso.ISOSource
import org.jpos.util.Log


/**
 * MtiAndProcessingCodeListener - MIT and Processing Code based request listener
 * @author <a href="mailto:long@long-tran.com">Long Tran</a>
 * @version $Revision$ $Date$
 */
public abstract class MtiAndProcessingCodeListener extends Log
    implements ISORequestListener, Configurable
{
    protected String mti
    protected String[] processingCodes
    Configuration cfg
    public MtiAndProcessingCodeListener () {
        super()
    }
   /**
    * @param cfg
    * <ul>
    *  <li>mti - supported message types (example: "0100","0800")
    *  <li>processingCode - supported processing codes, can be more than one (example: "31A000,04A000")
    * </ul>
    */
    public void setConfiguration (Configuration cfg) 
        throws ConfigurationException
    {
        this.cfg = cfg
        mti = cfg.getAll ("mti")
        processingCodes = cfg.get ("processing-codes", "*").split(",")
    }

    public boolean process (ISOSource source, ISOMsg m) {
        try{
            String mti = m.getMTI ()
            if (this.mti != mti) {
                return false
            }
            if ( processingCodes.indexOf(m.getString(3)) >= 0 || processingCodes.indexOf('*') >= 0) {
                if (processMatchedMessage(source, m)) {
                    return true
                }
            }
        }catch (Exception e){
            warn(e)
        }
        //if we reached this far none of the sources handled the request.
        return false
    }

    abstract boolean processMatchedMessage (ISOSource source, ISOMsg m);
}
