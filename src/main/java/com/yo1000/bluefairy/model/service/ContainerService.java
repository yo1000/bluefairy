package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.ContainerCreator;
import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.entity.docker.*;
import com.yo1000.bluefairy.model.repository.ContainerRepository;
import com.yo1000.bluefairy.model.repository.ContainerUserRepository;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class ContainerService {
    @Resource
    private ContainerRepository containerRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ContainerUserRepository containerUserRepository;

    public Container[] getContainers() {
        return this.getContainerRepository().getJson();
    }

    public Container[] getContainersAll() {
        return this.getContainerRepository().getJson(true);
    }

    public ContainerInspect getContainer(String id) {
        return this.getContainerRepository().getInspect(id);
    }

    public Map<String, ContainerCreator> getContainerUserMap() {
        List<ContainerCreator> containerCreators = this.getContainerUserRepository().find();
        Map<String, ContainerCreator> containerUserMap = new HashMap<String, ContainerCreator>();

        for (ContainerCreator containerCreator : containerCreators) {
            containerUserMap.put(containerCreator.getId(), containerCreator);
        }

        return containerUserMap;
    }

    public void startContainer(String id) {
        this.getContainerRepository().postStart(id);
    }

    public void stopContainer(String id) {
        this.getContainerRepository().postStop(id);
    }

    public void removeContainer(String id) {
        this.getContainerRepository().deleteRemove(id);
    }

    public ContainerCreated runContainer(String image) {
        return this.runContainer(image, null);
    }

    public ContainerCreated runContainer(String image, String username) {
        ContainerCreated container = this.createContainer(image);
        this.startContainer(container.getId());

        if (username != null) {
            this.writeContainerUser(container.getId(), username);
        }

        return container;
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate) {
        return this.runContainer(containerCreate, null);
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate, String name) {
        return this.runContainer(containerCreate, name, null);
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate, String name, String username) {
        ContainerCreated container = this.createContainer(containerCreate, name);
        this.startContainer(container.getId());

        if (username != null) {
            this.writeContainerUser(container.getId(), username);
        }

        return container;
    }

    public void writeContainerUser(String id, String username) {
        User user = this.getUserRepository().findByUsername(username);

        ContainerCreator containerCreator = new ContainerCreator();
        containerCreator.setId(id);
        containerCreator.setCreator(user);

        this.getContainerUserRepository().create(containerCreator);
    }

    public ContainerCreated createContainer(ContainerCreate container) {
        return this.createContainer(container, null);
    }

    public ContainerCreated createContainer(ContainerCreate container, String name) {
        return this.getContainerRepository().postCreate(container, name);
    }

    public ContainerCreated createContainer(String image) {
        ContainerCreate container = new ContainerCreate();
        container.setHostname("");
        container.setDomainname("");
        container.setUser("");
        container.setMemory(0L);
        container.setMemorySwap(0L);
        container.setCpuShares(0);
        container.setCpuset("0");
        container.setAttachStdin(false);
        container.setAttachStdout(false);
        container.setAttachStderr(false);
        container.setTty(false);
        container.setOpenStdin(false);
        container.setStdinOnce(false);
        container.setEnv(null);
        container.setCmd(new String[] {});
        container.setEntrypoint(new String[] {});
        container.setImage(image);
        container.setVolumes(new HashMap<String, HashMap<String, String>>() {
            {

            }
        });
        container.setWorkingDir("");
        container.setNetworkDisabled(false);
        container.setMacAddress(null);
        container.setExposedPorts(new HashMap<String, HashMap<String, String>>() {
            {

            }
        });
        container.setSecurityOpts(new String[] {""});

        HostConfig hostConfig = new HostConfig();
        container.setHostConfig(hostConfig);

        hostConfig.setBinds(new String[]{});
        hostConfig.setLinks(new String[]{});

        hostConfig.setPortBindings(new HashMap<String, PortBinding[]>());
        hostConfig.setPublishAllPorts(true);
        hostConfig.setPrivileged(false);
        hostConfig.setReadonlyRootfs(false);
        hostConfig.setDns(new String[]{});
        hostConfig.setDnsSearch(new String[]{});
        hostConfig.setExtraHosts(null);
        hostConfig.setVolumesFrom(new String[]{});
        hostConfig.setCapAdd(new String[]{});
        hostConfig.setCapDrop(new String[]{});

        HostConfig.RestartPolicy restartPolicy = new HostConfig.RestartPolicy();
        hostConfig.setRestartPolicy(restartPolicy);

        restartPolicy.setName("");
        restartPolicy.setMaximumRetryCount(0);

        hostConfig.setRestartPolicy(new HostConfig.RestartPolicy());
        hostConfig.setNetworkMode("bridge");
        hostConfig.setDevices(new String[]{});

        return this.createContainer(container);
    }

    protected ContainerRepository getContainerRepository() {
        return containerRepository;
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    protected ContainerUserRepository getContainerUserRepository() {
        return containerUserRepository;
    }
}
