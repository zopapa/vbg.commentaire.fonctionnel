package com.bootcamp.utils;


import com.bootcamp.commons.models.Criteria;
import com.bootcamp.commons.models.Criterias;

import com.bootcamp.constants.CommonsWsConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darextossa on 11/27/17.
 */
public class RequestParser implements CommonsWsConstants {

    public static Criterias getCriterias(HttpServletRequest request){
        Map<String, String[]> queryParameters = getQueryParameters(request);
        if(queryParameters.get(CRITERIA_FIELDS) == null) return null;

        String criteriaParameter = queryParameters.get(CRITERIA_FIELDS)[0];

        Criterias criterias = new Criterias();
        String[] listCriteriaStr = criteriaParameter.split(",");

        for(String criteriaStr: listCriteriaStr){
            String operator = getOperator(criteriaStr);
            String[] criteriaFields = operator.split(operator);
            Criteria criteria = new Criteria(criteriaFields[0], operator, criteriaFields[1]);
            criterias.addCriteria(criteria);
        }
        return criterias;
    }

    public static  List<String> getFields(HttpServletRequest request){
        Map<String, String[]> queryParameters = getQueryParameters(request);
        if(queryParameters.get(CRITERIA_FIELDS) == null) return null;

        String fieldsParameter = queryParameters.get(SELECT_FIELDS)[0];
        String[] listFieldsStr = fieldsParameter.split(",");
        List<String> fields = new ArrayList<>();

        for(String fieldStr: listFieldsStr){
            fields.add(fieldStr);
        }
        return fields;
    }

    public static Map<String, String[]> getQueryParameters(HttpServletRequest request) {
        Map<String, String[]> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();

        if (StringUtils.isEmpty(queryString)) {
            return queryParameters;
        }

        String[] parameters = queryString.split("&");

        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");
            String[] values = queryParameters.get(keyValuePair[0]);
            values = ArrayUtils.add(values, keyValuePair.length == 1 ? "" : keyValuePair[1]); //length is one if no value is available.
            queryParameters.put(keyValuePair[0], values);
        }
        return queryParameters;
    }

    private static  ArrayList<String> getQueryOperators(){
        return new ArrayList<String>() {{
            add("=");
            add("<>");
            add(">");
            add("<");
        }};
    }


    private static HashMap<String, String> queryOperatorMap(){
        return new HashMap<String, String>() {{
            put("=","=");
            put("<>","<>");
            put(">",">");
            put("<","<");
        }};
    }

    private static String getOperator(String query){
        ArrayList<String> operators = getQueryOperators();
        for (String operator: operators){
            if(query.indexOf(operator) != -1) return operator;
        }

        return null;
    }
}
