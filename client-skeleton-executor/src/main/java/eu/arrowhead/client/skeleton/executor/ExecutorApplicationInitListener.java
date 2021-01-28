package eu.arrowhead.client.skeleton.executor;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

import eu.arrowhead.client.skeleton.executor.security.ExecutorSecurityConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import eu.arrowhead.client.library.ArrowheadService;
import eu.arrowhead.client.library.config.ApplicationInitListener;
import eu.arrowhead.client.library.util.ClientCommonConstants;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.core.CoreSystem;
import eu.arrowhead.common.exception.ArrowheadException;

@Component
public class ExecutorApplicationInitListener extends ApplicationInitListener {

    //=================================================================================================
    // members

    @Autowired
    private ArrowheadService arrowheadService;

    @Autowired
    private ExecutorSecurityConfig executorSecurityConfig;

    @Value(ClientCommonConstants.$TOKEN_SECURITY_FILTER_ENABLED_WD)
    private boolean tokenSecurityFilterEnabled;

    @Value(CommonConstants.$SERVER_SSL_ENABLED_WD)
    private boolean sslEnabled;

    private final Logger logger = LogManager.getLogger(ExecutorApplicationInitListener.class);

    //=================================================================================================
    // methods

    //-------------------------------------------------------------------------------------------------
    @Override
    protected void customInit(final ContextRefreshedEvent event) {

        //Checking the availability of necessary core systems
        checkCoreSystemReachability(CoreSystem.SERVICE_REGISTRY);
        checkCoreSystemReachability(CoreSystem.ORCHESTRATOR);


        //Initialize Arrowhead Context
        arrowheadService.updateCoreServiceURIs(CoreSystem.ORCHESTRATOR);

        //TODO: implement here any custom behavior on application start up

    }

    //-------------------------------------------------------------------------------------------------
    @Override
    public void customDestroy() {
        //TODO: implement here any custom behavior on application shutdown
    }
}
