package IMADWRGH.Gestion_CV.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private int id;
    private String profile;
    private Information idInfons;
    private List<Skills> idSkills;
    private List<Companies> idComps;

}
