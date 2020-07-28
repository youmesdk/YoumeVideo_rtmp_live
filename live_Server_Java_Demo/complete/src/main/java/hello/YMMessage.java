package hello;
public class YMMessage {

    private String from; //抄送者固定为"youme_msg_cc"
    private String serial_no;//消息抄送序列号，可用于排除重复接收
    private String msg_id;//消息id
    private String snder_id;//发送者id
    private String recver_id;//接收者id
    private Integer msg_type;//消息类型，6:文本消息；7：语音消息；12：二进制消息；15：礼物消息
    private Integer chat_type;//聊天类型，1：私聊；2：频道消息；
    private String msg_content;//消息内容，若为语音消息，内容是该语音的URL；若为二进制消息，内容为原始消息Base64编码后内容；若为礼物消息，内容固定是“礼物消息”
    private String c_time;//消息创建时间
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @return the c_time
	 */
	public String getC_time() {
		return c_time;
	}
	/**
	 * @param c_time the c_time to set
	 */
	public void setC_time(String c_time) {
		this.c_time = c_time;
	}
	/**
	 * @return the msg_content
	 */
	public String getMsg_content() {
		return msg_content;
	}
	/**
	 * @param msg_content the msg_content to set
	 */
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	/**
	 * @return the chat_type
	 */
	public Integer getChat_type() {
		return chat_type;
	}
	/**
	 * @param chat_type the chat_type to set
	 */
	public void setChat_type(Integer chat_type) {
		this.chat_type = chat_type;
	}
	/**
	 * @return the msg_type
	 */
	public Integer getMsg_type() {
		return msg_type;
	}
	/**
	 * @param msg_type the msg_type to set
	 */
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	/**
	 * @return the recver_id
	 */
	public String getRecver_id() {
		return recver_id;
	}
	/**
	 * @param recver_id the recver_id to set
	 */
	public void setRecver_id(String recver_id) {
		this.recver_id = recver_id;
	}
	/**
	 * @return the snder_id
	 */
	public String getSnder_id() {
		return snder_id;
	}
	/**
	 * @param snder_id the snder_id to set
	 */
	public void setSnder_id(String snder_id) {
		this.snder_id = snder_id;
	}
	/**
	 * @return the msg_id
	 */
	public String getMsg_id() {
		return msg_id;
	}
	/**
	 * @param msg_id the msg_id to set
	 */
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	/**
	 * @return the serial_no
	 */
	public String getSerial_no() {
		return serial_no;
	}
	/**
	 * @param serial_no the serial_no to set
	 */
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

}