package com.custom.config;

import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by fuyd on 2019-11-05
 */
public class DefaultRPCHook implements RPCHook {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRPCHook.class);

    @Override
    public void doBeforeRequest(String remoteAddr, RemotingCommand request) {
        DefaultRPCHook.LOG.info("---------doBefore---------");
    }

    @Override
    public void doAfterResponse(String remoteAddr, RemotingCommand request, RemotingCommand response) {
        DefaultRPCHook.LOG.info("---------doAfter---------");
    }
}
