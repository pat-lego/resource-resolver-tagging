package com.adobe.aem.support.core.services;

import java.util.Collections;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.support.core.pojo.TagPojo;
import com.day.cq.tagging.InvalidTagFormatException;
import com.day.cq.tagging.JcrTagManagerFactory;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@Component(service = SimpleService.class)
public class SimpleServiceImpl implements SimpleService {

    Logger logger = LoggerFactory.getLogger(getClass());
    private final String serviceName = "aemsupport";

    ResourceResolverFactory resourceResolverFactory;

    @Activate
    public SimpleServiceImpl(@Reference ResourceResolverFactory resourceResolverFactory) throws LoginException, InvalidTagFormatException {
        this.resourceResolverFactory = resourceResolverFactory;
        ResourceResolver resolver = this.resourceResolverFactory
                .getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, serviceName));
        TagPojo tagPojo = new TagPojo(); 
        
        Resource content = resolver.getResource("/content");
        TagManager tagger = tagPojo.getTagManager(content);
        Tag tag = tagger.resolve("/content/cq:tags");
        logger.info("Tag " + tag.getCount());
    }

    @Override
    public void simple() {
        logger.info("I was called");
    }

}
