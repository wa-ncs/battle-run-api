package com.wancs.battle_run.domain.running.dto.response;

import com.wancs.battle_run.domain.running.entity.Record;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

import static com.wancs.battle_run.global.common.InitializationByType.*;

@Getter
public class RecordListResponseDto {
    private Long id;
    private Float distance;
    private Long runningTime;
    private LocalDateTime createdDate;

    @Builder
    public RecordListResponseDto(Record entity) {
        this.id = longCheck(entity.getId());
        this.distance = floatCheck(entity.getDistance());
        this.runningTime = longCheck(entity.getRunningTime());
        this.createdDate = entity.getCreatedDate();
    }

    public static RecordListResponseDto fromEntity(Record entity){
        return RecordListResponseDto.builder()
                .entity(entity)
                .build();
    }
}
