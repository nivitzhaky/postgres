package com.handson.postgres.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.handson.postgres.controller.ProductsController;
import com.handson.postgres.json.ProductIn;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestContext {

    private final String userCreate = UUID.randomUUID().toString();

    private final Map<String, String> vars = new HashMap<>();

    private final ObjectMapper om;

    private final Principal principal;

    public TestContext(ObjectMapper om) {
        super();
        this.om = om;
        vars.put("Webservice_URL", "http://localhost:8080");
        vars.put("ProductTitleCreate", "");
        vars.put("CategoryCreate", "");
        principal = mock(Principal.class);
        when(principal.getName()).thenReturn(userCreate);
    }

    public void givenProducts(int numPatients, ProductsController productsController) throws Exception {

        for (int i = 0; i < numPatients; i++) {
            vars.put("ProductTitleCreate", "ProductNo" + Strings.padStart(Integer.toString(i), 5, '0'));
            vars.put("CategoryCreate", "category" + (i % 2));
            productsController.addNewProduct(getUser(), get("json/product.json", ProductIn.class));
        }
    }

    private String populate(String source) {
        StrSubstitutor sub = new StrSubstitutor(vars, "{{", "}}");
        return sub.replace(source);
    }

    public <T> T get(String jsonFile, Class<T> clazz) throws Exception {
        String json = readFile(ClassLoader.getSystemResource(jsonFile).toURI());
        String populatedJson = populate(json);
        return om.readValue(populatedJson, clazz);
    }

    public Principal getPrincipal() {
        return principal;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public Principal getUser() {
        return principal;
    }

    private static String readFile(URI filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

}
