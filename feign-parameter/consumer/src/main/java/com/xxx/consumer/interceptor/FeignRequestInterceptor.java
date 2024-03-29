package com.xxx.consumer.interceptor;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {

        if(template.method().equals("GET")&&template.body()!=null){

            try {

                JsonNode jsonNode = objectMapper.readTree(template.body());
                template.body(null);
                Map<String, Collection<String>> queries = new HashMap<>();
                buildQuery(jsonNode,"",queries);
                template.queries(queries);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void buildQuery(JsonNode jsonNode,String path,Map<String, Collection<String>> queries) {

        if(!jsonNode.isContainerNode()){  //叶子节点
            if(jsonNode.isNull()){
                return;
            }
            Collection<String> values = queries.get(path);
            if(null==values){
                values = new ArrayList<>();
                queries.put(path,values);
            }
            values.add(jsonNode.asText());
            return;
        }
        if(jsonNode.isArray()){

            Iterator it = jsonNode.elements();
            while (it.hasNext()){
                buildQuery((JsonNode) it.next(),path,queries);
            }

        }else {

            Iterator<Map.Entry<String,JsonNode>> it = jsonNode.fields();
            while(it.hasNext()){
                Map.Entry<String,JsonNode> entry= it.next();
                if(StringUtils.hasText(path)){
                    buildQuery(entry.getValue(),path+"."+entry.getKey(),queries);
                }else {
                    buildQuery(entry.getValue(),entry.getKey(),queries);
                }
            }


        }


    }


}
