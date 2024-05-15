package IMADWRGH.Gestion_CV.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Companies {
    private int id;
    private String name;
    private String department;
    private String yearExpression;
}
