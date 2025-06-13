package models;

import lombok.Data;
import java.util.List;

@Data
public class PetDto {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}

