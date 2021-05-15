package org.skillbox.springbootrest.api.response;

import org.skillbox.springbootrest.model.TagWeight;
import java.util.HashSet;
import java.util.Set;

public class TagsResponse {

    private Set<TagWeight> tagWeights = new HashSet<>();

    public Set<TagWeight> getTagWeights() {
        return tagWeights;
    }

    public void setTagWeights(Set<TagWeight> tagWeights) {
        this.tagWeights = tagWeights;
    }
}