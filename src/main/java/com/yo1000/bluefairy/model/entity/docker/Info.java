package com.yo1000.bluefairy.model.entity.docker;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yoichi.kikuchi on 15/03/13.
 */
public class Info {
    @JsonProperty("Containers")
    private Integer containers;
    @JsonProperty("Debug")
    private Integer debug;
    @JsonProperty("DockerRootDir")
    private String dockerRootDir;
    @JsonProperty("Driver")
    private String driver;
    @JsonProperty("DriverStatus")
    private String[][] driverStatus;
    @JsonProperty("ExecutionDriver")
    private String executionDriver;
    @JsonProperty("ID")
    private String id;
    @JsonProperty("IPv4Forwarding")
    private Integer ipv4Forwarding;
    @JsonProperty("Images")
    private Integer images;
    @JsonProperty("IndexServerAddress")
    private String indexServerAddress;
    @JsonProperty("InitPath")
    private String initPath;
    @JsonProperty("InitSha1")
    private String initSha1;
    @JsonProperty("KernelVersion")
    private String kernelVersion;
    @JsonProperty("Labels")
    private String labels;
    @JsonProperty("MemTotal")
    private Long memTotal;
    @JsonProperty("MemoryLimit")
    private Integer memoryLimit;
    @JsonProperty("NCPU")
    private Integer nCpu;
    @JsonProperty("NEventsListener")
    private Integer nEventsListener;
    @JsonProperty("NFd")
    private Integer nFd;
    @JsonProperty("NGoroutines")
    private Integer nGoroutines;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("OperatingSystem")
    private String operatingSystem;
    @JsonProperty("RegistryConfig")
    private RegistryConfig registryConfig;
    @JsonProperty("SwapLimit")
    private Integer swapLimit;

    public Integer getContainers() {
        return containers;
    }

    public void setContainers(Integer containers) {
        this.containers = containers;
    }

    public Integer getDebug() {
        return debug;
    }

    public void setDebug(Integer debug) {
        this.debug = debug;
    }

    public String getDockerRootDir() {
        return dockerRootDir;
    }

    public void setDockerRootDir(String dockerRootDir) {
        this.dockerRootDir = dockerRootDir;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String[][] getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String[][] driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getExecutionDriver() {
        return executionDriver;
    }

    public void setExecutionDriver(String executionDriver) {
        this.executionDriver = executionDriver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIpv4Forwarding() {
        return ipv4Forwarding;
    }

    public void setIpv4Forwarding(Integer ipv4Forwarding) {
        this.ipv4Forwarding = ipv4Forwarding;
    }

    public Integer getImages() {
        return images;
    }

    public void setImages(Integer images) {
        this.images = images;
    }

    public String getIndexServerAddress() {
        return indexServerAddress;
    }

    public void setIndexServerAddress(String indexServerAddress) {
        this.indexServerAddress = indexServerAddress;
    }

    public String getInitPath() {
        return initPath;
    }

    public void setInitPath(String initPath) {
        this.initPath = initPath;
    }

    public String getInitSha1() {
        return initSha1;
    }

    public void setInitSha1(String initSha1) {
        this.initSha1 = initSha1;
    }

    public String getKernelVersion() {
        return kernelVersion;
    }

    public void setKernelVersion(String kernelVersion) {
        this.kernelVersion = kernelVersion;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Long getMemTotal() {
        return memTotal;
    }

    public void setMemTotal(Long memTotal) {
        this.memTotal = memTotal;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getNCpu() {
        return nCpu;
    }

    public void setNcpu(Integer nCpu) {
        this.nCpu = nCpu;
    }

    public Integer getnEventsListener() {
        return nEventsListener;
    }

    public void setnEventsListener(Integer nEventsListener) {
        this.nEventsListener = nEventsListener;
    }

    public Integer getnFd() {
        return nFd;
    }

    public void setnFd(Integer nFd) {
        this.nFd = nFd;
    }

    public Integer getnGoroutines() {
        return nGoroutines;
    }

    public void setnGoroutines(Integer nGoroutines) {
        this.nGoroutines = nGoroutines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public RegistryConfig getRegistryConfig() {
        return registryConfig;
    }

    public void setRegistryConfig(RegistryConfig registryConfig) {
        this.registryConfig = registryConfig;
    }

    public Integer getSwapLimit() {
        return swapLimit;
    }

    public void setSwapLimit(Integer swapLimit) {
        this.swapLimit = swapLimit;
    }

    public static class RegistryConfig {
        @JsonProperty("IndexConfigs")
        private IndexConfigs indexConfigs;

        public IndexConfigs getIndexConfigs() {
            return indexConfigs;
        }

        public void setIndexConfigs(IndexConfigs indexConfigs) {
            this.indexConfigs = indexConfigs;
        }

        public static class IndexConfigs {
            @JsonProperty("docker.io")
            private DockerIO dockerIO;
            @JsonProperty("InsecureRegistryCIDRs")
            private String[] insecureRegistryCIDRs;

            public DockerIO getDockerIO() {
                return dockerIO;
            }

            public void setDockerIO(DockerIO dockerIO) {
                this.dockerIO = dockerIO;
            }

            public String[] getInsecureRegistryCIDRs() {
                return insecureRegistryCIDRs;
            }

            public void setInsecureRegistryCIDRs(String[] insecureRegistryCIDRs) {
                this.insecureRegistryCIDRs = insecureRegistryCIDRs;
            }

            public static class DockerIO {
                @JsonProperty("Mirrors")
                private String[] mirrors;
                @JsonProperty("Name")
                private String name;
                @JsonProperty("Official")
                private Boolean official;
                @JsonProperty("Secure")
                private Boolean secure;

                public String[] getMirrors() {
                    return mirrors;
                }

                public void setMirrors(String[] mirrors) {
                    this.mirrors = mirrors;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Boolean getOfficial() {
                    return official;
                }

                public void setOfficial(Boolean official) {
                    this.official = official;
                }

                public Boolean getSecure() {
                    return secure;
                }

                public void setSecure(Boolean secure) {
                    this.secure = secure;
                }
            }
        }
    }
}
