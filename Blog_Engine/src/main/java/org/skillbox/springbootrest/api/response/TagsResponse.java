package org.skillbox.springbootrest.api.response;

import org.skillbox.springbootrest.model.TagWeight;
import java.util.ArrayList;
import java.util.List;

public class TagsResponse {

    private List<TagWeight> tags = new ArrayList<>();

    public List<TagWeight> getTags() {
        return tags;
    }

    public void setTags(List<TagWeight> tags) {
        this.tags = tags;
    }
}