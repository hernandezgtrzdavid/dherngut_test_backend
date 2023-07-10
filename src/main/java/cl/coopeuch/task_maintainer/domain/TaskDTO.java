package cl.coopeuch.task_maintainer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

    private Integer taskId;
    private String description;
    private Date creationDate;
    private boolean activeTask;
}
