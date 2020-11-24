package com.avalon.ms.common.json;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jackson sample test
 */
public class JsonUtilTest {

    public static class User{
        public enum Gender{ MALE, FEMALE;}
        public static class Name{
            private String _first,_last;

            public String get_first() {
                return _first;
            }

            public void set_first(String _first) {
                this._first = _first;
            }

            public String get_last() {
                return _last;
            }

            public void set_last(String _last) {
                this._last = _last;
            }
        }
        private Gender _gender;
        private Name _name;
        private boolean _isVerified;
        private byte[] _userImage;
        private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Gender get_gender() {
            return _gender;
        }

        public void set_gender(Gender _gender) {
            this._gender = _gender;
        }

        public Name get_name() {
            return _name;
        }

        public void set_name(Name _name) {
            this._name = _name;
        }

        public boolean is_isVerified() {
            return _isVerified;
        }

        public void set_isVerified(boolean _isVerified) {
            this._isVerified = _isVerified;
        }

        public byte[] get_userImage() {
            return _userImage;
        }

        public void set_userImage(byte[] _userImage) {
            this._userImage = _userImage;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * jsonInclude 注解 空值或null  不参加序列化
     * 该注解放在属性上，只对该属性有影响，放在类上都全部属性都有影响
     */
    //@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
    static class JsonResult{

        String code;
        String msg;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        String id;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    @Test
    public void obj2json2() throws Exception{
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("111111");
        jsonResult.setMsg("success");
        jsonResult.setId("123123123123123");

        JsonResult jsonResult1 = new JsonResult();
        jsonResult1.setCode("1111112");
        jsonResult1.setMsg("success2");


        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(jsonResult);
        System.out.println(s);
       // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       // objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String s1 = objectMapper.writeValueAsString(jsonResult1);
        System.out.println(s1);
    }

    @Test
    public void map2Json() throws IOException {
        Map<String, Object> userData = new HashMap<>();
        Map<String, String> nameStruct = new HashMap<>();
        nameStruct.put("first", "Joe");
        nameStruct.put("last", "Sixpack");
        userData.put("name", nameStruct);
        userData.put("gender", "MALE");
        userData.put("verified", Boolean.FALSE);
        userData.put("userImage", "Rm9vYmFyIQ==");

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.writeValue(new File("user-modified.json"), userData);
        String s = objectMapper.writeValueAsString(userData);
        System.out.println(s);
    }
    
    @Test
    public void json2Map() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> stringUserMap = objectMapper.readValue(new File("user-modified.json"), new TypeReference<Map<String, Object>>() {
        });
        stringUserMap.forEach( (k,v) -> {
            System.out.println(k+":"+v);
        });
    }

    @Test
    public void putTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("code", 111111);
        objectNode.put("msg", "success");
        ObjectNode suObjectNode = objectMapper.createObjectNode();
        suObjectNode.put("id", 123456789L);
        suObjectNode.put("money", 12.34d);
        objectNode.putAll(suObjectNode);
        String s = objectNode.toPrettyString();
        String s1 = objectNode.toString();
        System.out.println(s);
        System.out.println(s1);
        Assert.assertEquals(s, s1);
    }

    @Test
    public void putTest2() throws IOException {
        JsonFactory f = new JsonFactory();
        JsonGenerator g = f.createGenerator(new File("user.json"), JsonEncoding.UTF8);
        //start data
        g.writeStartObject();
        //start name
        g.writeObjectFieldStart("name");
        g.writeStringField("first", "joe");
        g.writeStringField("last", "pack");
        //end name
        g.writeEndObject();
        g.writeStringField("gender", "male");
        g.writeBooleanField("verified", false);
        g.writeFieldName("userImage");
        g.writeBinary("12345678".getBytes());
        g.writeStringField("date", new Date().toString());
        //end data
        g.writeEndObject();
        g.close();
    }

    @Test
    public void putTest3() throws IOException {
        JsonFactory f = new JsonFactory();
        JsonParser jp = f.createParser(new File("user.json"));
        User user = new User();
        jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jp.getCurrentName();
            jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
            if ("name".equals(fieldname)) { // contains an object
                User.Name name = new User.Name();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    String namefield = jp.getCurrentName();
                    jp.nextToken(); // move to value
                    if ("first".equals(namefield)) {
                        name.set_first(jp.getText());
                    } else if ("last".equals(namefield)) {
                        name.set_last(jp.getText());
                    } else {
                        throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
                    }
                }
                user.set_name(name);
            } else if ("gender".equals(fieldname)) {
                user.set_gender(User.Gender.valueOf(jp.getText()));
            } else if ("verified".equals(fieldname)) {
                user.set_isVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
            } else if ("userImage".equals(fieldname)) {
                user.set_userImage(jp.getBinaryValue());
            } else {
                throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
            }
        }
        jp.close(); // ensure resources get cleaned up timely and properly
        System.out.println(user.toString());
    }

    @Test
    public void readTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File("user.json"));
        System.out.println(jsonNode.get("date").toString());
        String nameJson = jsonNode.get("name").toString();
        System.out.println(jsonNode.get("name").get("lasts"));
        System.out.println(jsonNode.get("name").get("first"));
        System.out.println("find:"+jsonNode.findValue("names"));
        JsonNode nameNode = objectMapper.readTree(nameJson);
        System.out.println(nameNode.get("last").toString());
        System.out.println(nameNode.get("first").toString());
    }

    @Test
    public void readTest2() throws JsonProcessingException {
        User user = new User();
        user.set_userImage("2345".getBytes());
        user.set_isVerified(true);
        user.set_gender(User.Gender.FEMALE);
        user.setDate(new Date());
        user.set_name(new User.Name(){{
            set_last("last");
            set_first("first");
        }});

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(user);

        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        JsonNode returncode = jsonNode.get("returncode");
        System.out.println("get:"+returncode);
        JsonNode returncode1 = jsonNode.findPath("returncode");
        System.out.println("findPath:"+returncode1);
        JsonNode returncode2 = jsonNode.findValue("returncode");
        System.out.println("findValue:"+returncode2);
    }

    @Test
    public void obj2json() throws JsonProcessingException {
        User user = new User();
        user.set_userImage("2345".getBytes());
        user.set_isVerified(true);
        user.set_gender(User.Gender.FEMALE);
        user.setDate(new Date());
        user.set_name(new User.Name(){{
            set_last("last");
            set_first("first");
        }});

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(user);
        System.out.println(s);
    }

    @Test
    public void json2obj() throws JsonProcessingException {
        String jsonStr = "{\"code\":\"1111111\",\"costTime\":315,\"data\":{\"id\":214396739854035539,\"orderMoney\":1.0,\"orderId\":\"iOS1604642733197\",\"wubaCash\":1.0},\"method\":\"applyRefundV2\",\"msg\":\"成功\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        System.out.println(jsonNode.get("code"));
        System.out.println(jsonNode.get("code").asText());
    }
}