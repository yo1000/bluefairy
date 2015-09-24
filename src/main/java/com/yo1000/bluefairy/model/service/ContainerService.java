package com.yo1000.bluefairy.model.service;

import com.yo1000.bluefairy.model.entity.ContainerCreator;
import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.entity.docker.*;
import com.yo1000.bluefairy.model.repository.ContainerCreatorRepository;
import com.yo1000.bluefairy.model.repository.ContainerRepository;
import com.yo1000.bluefairy.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContainerCreatorRepository containerCreatorRepository;

    public Container[] getContainers() {
        return this.getContainerRepository().getJson();
    }

    public Container[] getContainersAll() {
        return this.getContainerRepository().getJson(true);
    }

    public ContainerInspect getContainer(String id) {
        return this.getContainerRepository().getInspect(id);
    }

    public Map<String, ContainerCreator> getContainerCreatorMap() {
        return this.getContainerCreatorMap(null, true);
    }

    public Map<String, ContainerCreator> getContainerCreatorMap(String username) {
        return this.getContainerCreatorMap(username, false);
    }

    protected Map<String, ContainerCreator> getContainerCreatorMap(String username, boolean all) {
        Map<String, ContainerCreator> creatorMap = new HashMap<String, ContainerCreator>();
        List<ContainerCreator> containerCreators = this.getContainerCreatorRepository().find();

        for (ContainerCreator containerCreator : containerCreators) {
            creatorMap.put(containerCreator.getId(), containerCreator);
        }

        Map<String, ContainerCreator> containerCreatorMap = new HashMap<String, ContainerCreator>();
        Container[] containers = all ? this.getContainersAll() : this.getContainers();

        for (Container container : containers) {
            ContainerCreator containerCreator = creatorMap.containsKey(container.getId())
                    ? creatorMap.get(container.getId())
                    : new ContainerCreator();

            if (username != null && containerCreator.getCreator() == null) {
                continue;
            }

            if (username != null && !containerCreator.getCreator().getUsername().equals(username)) {
                continue;
            }

            containerCreator.setContainer(container);
            containerCreatorMap.put(container.getId(), containerCreator);
        }

        return containerCreatorMap;
    }

    public Map<String, ContainerCreator> getContainerUserMap() {
        List<ContainerCreator> containerCreators = this.getContainerCreatorRepository().find();
        Map<String, ContainerCreator> containerUserMap = new HashMap<String, ContainerCreator>();

        for (ContainerCreator containerCreator : containerCreators) {
            containerUserMap.put(containerCreator.getId(), containerCreator);
        }

        return containerUserMap;
    }

    public void startContainer(String id) {
        this.getContainerRepository().postStart(id);
    }

    public void startContainer(String id, String username) {
        this.validateCreator(id, username);
        this.getContainerRepository().postStart(id);
    }

    public void stopContainer(String id, String username) {
        this.validateCreator(id, username);
        this.getContainerRepository().postStop(id);
    }

    public void removeContainer(String id, String username) {
        this.validateCreator(id, username);
        this.getContainerRepository().deleteRemove(id);
    }

    public ContainerCreated runContainer(String image) {
        return this.runContainer(image, null);
    }

    public ContainerCreated runContainer(String image, String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("Unspecified username.");
        }

        ContainerCreated container = this.createContainer(image);
        this.startContainer(container.getId());

        return container;
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate) {
        return this.runContainer(containerCreate, null);
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate, String name) {
        return this.runContainer(containerCreate, name, null);
    }

    public ContainerCreated runContainer(ContainerCreate containerCreate, String name, String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalStateException("Unspecified username.");
        }

        ContainerCreated container = this.createContainer(containerCreate, name);
        this.startContainer(container.getId());
        this.writeContainerUser(container.getId(), username);

        return container;
    }

    public void writeContainerUser(String id, String username) {
        User user = this.getUserRepository().findByUsername(username);

        ContainerCreator containerCreator = new ContainerCreator();
        containerCreator.setId(id);
        containerCreator.setCreator(user);

        this.getContainerCreatorRepository().create(containerCreator);
    }

    public void validateCreator(String id, String username) {
        if (!this.getContainerCreatorRepository().existsByIdAndUsername(id, username)) {
            throw new IllegalStateException("Different container owners.");
        }
    }

    public ContainerCreated createContainer(ContainerCreate container) {
        return this.createContainer(container, null);
    }

    public ContainerCreated createContainer(ContainerCreate container, String name) {
        return this.getContainerRepository().postCreate(container, name);
    }

    public ContainerCreated createContainer(String image) {
        ContainerCreate container = new ContainerCreate();
        container.setHostname(null);
        container.setDomainname(null);
        container.setUser(null);
        container.setMemory(0L);
        container.setMemorySwap(0L);
        container.setCpuShares(0);
        container.setCpuset(null);
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
        container.setVolumes(new HashMap<String, HashMap<String, String>>() {});
        container.setWorkingDir(null);
        container.setNetworkDisabled(false);
        container.setMacAddress(null);
        container.setExposedPorts(new HashMap<String, HashMap<String, String>>() {});
        container.setSecurityOpts(new String[] {});

        HostConfig hostConfig = new HostConfig();
        container.setHostConfig(hostConfig);

        hostConfig.setBinds(new String[] {});
        hostConfig.setLinks(new String[] {});

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

        restartPolicy.setName(null);
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

    protected ContainerCreatorRepository getContainerCreatorRepository() {
        return containerCreatorRepository;
    }
}
