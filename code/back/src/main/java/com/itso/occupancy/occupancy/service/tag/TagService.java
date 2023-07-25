package com.itso.occupancy.occupancy.service.tag;

import com.itso.occupancy.occupancy.dto.model.tag.*;
import com.itso.occupancy.occupancy.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface TagService {
    public ResponseEntity<List<TagDto>> getAllTag();
    public ResponseEntity<TagDto> getTag(Long id);
    public ResponseEntity<List<TagDto>> postAllTag();
    public ResponseEntity<TagDto> postTag(TagIdDto tagIdDto);
    public ResponseEntity<TagDto> createTag(TagCreateDto tagCreateDto);
    ResponseEntity<Object> deleteTag(Long id);
    ResponseEntity<TagDto> updateTag(Long id,TagUpdateDto tagUpdateDto);
    public ResponseEntity<List<TagDto>> rechercheByName(TagRechercheDto recherche);
}
