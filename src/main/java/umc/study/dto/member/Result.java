package umc.study.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {

    private int count;
    private T data;
}