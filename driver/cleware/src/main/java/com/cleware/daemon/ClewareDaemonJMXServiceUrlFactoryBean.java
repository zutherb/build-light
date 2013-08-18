package com.cleware.daemon;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;

import javax.management.remote.JMXServiceURL;
import java.util.List;
import java.util.Properties;

/**
 * @author zutherb
 */
public class ClewareDaemonJMXServiceUrlFactoryBean implements FactoryBean<String> {

    @Override
    public String getObject() throws Exception {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor desc : vms) {
            if (desc.displayName().contains(ClewareDaemonImpl.class.getName())) {
                VirtualMachine vm = VirtualMachine.attach(desc);
                Properties props = vm.getAgentProperties();
                String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if (StringUtils.isNotEmpty(connectorAddress)) {
                    return connectorAddress;
                }
            }
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return JMXServiceURL.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
