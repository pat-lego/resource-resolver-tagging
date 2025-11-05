package com.adobe.aem.support.core.pojo;

import org.apache.sling.api.resource.Resource;

import com.day.cq.tagging.TagManager;

public class TagPojo {

    public TagManager getTagManager(Resource resource) {
        return resource.getResourceResolver().adaptTo(TagManager.class);
    }

}
