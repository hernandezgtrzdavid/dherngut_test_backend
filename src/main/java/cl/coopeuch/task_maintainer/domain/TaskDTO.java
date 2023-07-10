package cl.coopeuch.task_maintainer.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class TaskDTO {

    private Integer taskId;
    private String creationUser;
    private String description;
    private Date creationDate;
    private boolean activeTask;
}
