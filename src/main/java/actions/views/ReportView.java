package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportView {

    private Integer id;

    private EmployeeView employee;

    private ClientView client;

    private LocalDate reportDate;

    private String title;

    private String content;

    private String progress;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
