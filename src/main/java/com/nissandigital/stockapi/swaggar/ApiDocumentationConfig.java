package com.nissandigital.stockapi.swaggar;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Stock Api",
                version = "V12.0.12",
                title = "Stocks api powered by iextrading",
                contact = @Contact(
                   name = "Debojyoti Paul", 
                   email = "debojyoti@gmail.com", 
                   url = "http://www.debojyotipaul.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://debojyotipaul.com")
)
public interface ApiDocumentationConfig {

}