package com.myCompanyName.myProjectName.common.rest.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.myCompanyName.myProjectName.generated.model.HalLink;
import com.myCompanyName.myProjectName.generated.model.HalLink.MethodEnum;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;

public abstract class BaseController {

  protected HalLink getHalGetLink(HttpEntity httpEntity) {
    return getHalLink(httpEntity, MethodEnum.GET);
  }

  protected HalLink getHalPostLink(HttpEntity httpEntity) {
    return getHalLink(httpEntity, MethodEnum.POST);
  }

  protected HalLink getHalDeleteLink(HttpEntity httpEntity) {
    return getHalLink(httpEntity, MethodEnum.DELETE);
  }

  private static HalLink getHalLink(HttpEntity httpEntity, MethodEnum method) {
    Link link = linkTo(httpEntity).withSelfRel();
    HalLink halLink = new HalLink();
    halLink.setHref(link.getHref());
    halLink.setMethod(method);
    halLink.setTemplated(link.isTemplated());
    return halLink;
  }
}
