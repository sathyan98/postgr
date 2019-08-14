import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Actorservice {
    Actorrepository actorrepository;

    @Autowired
    @Qualifier("mariaJdbcTemplate")
    private JdbcTemplate mariaJdbcTemplate;

    @Autowired
    @Qualifier("sqlJdbcTemplate1")
    private JdbcTemplate sqlJdbcTemplate1;

    public List<Map<String, Object>> getBoxplotData(String parameterName, String FT, String TT, String Machine){
        try {
            List<Map<String, Object>> boxplotData;
            String query;
            if(Machine.equals("All")) {
                query = StringUtils.replaceEach(actorrepository.boxplotQuery_allMachines,
                        new String[]{"ParamName", "FromTime", "ToTime"},
                        new String[]{parameterName, FT, TT});

            }else{
                query = StringUtils.replaceEach(actorrepository.boxplotQuery_machineSelection,
                        new String[]{"ParamName", "FromTime", "ToTime", "MachineName"},
                        new String[]{parameterName, FT, TT, Machine});

            }
            boxplotData = mariaJdbcTemplate.queryForList(query);
            return boxplotData;
        }catch(Exception e){
            System.out.println("Exception from getBoxplotData function in CustomQueryService");
            throw e;
        }
    }
