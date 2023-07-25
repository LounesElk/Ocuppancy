package com.itso.occupancy.occupancy.controller.tag;

import com.itso.occupancy.occupancy.dto.model.client.ClientRechercheDto;
import com.itso.occupancy.occupancy.dto.model.tag.*;
import com.itso.occupancy.occupancy.model.Tag;
import com.itso.occupancy.occupancy.service.tag.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;

    @GetMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<TagDto>> getAllTag(){return tagService.getAllTag();} //Récupère tous les tag en get

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<TagDto> getTag(@PathVariable Long id){ //Récupère un tag en get
        return tagService.getTag(id);
    }

    @PostMapping("")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<TagDto>> postAllTag(){return tagService.postAllTag();} //Récupère tous les tag en post

    @PostMapping("/id")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<TagDto> postTag(@RequestBody TagIdDto tagIdDto){ //Récupère un tag en post
        return tagService.postTag(tagIdDto);
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<TagDto> createTag(@RequestBody TagCreateDto tagCreateDto){ //Crée un tag
        return tagService.createTag(tagCreateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<Object> deleteTag(@PathVariable Long id){  //Supp un tag
        return tagService.deleteTag(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<TagDto> updateTag(@PathVariable Long id, @RequestBody TagUpdateDto tagUpdateDto){ //Modifie un tag
        return tagService.updateTag(id,tagUpdateDto);
    }

    @PostMapping("/recherche")
    @PreAuthorize("hasPermission(returnObject, 'export')")
    public ResponseEntity<List<TagDto>> rechercheByName(@RequestBody TagRechercheDto recherche){ //Recherche un tag
        return tagService.rechercheByName(recherche);
    }
}
