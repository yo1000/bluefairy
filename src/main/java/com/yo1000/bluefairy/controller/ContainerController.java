package com.yo1000.bluefairy.controller;

import com.yo1000.bluefairy.model.entity.docker.ContainerCreated;
import com.yo1000.bluefairy.model.entity.docker.ContainerInspect;
import com.yo1000.bluefairy.model.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yoichi.kikuchi on 15/02/19.
 */
@Controller
@RequestMapping("container")
public class ContainerController {
    @Autowired
    private UserDetails userDetails;

    @Autowired
    private ContainerService containerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Containers");
        model.addAttribute("containers", this.getContainerService().getContainers());
        model.addAttribute("containerUserMap", this.getContainerService().getContainerUserMap());
        model.addAttribute("containerCreatorMap", this.getContainerService().getContainerCreatorMap(this.getUserDetails().getUsername()));

        return "containers";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String all(Model model) {
        model.addAttribute("title", "All containers");
        model.addAttribute("containers", this.getContainerService().getContainersAll());
        model.addAttribute("containerUserMap", this.getContainerService().getContainerUserMap());
        model.addAttribute("containerCreatorMap", this.getContainerService().getContainerCreatorMap());

        return "containers";
    }

    @RequestMapping(value = "{id:(?!^all$).+}", method = RequestMethod.GET)
    public String id(@PathVariable String id, Model model) {
        ContainerInspect container = this.getContainerService().getContainer(id);

        model.addAttribute("title", "Container " + container.getIdToShort());
        model.addAttribute("container", container);

        return "container";
    }

    @RequestMapping(value = "run", method = RequestMethod.POST)
    public String run(@RequestParam String id, @RequestParam String repoTags) {
        ContainerCreated container = this.getContainerService()
                .runContainer(repoTags, this.getUserDetails().getUsername());

        return "redirect:/container/" + container.getId();
    }

    @RequestMapping(value = "{id}/start", method = RequestMethod.POST)
    public String start(@PathVariable String id) {
        this.getContainerService().startContainer(id, this.getUserDetails().getUsername());

        return "redirect:/container/" + id;
    }

    @RequestMapping(value = "{id}/stop", method = RequestMethod.POST)
    public String stop(@PathVariable String id) {
        this.getContainerService().stopContainer(id, this.getUserDetails().getUsername());

        return "redirect:/container/all";
    }

    @RequestMapping(value = "{id}/remove", method = RequestMethod.POST)
    public String remove(@PathVariable String id) {
        this.getContainerService().removeContainer(id, this.getUserDetails().getUsername());

        return "redirect:/container/all";
    }

    protected ContainerService getContainerService() {
        return containerService;
    }

    protected UserDetails getUserDetails() {
        return userDetails;
    }
}
