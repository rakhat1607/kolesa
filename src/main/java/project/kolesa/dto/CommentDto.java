package project.kolesa.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String comment;
    private UserDto userDto;
    private AdDto adDto;
}
