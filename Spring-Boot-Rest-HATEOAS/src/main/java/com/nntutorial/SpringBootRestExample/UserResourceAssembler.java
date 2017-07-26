package com.nntutorial.SpringBootRestExample;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

	@Override
	public Resource<User> toResource(User user) {
		Resource<User> resource=new Resource<>(user);
		resource.add(ControllerLinkBuilder.linkTo(UserController.class).slash("getallusers").withRel("to_getAllUsers"));
		resource.add(ControllerLinkBuilder.linkTo(UserController.class).slash("getusers").slash(user.getUsercompanyname()).withRel("to_getUsers_byCompanyName"));
		resource.add(ControllerLinkBuilder.linkTo(UserController.class).slash("getuserbyID").slash(user.getId()).withSelfRel());
		return resource;
	}

}
