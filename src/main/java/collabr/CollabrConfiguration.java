package collabr;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class CollabrConfiguration extends Configuration {
    @NotNull
    @Valid
    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
