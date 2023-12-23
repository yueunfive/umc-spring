package umc.study.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {

    @ApiModelProperty(value = "전체 수", example = "1")
    private int count;

    @ApiModelProperty(value = "조회 목록")
    private List<T> data;
}