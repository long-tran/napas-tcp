package devatnight

import org.jpos.q2.Q2
/**
 * JposQ2 - Start Q2
 * @author <a href="mailto:long@long-tran.com">Long Tran</a>
 * @version $Revision$ $Date$
 */
class JposQ2 {

    public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("Please start this awesome app with at least a parameter pointing to the folder containing your XML")
            exit (1)
        }

        def q2 = new Q2(args[0])
        q2.start()
    }

}