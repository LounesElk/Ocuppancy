package com.itso.occupancy.occupancy.dto.mapper;

import com.itso.occupancy.occupancy.dto.model.tag.*;
import com.itso.occupancy.occupancy.model.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TagMapper {
    public static TagDto toTagDto(Tag tag){
        return new TagDto().setId(tag.getId()).setName(tag.getName()).setCode(tag.getCode());
    }
    public static TagCreateDto ToTagCreateDto(Tag tag){
        return new TagCreateDto().setName(tag.getName()).setCode(tag.getCode());
    }
    public static TagUpdateDto ToTagUpdateDto(Tag tag){
        return new TagUpdateDto().setName(tag.getName()).setCode(tag.getCode());
    }
    public static Tag toTagModel(TagCreateDto tagCreateDto){
        return new Tag().setName(tagCreateDto.getName()).setCode(tagCreateDto.getCode());
    }
}
