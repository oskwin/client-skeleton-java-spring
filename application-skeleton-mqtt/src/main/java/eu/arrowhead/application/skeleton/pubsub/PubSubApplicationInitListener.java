package eu.arrowhead.application.skeleton.pubsub;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ai.aitia.arrowhead.application.library.ArrowheadService;
import ai.aitia.arrowhead.application.library.config.ApplicationInitListener;
import ai.aitia.arrowhead.application.library.util.ApplicationCommonConstants;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.core.CoreSystem;
import eu.arrowhead.common.exception.ArrowheadException;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;

@Component
public class PubSubApplicationInitListener extends ApplicationInitListener implements MqttCallback {
	
	//=================================================================================================
	// members
	
	@Autowired
	private ArrowheadService arrowheadService;
	
	@Value(CommonConstants.$SERVER_SSL_ENABLED_WD)
	private boolean sslEnabled;
	
  MqttClient client = null;
	private final Logger logger = LogManager.getLogger(PubSubApplicationInitListener.class);
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@Override
	protected void customInit(final ContextRefreshedEvent event) {
		
		//Checking the availability of necessary core systems
		//checkCoreSystemReachability(CoreSystem.SERVICEREGISTRY);
		//checkCoreSystemReachability(CoreSystem.AUTHORIZATION);

		//Initialize Arrowhead Context
		//arrowheadService.updateCoreServiceURIs(CoreSystem.AUTHORIZATION);	
		
		//TODO: implement here any custom behavior on application start up
    try {
      client = arrowheadService.connectMQTTBroker(this, "127.0.0.1", 1883, "pubsub", "badpassword", "pubsub01");
      client.subscribe("ah/pubsub/messages");
    } catch (Exception e) {
      throw new ArrowheadException("Could not connect to MQTT broker!\n" + e.toString());
    }
	}

	//-------------------------------------------------------------------------------------------------
	@Override
	public void customDestroy() {
		//TODO: implement here any custom behavior on application shout down
    if (client != null) {
      try {
        arrowheadService.disconnectMQTTBroker(client);
        arrowheadService.closeMQTTBroker(client);
      } catch(Exception e) {
      }

      client = null;
    }
	}
	
	//=================================================================================================
	// MQTT callback methods

  @Override
  public void connectionLost(Throwable t) {
    System.out.println("connectionLost: " + t.toString());

		//TODO: implement here any custom behavior on broker disconnect, typically a reconnect after a short timeout
  }

  @Override
  public void messageArrived(String topic, MqttMessage message) throws Exception {
    System.out.println("topic - " + topic + ": " + new String(message.getPayload()));

		//TODO: implement here any custom behavior on incoming messages, filtered in topic
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken token) {
  }

	//=================================================================================================
	// assistant methods
	
}
