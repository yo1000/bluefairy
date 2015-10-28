package com.yo1000.bluefairy.controller.rest.v1;

import com.yo1000.bluefairy.model.entity.User;
import com.yo1000.bluefairy.model.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by yoichi.kikuchi on 15/05/10.
 */
@RestController
@RequestMapping("api/v1/user")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserRestController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json")
    public Object putItem(@PathVariable String id, @RequestBody User user) {
        this.getUserService().updateUser(id,
                user.getUsername(), user.getPassword(),
                user.getRole(), user.getFullname());

        return new HashMap<String, Object>() {
            {
                this.put("status", "success");
            }
        };
    }

    protected UserService getUserService() {
        return userService;
    }
}
