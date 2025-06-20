package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDto {
    private long id;
    private String name;
}
