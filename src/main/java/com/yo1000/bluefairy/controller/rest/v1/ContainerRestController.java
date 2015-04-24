package com.yo1000.bluefairy.controller.rest.v1;

import com.yo1000.bluefairy.model.entity.docker.Container;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreate;
import com.yo1000.bluefairy.model.entity.docker.ContainerCreated;
import com.yo1000.bluefairy.model.service.ContainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by yoichi.kikuchi on 15/04/17.
 */
@RestController
@RequestMapping("api/v1/container")
public class ContainerRestController {
    @Resource
    private ContainerService containerService;

    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public Container[] getItems() {
        return this.getContainerService().getContainers();
    }

    @RequestMapping(value = "all", method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public Container[] getItemsAll() {
        return this.getContainerService().getContainersAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "create", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public ContainerCreated postCreate(@RequestBody ContainerCreate containerCreate,
                                       @RequestParam String name) {
        return this.getContainerService().runContainer(containerCreate, name);
    }

    //    @RequestMapping("{id:(?!^all$).+}")


    protected ContainerService getContainerService() {
        return containerService;
    }
}
