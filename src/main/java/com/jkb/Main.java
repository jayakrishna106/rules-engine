package com.jkb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Read input from JSON file
        Map<String, Object> data = readJsonFile("input.json");

        // Load the rules dynamically from the database
        String rulesContent = loadRulesFromDatabase();
        KieSession kieSession = createKieSessionFromRules(rulesContent);

        kieSession.insert(data);
        kieSession.fireAllRules();

        // Print the discount and address status
        double discount = (double) data.get("discount");
      //  boolean isDiscounted = (boolean) ((Map<String, Object>) data.get("address")).get("discounted");
        System.out.println("Discount: " + discount);
      //  System.out.println("Address Discounted: " + isDiscounted);

        // Dispose the session
        kieSession.dispose();
    }

    private static Map<String, Object> readJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue("{\n" +
                    "  \"age\": 65,\n" +
                    "  \"address\": {\n" +
                    "    \"city\": \"New York\"\n" +
                    "  }\n" +
                    "}", HashMap.class);

            System.out.println(map);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String loadRulesFromDatabase() {
        // Replace this with your code to fetch the rules content from the database
        // and return it as a string
        // Example:
        // Retrieve rules from the database and return the content as a string
        // String rulesContent = databaseService.getRulesContent();
        // return rulesContent;
        return "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                "import java.util.Map;" +
                "rule \"Age Discount Rule\"\n" +
                "when\n" +
                "    $person: Map(this[\"age\"] > 60" +
              //  ", $address: address" +
                ")\n" +
             //   "    $address: Map(this[\"city\"] == \"New York\")\n" +
                "then\n" +
                "    double discount = 0.2; // Apply a 20% discount\n" +
                "    $person.put(\"discount\", discount);\n" +
             //   "    $address.put(\"discounted\", true);\n" +
                "end";
    }

    private static String loadRulesOfDSLRFromDatabase() {
        // Replace this with your code to fetch the rules content from the database
        // and return it as a string
        // Example:
        // Retrieve rules from the database and return the content as a string
        // String rulesContent = databaseService.getRulesContent();
        // return rulesContent;
        return "import com.fasterxml.jackson.databind.ObjectMapper;\n" +
                "import java.util.Map;" +
                "rule \"Age Discount Rule\"\n" +
                "    when\n" +
                "        Customer(age > 60, address.city == \"New York\")\n" +
                "    then\n" +
                "        applyDiscount(0.2);\n" +
                "end\n" +
                "\n" +
                "rule \"Membership Discount Rule\"\n" +
                "    when\n" +
                "        Customer(membershipLevel == \"Gold\")\n" +
                "    then\n" +
                "        applyDiscount(0.15);\n" +
                "end\n" +
                "\n" +
                "rule \"Order Threshold Rule\"\n" +
                "    when\n" +
                "        Order(total > 1000)\n" +
                "    then\n" +
                "        setLargeOrderFlag(true);\n" +
                "end\n" +
                "\n" +
                "rule \"Product Category Rule\"\n" +
                "    when\n" +
                "        Product(price > 500)\n" +
                "    then\n" +
                "        setCategory(\"Premium\");\n" +
                "end";
    }

    private static KieSession createKieSessionFromRules(String rulesContent) {
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newByteArrayResource(rulesContent.getBytes()), ResourceType.DRL);
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException("Error compiling rules:\n" + knowledgeBuilder.getErrors().toString());
        }

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(rulesContent, ResourceType.DRL);
        KieContainer kieContainer = kieHelper.getKieContainer();

        Collection<KiePackage> packages = kieContainer.getKieBase().getKiePackages();
        kieContainer.getKieBase().getKiePackages().addAll(packages);

        return kieContainer.newKieSession();
    }
}
