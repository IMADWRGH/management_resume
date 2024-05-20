package IMADWRGH.Gestion_CV.Exception;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorBody {
    String msg;
    Date date;
}
