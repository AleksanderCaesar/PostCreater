package main.service;

import main.api.response.TagResponse;
import main.model.Tags;
import main.repos.TagRepo;
import main.utils.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService
{
    @Autowired
    private TagRepo tagRepo;

    public TagResponse getTagList(){
        TagResponse tagResponse = new TagResponse();
        List<TagUtil> tagUtilList = new ArrayList<>();
        List<Tags> tagsList = new ArrayList<>();
        tagsList.addAll(tagRepo.getTags());
        for(Tags tag : tagsList){
            TagUtil tagUtil = new TagUtil();
            tagUtil.setName(tag.getName());
            tagUtil.setWeight(0);
            tagUtilList.add(tagUtil);
        }
         tagResponse.setTagUtilList(tagUtilList);
        return tagResponse;
    }
}
