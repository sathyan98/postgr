import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

public class actorservice {

    @Service
    public class CustomQueryService {
        Actorrepository actorrepository;

        @Autowired
        @Qualifier("mariaJdbcTemplate")
        private JdbcTemplate mariaJdbcTemplate;

        @Autowired
        @Qualifier("sqlJdbcTemplate1")
        private JdbcTemplate sqlJdbcTemplate1;

        public void getBoxplotData(String firstname, String lastname) {
            try {
                List<Map<String, Object>> boxplotData;
                String query;
                if(test.equals("All")) {
                    query = StringUtils.replaceEach(customQueryRepository.boxplotQuery_allMachines,
                            new String[]{"ParamName", "FromTime", "ToTime"},
                            new String[]{parameterName, FT, TT});

                }else{
                    query = StringUtils.replaceEach(customQueryRepository.boxplotQuery_machineSelection,
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
        }


    }
}