package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

import com.youme.restapi.YoumeServerSDK;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * 获取推流地址
     */
    @RequestMapping(value = {"/getPublishAddress/{userid}"}, method = RequestMethod.GET)
    public ResponseEntity<Object> getPublishAddress(@PathVariable String userid) {
        
        if(userid == null){
            return new ResponseEntity<Object>( getResponseObj(1,"teacher id can't be null",null), HttpStatus.OK);
        }
        JSONObject obj = new JSONObject();
        obj.put("Hub", "youmetest");
        obj.put("StreamKey", "1001");
        //obj.put("Expire", 3600 * 4); //地址有效期限制，单位秒，demo设置四小时

        JSONObject obj2=new JSONObject();
        obj2.put("RoomID","1001");
        obj2.put("UserId","54321");
        obj2.put("DstUrl","rtmp://pili-publish.youme.im/youmetest/1001");


        try {

			String rslt = YoumeServerSDK.getInstance().sendPost("video","get_rtmp_publish_url", obj.toString());
            String rslt2= YoumeServerSDK.getInstance().sendPost("im","set_single_rtmp_param",obj2.toString());

			System.out.println(rslt);
			System.out.println(rslt2);
			
			JSONParser parser = new JSONParser();
			JSONObject rsltObj = (JSONObject)parser.parse(rslt);
		    return new ResponseEntity<Object>( getResponseObj(0,"",rsltObj), HttpStatus.OK);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		}
    }


    /**
     * 获取实时观看地址
     */
    @RequestMapping("/getLivePlayAddress/{userid}")
    public ResponseEntity<Object> getPlaybackAddress(@PathVariable String userid) {
        if(userid == null){
            return new ResponseEntity<Object>( getResponseObj(1,"teacher id can't be null",null), HttpStatus.OK);
        }
        JSONObject obj = new JSONObject();
        obj.put("Hub", "youmetest");
        obj.put("StreamKey", userid);

        try {
			String rslt = YoumeServerSDK.getInstance().sendPost("video","get_rtmp_play_url", obj.toString());
		    return this.getSuccessResult(rslt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		}
    }


    /**
     * 获取历史纪录
     */
    @RequestMapping("/getPublishHistory/{userid}/{starttime}/{endtime}")
    public ResponseEntity<Object> getPublishHistory(@PathVariable String userid,@PathVariable Long starttime,@PathVariable Long endtime) {
        if(userid == null){
            return new ResponseEntity<Object>( getResponseObj(1,"teacher id can't be null",null), HttpStatus.OK);
        }
        JSONObject obj = new JSONObject();
        obj.put("Hub", "youmetest");
        obj.put("StreamKey", userid);
        obj.put("StartTimestamp", starttime);
        obj.put("EndTimestamp", endtime);
        System.out.println( obj.toString());
        try {
			String rslt = YoumeServerSDK.getInstance().sendPost("video","get_history_activity", obj.toString());
			return this.getSuccessResult(rslt);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		}
    }

    /**
     * 获取回看地址
     */
    @RequestMapping(value = "/getPlaybackURL/{userid}/{starttime}/{endtime}",method = RequestMethod.GET)
    public ResponseEntity<Object> savePublishFile(@PathVariable String userid,@PathVariable Long starttime,@PathVariable Long endtime) {
        if(userid == null){
            return new ResponseEntity<Object>( getResponseObj(1,"teacher id can't be null",null), HttpStatus.OK);
        }
        JSONObject obj = new JSONObject();
        obj.put("Hub", "youmetest");
        obj.put("StreamKey", userid);
        obj.put("StartTimestamp", starttime);
        obj.put("EndTimestamp", endtime);

        try {
			String rslt = YoumeServerSDK.getInstance().sendPost("video","save_play_back", obj.toString());
			return this.getSuccessResult(rslt);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		}
    }

    // 服务器端发消息Demo
    @RequestMapping(value = "/sendmsg",method = RequestMethod.GET)
    public ResponseEntity<Object> sendMessage() {
       
        JSONObject obj = new JSONObject();
        obj.put("MsgSeq", ""+System.currentTimeMillis());
        obj.put("ChatType", 2);//1 是私聊，2是频道消息
        obj.put("SendID", "admin");
        obj.put("RecvID", "roomid");//ChatType为1时，这个是接收者userid，2时时频道id
        obj.put("Content", "this is msg from server,收到？");

        try {
			String rslt = YoumeServerSDK.getInstance().sendPost("im","query_im_send_msg", obj.toString());
			return this.getSuccessResult(rslt);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		}
    }

      // 服务器端发二进制消息Demo
      @RequestMapping(value = "/sendByteMsg",method = RequestMethod.GET)
      public ResponseEntity<Object> sendByteMessage() {
         
          JSONObject obj = new JSONObject();
          obj.put("MsgSeq", ""+System.currentTimeMillis());
          obj.put("ChatType", 2);//1 是私聊，2是频道消息
          obj.put("SendID", "admin");
          obj.put("RecvID", "roomid");//ChatType为1时，这个是接收者userid，2时时频道id
          try{
            obj.put("Content", Base64.getEncoder().encodeToString("可以是任意二进制数据".getBytes("utf-8")));
          }catch(Exception e){
            e.printStackTrace();
          }
          obj.put("Base64", 1);
  
          try {
              String rslt = YoumeServerSDK.getInstance().sendPost("im","query_im_send_msg", obj.toString());
              return this.getSuccessResult(rslt);	
          } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
          }
      }

    //获取游密服务器抄送过来的消息
    @RequestMapping(value = "/recvmsg", method = RequestMethod.POST)
    public ResponseEntity<YMMessage> update(@RequestBody YMMessage msg) {
        //直接返回收到的内容，方便测试
        if(msg.getMsg_type() == 12){
            //二进制消息，需要base64 解码
            byte[] customMsg = Base64.getDecoder().decode(msg.getMsg_content());

        }
         return new ResponseEntity<YMMessage>(msg, HttpStatus.OK);
    }

    private JSONObject getResponseObj(int code,String msg,Object result){
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("msg",msg);
        if(result != null){
            obj.put("result",result);
        }
        return obj;
    }

    private ResponseEntity<Object> getSuccessResult(String ret){
        System.out.println(ret);
        JSONParser parser = new JSONParser();
		JSONObject rsltObj;
		try {
			rsltObj = (JSONObject)parser.parse(ret);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<Object>( getResponseObj(999,e.getMessage(),null), HttpStatus.OK);
		} 
		return new ResponseEntity<Object>( getResponseObj(0,"",rsltObj), HttpStatus.OK);
    }
    
}
