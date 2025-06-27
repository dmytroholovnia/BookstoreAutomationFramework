package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private Integer id;
    private String title;
    private String description;
    private Integer pageCount;
    private String excerpt;
    private OffsetDateTime publishDate;
}
