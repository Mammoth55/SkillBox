package org.skillbox.springbootrest.api.response;

import org.skillbox.springbootrest.model.TagWeight;
import java.util.HashSet;
import java.util.Set;

public class TagsResponse {

    private Set<TagWeight> tags = new HashSet<>();

    public Set<TagWeight> getTags() {
        return tags;
    }

    public void setTags(Set<TagWeight> tags) {
        this.tags = tags;
    }
}