package com.itso.occupancy.occupancy.service.tag;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.ElementNotFoundException;
import com.itso.occupancy.occupancy.dto.mapper.TagMapper;
import com.itso.occupancy.occupancy.dto.model.tag.*;
import com.itso.occupancy.occupancy.model.Tag;
import com.itso.occupancy.occupancy.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public ResponseEntity<List<TagDto>> getAllTag(){
        List<TagDto> allTag = tagRepository.SupprimerIsFalseOrderByName().stream().map(TagMapper::toTagDto).toList();
        return new ResponseEntity<>(allTag, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagDto> getTag(Long id){
        TagDto tag = TagMapper.toTagDto(findTagIfExists(id));
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TagDto>> postAllTag(){
        List<TagDto> allTag = tagRepository.SupprimerIsFalseOrderByName().stream().map(TagMapper::toTagDto).toList();
        return new ResponseEntity<>(allTag, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagDto> postTag(TagIdDto tagIdDto){
        TagDto tag = TagMapper.toTagDto(findTagIfExists(tagIdDto.getId()));
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TagDto> createTag(TagCreateDto tagCreateDto){
        Tag tag = TagMapper.toTagModel(tagCreateDto);
        Tag tag1 = tagRepository.save(tag);
        TagDto tagDto = TagMapper.toTagDto(tag1);
        return new ResponseEntity<>(tagDto , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteTag(Long id){
        Tag tag = findTagIfExists(id);
        tag.setSupprimer(Boolean.TRUE);
        tagRepository.save(tag);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<TagDto> updateTag(Long id, TagUpdateDto tagUpdateDto){
        Tag tag = findTagIfExists(id);
        tag.setName(tagUpdateDto.getName())
                .setCode(tagUpdateDto.getCode());
        Tag tag1 = tagRepository.save(tag);
        TagDto tagDto = TagMapper.toTagDto(tag1);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    public ResponseEntity<List<TagDto>> rechercheByName(TagRechercheDto recherche){
        List<TagDto> allTag = tagRepository.findByNameContainingIgnoreCaseAndSupprimerIsFalseOrderByName(recherche.getName()).stream().map(TagMapper::toTagDto).toList();
        return new ResponseEntity<>(allTag, HttpStatus.OK);
    }

    //Gestion des erreurs
    private Tag findTagIfExists(Long id) {

        return tagRepository.findByIdAndSupprimerIsFalse(id)
                .orElseThrow(() -> new ElementNotFoundException(
                        String.format("Unable to find Tag [id = %s] or it is deleted", id)
                ));
    }
}
