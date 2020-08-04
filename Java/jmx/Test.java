package jmx;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 *  使用 jmx 获取远程 java 程序的 MBean 数据
 * @author wangsw
 *
 */
public class Test {
    public static void main(String[] args) {
        try {         
            getKafkaData();
            getConnectData();
            getStreamsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getKafkaData() throws Exception {
    	System.out.println(Thread.currentThread().getStackTrace()[1]);
        List<String> jmxURLs = new ArrayList<>();
        jmxURLs.add("service:jmx:rmi:///jndi/rmi://{IP}:49900/jmxrmi");
        jmxURLs.add("service:jmx:rmi:///jndi/rmi://{IP}:49900/jmxrmi");
        jmxURLs.add("service:jmx:rmi:///jndi/rmi://{IP}:49900/jmxrmi");
        long totalAccesslogMessagesInPerSec = 0, totalAccesslogBytesInPerSec = 0, totalChannelviewMessagesInPerSec = 0, totalChannelviewBytesInPerSec = 0;
        for(String jmxURL : jmxURLs) {
        	JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();
            
        	ObjectName accesslogMessagesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec,topic=accesslog");
        	String attrCountName = "Count";
            System.out.println(jmxURL + " " + accesslogMessagesInPerSec + " : " + attrCountName + " : " + mbsc.getAttribute(accesslogMessagesInPerSec, attrCountName));
            totalAccesslogMessagesInPerSec += (long)mbsc.getAttribute(accesslogMessagesInPerSec, attrCountName);
            		
            ObjectName accesslogBytesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=accesslog");
            System.out.println(jmxURL + " " + accesslogBytesInPerSec + " : " + attrCountName + " : " + mbsc.getAttribute(accesslogBytesInPerSec, attrCountName));
            totalAccesslogBytesInPerSec += (long)mbsc.getAttribute(accesslogBytesInPerSec, attrCountName);
            
            ObjectName channelviewMessagesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec,topic=channelview");
            System.out.println(jmxURL + " " + channelviewMessagesInPerSec + " : " + attrCountName + " : " + mbsc.getAttribute(channelviewMessagesInPerSec, attrCountName));
            totalChannelviewMessagesInPerSec += (long)mbsc.getAttribute(channelviewMessagesInPerSec, attrCountName);
            
            ObjectName channelviewBytesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=channelview");
            System.out.println(jmxURL + " " + channelviewBytesInPerSec + " : " + attrCountName + " : " + mbsc.getAttribute(channelviewBytesInPerSec, attrCountName));
            totalChannelviewBytesInPerSec += (long)mbsc.getAttribute(channelviewBytesInPerSec, attrCountName);
            
        }
        System.out.println("总计值：");
        System.out.println("totalAccesslogMessagesInPerSec:" + totalAccesslogMessagesInPerSec);
        System.out.println("totalAccesslogBytesInPerSec:" + totalAccesslogBytesInPerSec);
        System.out.println("totalChannelviewMessagesInPerSec:" + totalChannelviewMessagesInPerSec);
        System.out.println("totalChannelviewBytesInPerSec:" + totalChannelviewBytesInPerSec);
	}


    private static void getConnectData() throws Exception {
    	System.out.println(Thread.currentThread().getStackTrace()[1]);
    	JMXServiceURL serviceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://{IP}:49901/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection mbsc = connector.getMBeanServerConnection();
        
    	ObjectName connectMetrics = new ObjectName("kafka.connect:type=connect-metrics,client-id=connect-1");
    	String attrRequestName = "request-total";
        System.out.println(serviceURL + " " + connectMetrics + " : " + attrRequestName + " : " + mbsc.getAttribute(connectMetrics, attrRequestName));
        
        String attrResponseName = "response-total";
        System.out.println(serviceURL + " " + connectMetrics + " : " + attrResponseName + " : " + mbsc.getAttribute(connectMetrics, attrResponseName));
        
        ObjectName urlFlowSink = new ObjectName("kafka.connect:type=sink-task-metrics,connector=url_flow_sink,task=1");
        String attrSinkRecordReadTotal = "sink-record-read-total";
        System.out.println(serviceURL + " " + urlFlowSink + " : " + attrSinkRecordReadTotal + " : " + mbsc.getAttribute(urlFlowSink, attrSinkRecordReadTotal)); 
	}

    private static void getStreamsData() throws Exception {
    	System.out.println(Thread.currentThread().getStackTrace()[1]);
    	List<String> urls = new ArrayList<>();
    	urls.add("service:jmx:rmi:///jndi/rmi://{IP}:49902/jmxrmi");
    	urls.add("service:jmx:rmi:///jndi/rmi://{IP}:49903/jmxrmi");
    	for(String url : urls) {
    		JMXServiceURL serviceURL = new JMXServiceURL(url);
            JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
            MBeanServerConnection mbsc = connector.getMBeanServerConnection();
            
            double total = 0;
            Set<ObjectName> objectNames = mbsc.queryNames(ObjectName.getInstance("kafka.producer:type=producer-metrics,client-id=*"), null);
            for (ObjectName objectName : objectNames) {
                String attrRecordSendTotal = "record-send-total";
                System.out.println(serviceURL + " " + objectName + " : " + attrRecordSendTotal + " : " + mbsc.getAttribute(objectName, attrRecordSendTotal));
                total += (double) mbsc.getAttribute(objectName, attrRecordSendTotal);
            }
            System.out.println("总计值：");
            System.out.println(total);
    	}  
	}
}
