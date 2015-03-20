package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.docker.*;
import com.yo1000.bluefairy.model.repository.ContainerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class ContainerService {
    @Resource
    private ContainerRepository containerRepository;

    public Container[] getContainers() {
        return this.getContainerRepository().getJson();
    }

    public Container[] getContainersAll() {
        return this.getContainerRepository().getJson(true);
    }

    public ContainerInspect getContainer(String id) {
        return this.getContainerRepository().getInspect(id);
    }

    public void startContainer(String id) {
        this.getContainerRepository().postStart(id);
    }

    public void stopContainer(String id) {
        this.getContainerRepository().postStop(id);
    }

    public ContainerCreated runContainer(String image) {
        ContainerCreated container = this.newContainer(image);
        this.startContainer(container.getId());

        return container;
    }

    protected ContainerCreated newContainer(String image) {
        ContainerCreate container = new ContainerCreate();
        container.setHostname("");
        container.setDomainname("");
        container.setUser("");
        container.setMemory(0L);
        container.setMemorySwap(0L);
        container.setCpuShares(0);
        container.setCpuset("0");
        container.setAttachStdin(false);
        container.setAttachStdout(false);//e.g. true
        container.setAttachStderr(false);//e.g. true
        container.setTty(false);
        container.setOpenStdin(false);
        container.setStdinOnce(false);
        container.setEnv(null);
        container.setCmd(new String[] {});
        container.setEntrypoint("");
        container.setImage(image);
        container.setVolumes(new HashMap<String, HashMap<String, String>>() {
            {

            }
        });//e.g. "/tmp":{}
        container.setWorkingDir("");
        container.setNetworkDisabled(false);
        container.setMacAddress(null);//e.g. "12:34:56:78:9a:bc"
        container.setExposedPorts(new HashMap<String, HashMap<String, String>>() {
            {

            }
        });//e.g. "22/tcp":{}
        container.setSecurityOpts(new String[] {""});

        HostConfig hostConfig = new HostConfig();
        container.setHostConfig(hostConfig);

        hostConfig.setBinds(new String[]{});//["/tmp:/tmp"]
        hostConfig.setLinks(new String[]{});//["redis3:redis"]
        hostConfig.setLxcConf(new HashMap[] {
            new HashMap<String, String>() {
                {
                    this.put("lxc.utsname", "docker");
                }
            }
        });
        hostConfig.setPortBindings(new HashMap<String, PortBinding[]>());
        hostConfig.setPublishAllPorts(true);
        hostConfig.setPrivileged(false);
        hostConfig.setReadonlyRootfs(false);
        hostConfig.setDns(new String[]{});
        hostConfig.setDnsSearch(new String[]{});
        hostConfig.setExtraHosts(null);
        hostConfig.setVolumesFrom(new String[]{});
        hostConfig.setCapAdd(new String[]{});//"NET_ADMIN"
        hostConfig.setCapDrop(new String[]{});//"MKNOD"

        HostConfig.RestartPolicy restartPolicy = new HostConfig.RestartPolicy();
        hostConfig.setRestartPolicy(restartPolicy);

        restartPolicy.setName("");
        restartPolicy.setMaximumRetryCount(0);

        hostConfig.setRestartPolicy(new HostConfig.RestartPolicy());
        hostConfig.setNetworkMode("bridge");
        hostConfig.setDevices(new String[]{});

        return this.getContainerRepository().postCreate(container);
    }

    protected ContainerRepository getContainerRepository() {
        return containerRepository;
    }
}
