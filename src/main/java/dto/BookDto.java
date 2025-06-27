package dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String description;
    private Integer pageCount;
    private String excerpt;
    private String publishDate; //todo deserialize as time
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX")
//    private OffsetDateTime publishDate;
}
