package com.unico.soap;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jws.WebMethod;
import javax.jws.WebService;
import com.unico.helper.GCDPoJo;
import com.unico.helper.MessageQueueHelper;

/**
 * This class exposes SOAP services for GCD operations
 * @author H.Husain
 */
@WebService(name ="GCDService" )
public class GCDService {

    @Resource(mappedName = "java:jboss/exported/jms/queue/gcdRestQueue")
    private Queue myQueue;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory myQueueFactory;

    @Resource(mappedName = "java:jboss/exported/jms/queue/gcdSoapQueue")
    private Queue gcdQueue;

    private MessageQueueHelper messageQueueOperation = MessageQueueHelper.getInstance();

    /**
     * Method to calculated the GCD
     * @return
     * @throws Exception 
     */
    @WebMethod
    public int gcd() throws Exception {
        Message message = messageQueueOperation.readMessage(myQueue, myQueueFactory);
        if (message != null) {
            GCDPoJo gcd = (GCDPoJo) ((ObjectMessage) message).getObject();
            int gcdvalue = findGreatestCommonDivisor(gcd.getI1(), gcd.getI2());
            messageQueueOperation.sendMessage(gcdvalue, gcdQueue, myQueueFactory);
            return gcdvalue;
        }
        return 0;
    }

    /**
     * Method to fetch all elements from GCD queue
     * @return
     * @throws Exception 
     */
    @WebMethod
    public List<Integer> gcdList() throws Exception {
        List<Integer> result = new ArrayList();
        for (Message message : messageQueueOperation.readAllMessages(gcdQueue, myQueueFactory)) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            result.add((Integer) objectMessage.getObject());
        }
        return result;
    }

    /**
     * Method to fetch all elements from gcd queue and calculate sum
     * @return
     * @throws Exception 
     */
    @WebMethod
    public int gcdSum() throws Exception {
        int sum = 0;

        for (Message message : messageQueueOperation.readAllMessages(gcdQueue, myQueueFactory)) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            sum += (Integer) objectMessage.getObject();
        }

        return sum;
    }

    /**
     * Recursive method to find out the GCD
     * @param i1
     * @param i2
     * @return 
     */
    private int findGreatestCommonDivisor(int i1, int i2) {
        if (i2 == 0) {
            return i1;
        }
        return findGreatestCommonDivisor(i2, i1 % i2);
    }
}
