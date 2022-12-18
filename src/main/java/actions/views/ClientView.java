package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientView {

    private Integer id;

    private EmployeeView employee;

    private String name;

    private Integer deleteFlag;

}
