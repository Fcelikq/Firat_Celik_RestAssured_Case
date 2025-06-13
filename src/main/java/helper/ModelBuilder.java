package helper;

import models.Category;
import models.PetDto;
import models.Tag;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.FILE_PATH_DOG_IMAGE;

public class ModelBuilder {
    
    public static PetDto createPetBody(Long id, String name) {
        PetDto pet = new PetDto();
        pet.setId(id);
        pet.setName(name);
        
        // Set category
        Category category = new Category();
        category.setId(1L);
        category.setName(name);
        pet.setCategory(category);
        
        // Set photo URLs
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(FILE_PATH_DOG_IMAGE);
        pet.setPhotoUrls(photoUrls);
        
        // Set tags
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName(name);
        tags.add(tag);
        pet.setTags(tags);
        
        // Set status
        pet.setStatus("available");
        
        return pet;
    }
    
    public static PetDto createPetBodyWithStatus(Long id, String name, String status) {
        PetDto pet = createPetBody(id, name);
        pet.setStatus(status);
        return pet;
    }
}
