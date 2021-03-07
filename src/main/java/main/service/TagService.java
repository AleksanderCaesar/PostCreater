package main.service;

import main.api.response.TagResponse;
import main.model.TagToPost;
import main.model.Tags;
import main.repos.TagToPostRepo;
import main.repos.TagRepo;
import main.utils.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

@Service
public class TagService
{
    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private TagToPostRepo tagToPostRepo;

    public TagResponse getTagResponse(){
        TagResponse tagResponse = new TagResponse();
        List<TagUtil> tagUtilList = new ArrayList<>();
        List<Tags> tagsList = new ArrayList<>();
        tagsList.addAll(tagRepo.getTags());

        for(Tags tag : tagsList){
            TagUtil tagUtil = new TagUtil();
            tagUtil.setName(tag.getName());
            tagUtil.setWeight(getWeightOfTag(tag.getName())*getCoefficient());
            tagUtilList.add(tagUtil);
        }
         tagResponse.setTagUtilList(tagUtilList);
        return tagResponse;
    }

    public double getCoefficient (){
        List<TagToPost> tagToPostList = new ArrayList<>();
        tagToPostList.addAll(tagToPostRepo.getTagToPostList());
        TreeSet<String> tagNameList = new TreeSet<>();
        List<Integer> tagCount = new ArrayList<>();
        for(TagToPost tp : tagToPostList){
            tagNameList.add(tp.getTagId().getName());
        }
        for(String tagName : tagNameList){
           tagCount.add(tagToPostRepo.getTagToPostByTagName(tagName).size());
        }
        double k = 0l;
        try {
            Integer max = Collections.max(tagCount);
            int size = tagToPostList.size();
            k = 1/(max/size);
        } catch (ArithmeticException ex){
            ex.printStackTrace();
        }
        return k;
    }

    public double getWeightOfTag(String name){
        return tagToPostRepo.getTagToPostByTagName(name).size()/tagToPostRepo.getTagToPostList().size();
    }
}
